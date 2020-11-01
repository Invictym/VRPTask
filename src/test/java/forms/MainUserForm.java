package forms;

import framework.BaseForm;
import framework.ui.Label;
import org.openqa.selenium.By;

public class MainUserForm extends BaseForm {

    private Label accountLabel = new Label(By.xpath("//one-app-nav-bar-item-root[@data-id=\"Account\"]"), "Account");

    public MainUserForm() {
        super(By.xpath("//div[@class=\"slds-context-bar\"]"), "Main User form");
    }

    public void openAccounts() {
        accountLabel.click();
    }
}
