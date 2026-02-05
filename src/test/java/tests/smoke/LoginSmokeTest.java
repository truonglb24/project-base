package tests.smoke;

import hooks.BaseTest;
import hooks.Flaky;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.LoginPage;

public class LoginSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void login_success() {
        var home = new LoginPage(driver()).loginDefault();
        Assert.assertTrue(home.isAt(), "Should land on home/dashboard after login");
    }

    @Flaky(retries = 1)
    @Test(groups = {"smoke"})
    public void login_invalid_should_show_error() {
        new LoginPage(driver())
                .open()
                .inputUsername("wrong_user")
                .inputPassword("wrong_pass")
                .submitExpectFail();

        Assert.assertTrue(driver().getCurrentUrl().contains("/login"));
    }
}
