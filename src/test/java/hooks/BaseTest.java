package hooks;

import config.RunConfig;
import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUp(@Optional String browserFromSuite,
                      @Optional String headlessFromSuite) {

        String browser = (browserFromSuite != null && !browserFromSuite.isBlank())
                ? browserFromSuite
                : RunConfig.browser();

        boolean headless = (headlessFromSuite != null && !headlessFromSuite.isBlank())
                ? Boolean.parseBoolean(headlessFromSuite)
                : RunConfig.headless();

        DriverManager.start(browser, headless);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }

    protected WebDriver driver() {
        return DriverManager.get();
    }
}
