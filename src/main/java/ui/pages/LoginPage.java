package ui.pages;

import config.EnvConfig;
import org.openqa.selenium.WebDriver;
import ui.locators.LoginPageUI;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver, Duration.ofSeconds(15));
    }

    public LoginPage open() {
        act.open(EnvConfig.get("baseUrl") + "/login");
        wait.visible(LoginPageUI.USERNAME);
        return this;
    }

    public LoginPage inputUsername(String u) {
        act.type(LoginPageUI.USERNAME, u);
        return this;
    }

    public LoginPage inputPassword(String p) {
        act.type(LoginPageUI.PASSWORD, p);
        return this;
    }

    public HomePage submitExpectSuccess() {
        act.click(LoginPageUI.SUBMIT);
        return new HomePage(driver).waitLoaded();
    }

    public LoginPage submitExpectFail() {
        act.click(LoginPageUI.SUBMIT);
        wait.visible(LoginPageUI.ERROR_BOX);
        return this;
    }

    public HomePage loginDefault() {
        return open()
                .inputUsername(EnvConfig.get("username"))
                .inputPassword(EnvConfig.get("password"))
                .submitExpectSuccess();
    }
}
