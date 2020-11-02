package framework;

import framework.browser.Browser;
import framework.config.AutomationAppContext;
import framework.entity.BrowserValues;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public abstract class BaseEntity {

  protected BrowserValues values = AutomationAppContext.getBrowserValues();

  public Browser getBrowser() {
    return Browser.getBrowser();
  }

  @BeforeClass(alwaysRun = true)
  public void beforeTest() {
    getBrowser().navigate(values.getUrl());
  }

  @AfterClass(alwaysRun = true)
  public void afterTest() {
    Browser.close();
  }

  @AfterSuite(alwaysRun = true)
  public void exit() {
    Browser.quite();
  }

}
