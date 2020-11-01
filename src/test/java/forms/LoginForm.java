package forms;

import framework.BaseForm;
import framework.ui.Button;
import framework.ui.Input;
import org.openqa.selenium.By;

public class LoginForm extends BaseForm {

    private Input emailInp = new Input(By.id("username"), "Email");
    private Input passwordInp = new Input(By.id("password"), "Password");
    private Button loginBtn = new Button(By.id("Login"), "Login button");

    public LoginForm() {
        super(By.id("login_form"), "Login form");
    }

    public void login(String user, String password) {
        emailInp.sendKey(user);
        passwordInp.sendKey(password);
        loginBtn.click();
    }
}
