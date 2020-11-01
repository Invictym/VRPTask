package tests;

import forms.*;
import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest {

    String user = "a.v.kalatskij-hukg@force.com";
    String password = "12345678a";
    String account = "test_Acc";
    String accountChange = "Test_change";

    @BeforeClass
    public void login() {
        LoginForm loginForm = new LoginForm();
        loginForm.login(user, password);
    }

    @Test
    public void createAccountTest() {
        MainUserForm mainUserForm = new MainUserForm();
        mainUserForm.openAccounts();

        AccountsListForm accountsListForm = new AccountsListForm();
        accountsListForm.addNewAccount();

        NewAccountForm newAccountForm = new NewAccountForm();
        newAccountForm.createAccount(account);

        AccountForm accountForm = new AccountForm();
        Assert.assertTrue(accountForm.isAccountPresent(account));
    }

    @Test
    public void editAccountTest() {
        AccountForm accountForm = new AccountForm();
        accountForm.editAccountName(accountChange);

        Assert.assertTrue(accountForm.isAccountPresent(accountChange));
    }
}
