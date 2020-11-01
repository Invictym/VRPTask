package framework;

import framework.browser.Browser;
import framework.config.AutomationAppContext;
import framework.entity.BrowserValues;
import org.testng.annotations.*;

public abstract class BaseEntity {

  protected BrowserValues values = AutomationAppContext.getBrowserValues();

  public Browser getBrowser() {
    return Browser.getBrowser();
  }

  @BeforeClass
  public void beforeTest() {
    getBrowser().navigate(values.getUrl());
  }

  @AfterClass
  public void afterTest() {
    getBrowser().close();
  }

  @AfterSuite
  public void exit() {
    getBrowser().quite();
  }

}
