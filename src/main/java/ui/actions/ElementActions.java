package ui.actions;

import core.Waiter;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;

public class ElementActions {
    private final WebDriver driver;
    private final Waiter wait;

    public ElementActions(WebDriver driver, Waiter wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Open URL: {url}")
    public void open(String url) {
        driver.get(url);
    }

    @Step("Click: {by}")
    public void click(By by) {
        wait.clickable(by).click();
    }

    @Step("Type '{text}' into: {by}")
    public void type(By by, String text) {
        WebElement el = wait.visible(by);
        el.clear();
        el.sendKeys(text);
    }

    @Step("Get text from: {by}")
    public String getText(By by) {
        return wait.visible(by).getText();
    }

    @Step("Is displayed: {by}")
    public boolean isDisplayed(By by) {
        try {
            return wait.visible(by).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Scroll into view: {by}")
    public void scrollIntoView(By by) {
        WebElement el = wait.visible(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    @Step("JS click: {by}")
    public void jsClick(By by) {
        WebElement el = wait.visible(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }
}
