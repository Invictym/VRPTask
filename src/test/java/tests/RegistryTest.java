package tests;

import forms.IndexForm;
import forms.RegistryForm;
import framework.BaseTest;
import org.testng.annotations.Test;

public class RegistryTest extends BaseTest {

    @Test
    public void registryUser() {
        IndexForm mainForm = new IndexForm();
        mainForm.clickOnSignUp();
        mainForm.switchWindow();

        RegistryForm registryForm = new RegistryForm();
        registryForm.makeFirstStep("test", "test", "test");
        registryForm.makeSecondStep(RegistryForm.Employees.TWENTY, "test");
        registryForm.makeThirdStep("+375293331689", "test@test.com");
        System.out.println("t");
    }

    @Test
    public void registryUser2() {
        IndexForm mainForm = new IndexForm();
    }
}
