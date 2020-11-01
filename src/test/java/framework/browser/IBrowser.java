package framework.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.function.Function;

public interface IBrowser {

    void navigate(final String url);

    void close();

    void quite();

    void openNewWindow();

    void setTimeout(int timeout);

    boolean isInit();

    void wait(Function<WebDriver, ?> isTrue, int timeout);

    JavascriptExecutor getJsExecutor();

    WebElement findElement(By locator);

    File getScreenshotAsFile();

    void switchFrame(By by);

    void switchToDefault();
}
