package pl.b2b.net.pko_tests.cfg;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.b2b.testfactory.annotations.TestFactoryMethod;



public class CfgTest {

    public static String websiteAddress;
    public static String excelFilePath;

    @Test(priority = 1)
    @Parameters({"driverPath","url","excelPath"})
    @TestFactoryMethod(value = "start", description = "inicjalizacja srodowiska testowego", group = "konfiguracja")
    public void setUp(String driverPath,String url,String excelPath) {
        websiteAddress = url;
        excelFilePath = excelPath;
        WebDriverCfg.Initialize(driverPath);
        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance());
    }
    @Test(priority = 2)
    @TestFactoryMethod(value = "otworz strone", description = "przejdz do strony, ktorej adres podany zostal w parametrze", group = "konfiguracja")
    @Parameters({"url"})
    public void openSite(String url) {
        WebDriverCfg.getWebDriverInstance().get(url);
        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance().getCurrentUrl());
    }

    @Test(priority = 3)
    @TestFactoryMethod(value = "stop", description = "zakoncz test", group = "konfiguracja")
    public void tearUp() {
        WebDriverCfg.quitWebDriverInstance();
        Assert.assertNull(WebDriverCfg.getWebDriverInstance());
    }
}
