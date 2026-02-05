package hooks;

import core.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot();
        attachPageSource();
        attachBrowserConsole();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            return ((TakesScreenshot) DriverManager.get()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Attachment(value = "Page Source", type = "text/html")
    public String attachPageSource() {
        try {
            return DriverManager.get().getPageSource();
        } catch (Exception e) {
            return "N/A: " + e.getMessage();
        }
    }

    @Attachment(value = "Browser Console", type = "text/plain")
    public String attachBrowserConsole() {
        try {
            LogEntries logs = DriverManager.get().manage().logs().get(LogType.BROWSER);
            StringBuilder sb = new StringBuilder();
            logs.forEach(e -> sb.append(e.getLevel()).append(" ").append(e.getMessage()).append("\n"));
            return sb.length() == 0 ? "(no console logs)" : sb.toString();
        } catch (Exception e) {
            return "N/A (browser console not available): " + e.getMessage();
        }
    }
}
