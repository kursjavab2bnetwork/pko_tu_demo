package pl.b2b.net.pko_tests.cfg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

public class WebDriverCfg {

    private static WebDriver webDriverInstance = null;
    public static void Initialize(String driverPath) {
        if (webDriverInstance == null) {
            System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, driverPath);
            webDriverInstance = new FirefoxDriver();
            webDriverInstance.manage().window().maximize();
        }
    }

    public static WebDriver getWebDriverInstance() {
        return webDriverInstance;
    }

    public static void quitWebDriverInstance() {
        webDriverInstance.quit();
        webDriverInstance = null;
    }
}
