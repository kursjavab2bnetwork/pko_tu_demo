package pl.b2b.net.pko_tests.cfg;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.b2b.testfactory.annotations.TestFactoryMethod;



public class CfgTest {

    public static String websiteAddress;
    public static String excelFilePath;

    @Test(priority = 1)
    @Parameters({"driverPath"})
    @TestFactoryMethod(value = "Start", description = "Inicjalizacja środowiska testowego", group = "konfiguracja")
    public void setUp(String driverPath) {
        WebDriverCfg.Initialize(driverPath);
        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance());
    }
    @Test(priority = 2)
    @Parameters({"url","excelPath"})
    @TestFactoryMethod(value = "Otwórz stronę i podaj lokalizację pliku z danymi", description = "Otwórz stronę i podaj lokalizację pliku excel z danymi", group = "konfiguracja")
    public void openSite(String url, String excelPath) {
        websiteAddress = url;
        excelFilePath = excelPath;
        WebDriverCfg.getWebDriverInstance().get(websiteAddress);
        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance().getCurrentUrl());
    }

    @Test(priority = 3)
    @TestFactoryMethod(value = "Stop", description = "Zakoncz test", group = "konfiguracja")
    public void tearUp() {
        WebDriverCfg.quitWebDriverInstance();
        Assert.assertNull(WebDriverCfg.getWebDriverInstance());
    }
}
