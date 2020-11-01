package framework;

import framework.config.AutomationAppContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import framework.logger.Log;

public abstract class BaseElement extends BaseEntity {

  protected final By locator;
  private final String name;

  protected BaseElement(final By loc, final String nameOf) {
    locator = loc;
    name = nameOf;
  }

  private void waitForElementPresent(int timeout) {
    getBrowser().setTimeout(1);
    getBrowser().wait(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator), timeout);
    getBrowser().setTimeout(AutomationAppContext.getBrowserValues().getTimeout());
  }

  private void waitForElementPresent() {
    waitForElementPresent(values.getTimeout());
  }

  public void click() {
    waitForElementPresent();
    moveToElement();
    waitElementClickable();
    findElement().click();
    Log.info(name + " click");
  }

  public String getText() {
    int attempts = 0;
    boolean result = false;
    String text = null;
    while (attempts < 2) {
      try {
        moveToElement();
        text = findElement().getText();
        result = true;
        break;
      } catch (StaleElementReferenceException e) {

      }
      attempts++;
    }
    Assert.assertTrue(result);
    Log.info("Get text from [" + name + "]");
    return text;
  }
  public By getLocator() {
    return locator;
  }

  public void waitElementClickable() {
    getBrowser().setTimeout(1);
    getBrowser().wait(ExpectedConditions.elementToBeClickable(locator), AutomationAppContext.getBrowserValues().getTimeout());
    getBrowser().setTimeout(AutomationAppContext.getBrowserValues().getTimeout());
  }

  public void moveToElement() {
    getBrowser().getJsExecutor().executeScript("arguments[0].scrollIntoView(true);", findElement());
  }

  public void clickJs() {
    getBrowser().getJsExecutor().executeScript("arguments[0].click()", findElement());
  }

  protected WebElement findElement() {
    return getBrowser().findElement(locator);
  }

  public boolean elementIsDisplayed() {
    return findElement().isDisplayed();
  }

  public void waitWithoutException() {
    framework.logger.Log.info("Wait for element present: " + name);
    try {
      waitForElementPresent(3);
    } catch (Exception ex) {
      framework.logger.Log.info("Info: element not found");
    }
  }
  public void sendKeyClear(String key) {
    waitForElementPresent();
    waitElementClickable();
    moveToElement();
    for (int i = 0; i < 10; i++) {
      try {
        findElement().clear();
        findElement().sendKeys(key);
        break;
      } catch (ElementNotInteractableException ex) {
        Log.info("Wait element");
      }
    }
    Log.info(name + " set text: " + key);
  }

  public void sendKey(String key) {
    waitForElementPresent();
    waitElementClickable();
    moveToElement();
    for (int i = 0; i < 10; i++) {
      try {
        findElement().sendKeys(key);
        break;
      } catch (ElementNotInteractableException ex) {
        Log.info("Wait element");
      }
    }
    Log.info(name + " set text: " + key);
  }

  public void sendKeyJs(String key) {
    waitForElementPresent();
    waitElementClickable();
    moveToElement();
    getBrowser().getJsExecutor().executeScript("arguments[0].value='" + key + "';", findElement());
    Log.info(name + " set text by Js: " + key);
  }

}
