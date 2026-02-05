package ui.locators;

import org.openqa.selenium.By;

public class LoginPageUI {
    private LoginPageUI() {}
    public static final By USERNAME = By.id("username");
    public static final By PASSWORD = By.id("password");
    public static final By SUBMIT = By.cssSelector("button[type='submit']");
    public static final By ERROR_BOX = By.cssSelector("[data-testid='login-error']");
}
