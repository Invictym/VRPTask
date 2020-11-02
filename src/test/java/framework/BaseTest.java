package framework;

import framework.logger.Log;
import framework.utils.ScreenshotListener;
import framework.utils.TestStepsListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.util.Map;

@Listeners({ScreenshotListener.class, TestStepsListener.class})
public class BaseTest extends BaseEntity {

  protected Map<String, String> parameters;

  @BeforeTest
  public void setUp(ITestContext context) {
    Log.info("Get parameters");
    parameters = context.getCurrentXmlTest().getAllParameters();
  }
}
