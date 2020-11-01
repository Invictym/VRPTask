package framework;

import framework.ui.Label;
import org.openqa.selenium.By;

import java.util.Date;

public abstract class BaseForm extends BaseEntity {

  public String title;
  protected Label titlePicture;

  public BaseForm(final By formLocator, final String formTitle) {
    long before = new Date().getTime();
    title = formTitle;
    titlePicture = new Label(formLocator, title);
  }

  public void switchWindow() {
    getBrowser().openNewWindow();
  }

}

