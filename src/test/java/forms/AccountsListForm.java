package forms;

import framework.BaseForm;
import framework.ui.Button;
import org.openqa.selenium.By;

public class AccountsListForm extends BaseForm {

    private Button newAccountBtn = new Button(By.xpath("//ul[contains(@data-aura-class, \"oneActionsRibbon\")]//a[@title=\"New\"]"), "New account");


    public AccountsListForm() {
        super(By.id("sfdc-splitview-content"), "Accounts list");
    }

    public void addNewAccount() {
        newAccountBtn.click();
    }

}
