package pl.b2b.net.pko_tests.apps.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.b2b.net.pko_tests.apps.page.TravelOptionMethods;
import pl.b2b.net.pko_tests.bases.BaseTest;
import pl.b2b.net.pko_tests.cfg.CfgTest;
import pl.b2b.net.pko_tests.cfg.WebDriverCfg;
import pl.b2b.net.pko_tests.utils.DataLoader;
import pl.b2b.testfactory.annotations.TestFactoryMethod;

import java.awt.*;
import java.io.IOException;

public class TravelOptionTest extends BaseTest {

    private TravelOptionMethods travelOptionMethods;

    @BeforeClass
    @Override
    public void init() {
        super.init();
        travelOptionMethods = new TravelOptionMethods(wait, driver);

    }


    @DataProvider(name = "TestData")
    public Object[][] getData() throws IOException {
        DataLoader dataLoader = new DataLoader();
        Object[][] tab = dataLoader.read(CfgTest.excelFilePath);
        return tab;
    }

    @Test(dataProvider = "TestData")
    @TestFactoryMethod(value = "Porownanie parametrów", description = "Porównanie danych na stronie z danymi z pliku zewnetrznego", group = "konfiguracja")
    public void yourJourneyTest(String direction, String destination, String dateOfDeparture, String dateOfReturn, String numberOfAdults,
                                String numberOfChildren, String standardProtection, String fullComfort, String prestigiousJourney) throws AWTException {
        travelOptionMethods.checkPageTitleAndLogo();
        travelOptionMethods.selectDirection(direction);
        travelOptionMethods.selectPurpose(destination);
        travelOptionMethods.typeDates(dateOfDeparture, dateOfReturn);
        travelOptionMethods.numberOfTravelers(numberOfAdults, numberOfChildren);
        travelOptionMethods.clickNextButton();
        Assert.assertEquals(standardProtection,travelOptionMethods.getPriceInStandardProtection());
        Assert.assertEquals(fullComfort,travelOptionMethods.getPriceInFullComfort());
        Assert.assertEquals(prestigiousJourney,travelOptionMethods.getPriceInPrestigiusJourney());

        WebDriverCfg.getWebDriverInstance().get(CfgTest.websiteAddress);
        Assert.assertNotNull(WebDriverCfg.getWebDriverInstance().getCurrentUrl());
    }


}


