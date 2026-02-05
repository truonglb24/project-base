package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static WebDriver get() { return TL.get(); }

    public static void start(String browser, boolean headless) {
        browser = browser.toLowerCase().trim();

        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions o = new ChromeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--window-size=1920,1080");
                TL.set(new org.openqa.selenium.chrome.ChromeDriver(o));
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions o = new EdgeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--window-size=1920,1080");
                TL.set(new org.openqa.selenium.edge.EdgeDriver(o));
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions o = new FirefoxOptions();
                if (headless) o.addArguments("-headless");
                TL.set(new org.openqa.selenium.firefox.FirefoxDriver(o));
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        get().manage().deleteAllCookies();
        get().manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // explicit waits only
        get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        get().manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    public static void quit() {
        try {
            WebDriver d = get();
            if (d != null) d.quit();
        } finally {
            TL.remove();
        }
    }
}
