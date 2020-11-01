package framework.browser;

import framework.config.AutomationAppContext;
import framework.logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Browser implements IBrowser {

    private static volatile Browser browser = null;
    private WebDriver driver;

    private Browser() {
        driver = new DriverFactory().init();
    }

    public static Browser getBrowser() {
        Browser localBrowser = browser;
        if (localBrowser == null) {
            synchronized (DriverFactory.class) {
                localBrowser = browser;
                if (localBrowser == null) {
                    browser = new Browser();
                }
            }
        }
        return browser;
    }

    @Override
    public boolean isInit() {
        return driver != null;
    }

    @Override
    public void navigate(final String url) {
        Log.info("Navigate to:" + url);
        driver.get(url);
    }

    @Override
    public void close() {
        Log.info("Close browser");
        if (!isInit()) {
            return;
        }
        driver.close();
        driver = null;
        browser = null;
    }

    @Override
    public void quite() {
        Log.info("Quite browser");
        if (!isInit()) {
            return;
        }
        driver.quit();
        driver = null;
        browser = null;

    }

    @Override
    public void setTimeout(int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @Override
    public File getScreenshotAsFile() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    @Override
    public void wait(Function<WebDriver, ?> isTrue, int timeout) {
        new WebDriverWait(driver, timeout)
                .until(isTrue);
    }

    @Override
    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) driver;
    }

    @Override
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    @Override
    public void openNewWindow() {
        Log.info("Open new browser window");
        new WebDriverWait(driver, AutomationAppContext.getBrowserValues().getTimeout())
                .until(drive -> new ArrayList<>(driver.getWindowHandles()).size() > 1);
        List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }

    @Override
    public void switchFrame(By element) {
        driver.switchTo().frame(findElement(element));
    }

    @Override
    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }
}
