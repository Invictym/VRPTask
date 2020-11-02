package tests;

import forms.*;
import framework.BaseTest;
import framework.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest {

    @Parameters({"user", "password"})
    @BeforeClass
    public void login(String user, String password) {
        Log.info("Login by user " + user);
        LoginForm loginForm = new LoginForm();
        loginForm.login(user, password);
    }

    @Parameters("account_name")
    @Test
    public void createAccountTest(String account) {
        Log.info("Open accounts");
        MainUserForm mainUserForm = new MainUserForm();
        mainUserForm.openAccounts();

        Log.info("Create new");
        AccountsListForm accountsListForm = new AccountsListForm();
        accountsListForm.addNewAccount();

        NewAccountForm newAccountForm = new NewAccountForm();
        newAccountForm.createAccount(account);

        Log.info("Verify account");
        AccountForm accountForm = new AccountForm();
        Assert.assertTrue(accountForm.isAccountPresent(account));
    }

    @Parameters("changed_account_name")
    @Test(dependsOnMethods = "createAccountTest")
    public void editAccountTest(String accountChange) {
        Log.info("Edit account");
        AccountForm accountForm = new AccountForm();
        accountForm.editAccountName(accountChange);

        Log.info("Verify account");
        Assert.assertTrue(accountForm.isAccountPresent(accountChange));
    }
}
