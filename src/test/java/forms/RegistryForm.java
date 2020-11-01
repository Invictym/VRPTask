package forms;

import framework.entity.IDropBoxItem;
import framework.ui.Button;
import framework.ui.CheckBox;
import framework.ui.DropBox;
import framework.ui.Input;
import org.openqa.selenium.By;

public class RegistryForm {

    private final Input firstNameInp = new Input(By.xpath("//input[contains(@id, 'UserFirstName')]"), "User first name");
    private final Input lastNameInp = new Input(By.xpath("//input[contains(@id, 'UserLastName')]"), "User first name");
    private final Input jobTitleInp = new Input(By.xpath("//input[contains(@id, 'UserTitle')]"), "User first name");
    private final Button nextBtn = new Button(By.xpath("//form[contains(@id, 'signup_form')]//a[@role='button' and @data-page-cntrl='next']"), "Next");
    private final DropBox employeesDt = new DropBox(By.xpath("//select[contains(@id, 'CompanyEmployees')]"), "User first name");
    private final Input companyInp = new Input(By.xpath("//input[contains(@id, 'CompanyName')]"), "User first name");
    private final Input phoneInp = new Input(By.xpath("//input[contains(@id, 'UserPhone')]"), "User first name");
    private final Input emailInp = new Input(By.xpath("//input[contains(@id, 'UserEmail')]"), "User first name");
    private final CheckBox agreementCheckBox = new CheckBox(By.xpath("//input[contains(@id, 'SubscriptionAgreement')]/following-sibling::div[@class='checkbox-ui']"), "Agreement");
    private final Button submitBtn = new Button(By.xpath("//button[@type='submit']"), "Submit");

    public void makeFirstStep(final String firstName, final String lastName, final String jobTitle) {
        firstNameInp.sendKey(firstName);
        lastNameInp.sendKey(lastName);
        jobTitleInp.sendKey(jobTitle);
        nextBtn.click();
    }

    public void makeSecondStep(Employees select, final String companyName) {
        employeesDt.selectItem(select);
        companyInp.sendKey(companyName);
        nextBtn.click();
    }

    public void makeThirdStep(String phone, String email) {
        phoneInp.sendKey(phone);
        emailInp.sendKey(email);
        agreementCheckBox.click();
        submitBtn.click();
    }

    public enum Employees implements IDropBoxItem {
        TWENTY("10"), TWO_HUNDREDS("100"), TEN_THOUSANDS("500"), MORE_THAN_TEN_THOUSANDS("15000");

        private String value;

        Employees(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }
}
