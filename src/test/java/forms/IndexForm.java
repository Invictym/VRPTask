package forms;

import framework.BaseForm;
import framework.ui.Button;
import org.openqa.selenium.By;

public class IndexForm extends BaseForm {

    private final Button signupBtn = new Button(By.xpath("//a[contains(@href, 'signup')]/parent::div"), "Sign up button");
    private static final By form = By.id("marketing");

    public IndexForm() {
        super(form, "Marketing form");
        getBrowser().switchFrame(form);
    }

    public void clickOnSignUp() {
        signupBtn.click();
    }

}
