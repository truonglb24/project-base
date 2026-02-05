package ui.pages;

import org.openqa.selenium.WebDriver;
import ui.locators.HomePageUI;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver, Duration.ofSeconds(20));
    }

    public HomePage waitLoaded() {
        wait.visible(HomePageUI.APP_HEADER);
        return this;
    }

    public boolean isAt() {
        String url = currentUrl().toLowerCase();
        return url.contains("home") || url.contains("dashboard");
    }
}
