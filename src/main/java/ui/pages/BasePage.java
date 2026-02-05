package ui.pages;

import core.Waiter;
import org.openqa.selenium.WebDriver;
import ui.actions.ElementActions;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final Waiter wait;
    protected final ElementActions act;

    protected BasePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new Waiter(driver, timeout);
        this.act = new ElementActions(driver, wait);
    }

    public String currentUrl() { return driver.getCurrentUrl(); }
    public String title() { return driver.getTitle(); }
}
