package forms;

import framework.BaseForm;
import framework.ui.Button;
import framework.ui.Input;
import framework.ui.Label;
import org.openqa.selenium.By;

public class AccountForm extends BaseForm {

    private String accountLocator = "//div[text()=\"Account\"]/parent::h1//span[@data-aura-class=\"uiOutputText\" and text() =\"%s\"]";
    private Button editAccountNameBtn = new Button(By.xpath("//button[@title=\"Edit Account Name\"]"), "Edit account name");
    private Input nameInp = new Input(By.xpath("//input[@name=\"Name\"]"), "Name input");
    private Button saveBtn = new Button(By.xpath("//button[@title=\"Save\"]"), "Save");

    public AccountForm() {
        super(By.id("brandBand_2"), "Account edit form");
    }

    public boolean isAccountPresent(String name) {
        Label account = new Label(By.xpath(String.format(accountLocator, name)), "Account " + name);
        return account.elementIsDisplayed();
    }

    public void editAccountName(String name) {
        editAccountNameBtn.click();
        nameInp.sendKeyClear(name);
        saveBtn.click();
    }
}
