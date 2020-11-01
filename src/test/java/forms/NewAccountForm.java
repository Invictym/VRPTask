package forms;

import framework.BaseForm;
import framework.ui.Button;
import framework.ui.Input;
import org.openqa.selenium.By;

public class NewAccountForm extends BaseForm {

    private Input accountNameInp = new Input(By.xpath("//span[text()=\"Account Name\"]/ancestor::div[@role=\"listitem\"]//input"), "Account name");
    private Button saveBtn = new Button(By.xpath("//button[@title=\"Save\"]"), "Save");

    public NewAccountForm() {
        super(By.xpath("//div[contains(@class, \"modal-container\")]"), "New account form");
    }

    public void createAccount(String name) {
        accountNameInp.sendKey(name);
        saveBtn.click();
    }

}
