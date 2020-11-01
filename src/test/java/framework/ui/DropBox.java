package framework.ui;

import framework.BaseElement;
import framework.entity.IDropBoxItem;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class DropBox extends BaseElement {

  public DropBox(By locator, String name) {
    super(locator, name);
  }

  public void selectItem(IDropBoxItem item) {
    new Select(findElement()).selectByValue(item.getValue());
  }

}
