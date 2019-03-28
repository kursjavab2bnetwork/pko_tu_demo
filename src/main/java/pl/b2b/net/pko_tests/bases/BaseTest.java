package pl.b2b.net.pko_tests.bases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pl.b2b.net.pko_tests.cfg.WebDriverCfg;
import pl.b2b.testfactory.TestFactoryUtils;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    protected void init() {
        driver = WebDriverCfg.getWebDriverInstance();
        wait = new WebDriverWait(driver, 40);
    }

    @AfterMethod
    protected void failure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TestFactoryUtils.addScreenShotFromSeleniumDriver(driver);
        }
    }
}
