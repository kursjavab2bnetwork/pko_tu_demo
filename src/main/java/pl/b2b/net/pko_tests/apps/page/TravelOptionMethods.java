package pl.b2b.net.pko_tests.apps.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pl.b2b.net.pko_tests.bases.BasePage;
import pl.b2b.net.pko_tests.cfg.WebDriverCfg;

import java.awt.*;

public class TravelOptionMethods extends BasePage {

    @FindBy(xpath = TravelOptionData.STANDARD_PROTECTION)
    private WebElement standardProtection;
    @FindBy(xpath = TravelOptionData.FULL_COMFORT)
    private WebElement fullComfort;
    @FindBy(xpath = TravelOptionData.PRESTIGIOUS_JOURNEY)
    private WebElement prestigiousJourney;

    private WebDriverWait webDriverWait;
    private WebDriver webDriver = WebDriverCfg.getWebDriverInstance();

    public TravelOptionMethods(WebDriverWait webDriverWait, WebDriver webDriver) {
        super(webDriverWait, webDriver);
    }

    @FindBy(xpath = TravelOptionData.DESTINATION_EUROPE_TXT)
    protected WebElement destinationEurope;

    @FindBy(xpath = TravelOptionData.DESTINATION_WORLD_TXT)
    protected WebElement destinationWorld;

    @FindBy(xpath = TravelOptionData.REST_XPATH)
    protected WebElement purposeRest;

    @FindBy(xpath = TravelOptionData.SKIING_XPATH)
    protected WebElement purposeSkiing;

    @FindBy(xpath = TravelOptionData.HIGH_RISK_SPORTS_XPATH)
    protected WebElement purposeHighRisk;

    @FindBy(xpath = TravelOptionData.WORK_XPATH)
    protected WebElement purposeWork;

    @FindBy(xpath = TravelOptionData.PHYSICAL_WORK_XPATH)
    protected WebElement purposePhysicalWork;

    @FindBy(id = TravelOptionData.DATE_START_ID)
    protected WebElement startDate;

    @FindBy(id = TravelOptionData.DATE_END_ID)
    protected WebElement endDate;

    @FindBy(xpath = TravelOptionData.TRAVELLERS_ONE_PERSON_XPATH)
    protected WebElement travelers1person;

    @FindBy(xpath = TravelOptionData.TRAVELLERS_MORE_PEOPLE_XPATH)
    protected WebElement travelMorePeople;

    @FindBy(id = TravelOptionData.NUMBER_OF_ADULT_ID)
    protected WebElement numberOfAdultField;

    @FindBy(id = TravelOptionData.NUMBER_CHILDREN_ID)
    protected WebElement numberOfChildrenField;

    @FindBy(id = TravelOptionData.NEXT_BUTTON_ID)
    protected WebElement nextButton;


    public void checkPageTitleAndLogo(){
        Assert.assertNotNull(TravelOptionData.LOGO_XPATH, "Logo ID should be specified");
        Assert.assertNotNull(TravelOptionData.TITLE_XPATH, "The title does not match");
    }

    public void selectDirection(String direction) {
        switch (direction) {
            case "Europa":
                click(destinationEurope);
                break;
            case "Świat":
                click(destinationWorld);
                break;
        }
        checkIfSuccess(1);
    }

    public void selectPurpose(String purpose) {
        switch (purpose) {
            case "Wypoczynek":
                click(purposeRest);
                break;
            case "Narciarstwo":
                click(purposeSkiing);
                break;
            case "Sporty wysokiego ryzyka":
                click(purposeHighRisk);
                break;
            case "Praca":
                click(purposeWork);
                break;
            case "Praca fizyczna":
                click(purposePhysicalWork);
                break;
        }
        checkIfSuccess(2);
    }

    public void typeDates(String dateOfDeparture, String dateOfReturn) throws AWTException {
        typeDateRobot(startDate, dateOfDeparture);
        typeDateRobot(endDate, dateOfReturn);
        checkIfSuccess(3);
    }

    public void clickNextButton() {
        click(nextButton);
    }

    public void numberOfTravelers(String numberOfAdults, String numberOfChildren) {

        if (numberOfAdults.equals("1") && numberOfChildren.equals("0")) {
            click(travelers1person);
            checkIfSuccess(5);
        } else {
            click(travelMorePeople);
            type(numberOfAdultField, Keys.BACK_SPACE);
            type(numberOfAdultField, numberOfAdults);
            type(numberOfChildrenField, Keys.BACK_SPACE);
            type(numberOfChildrenField, numberOfChildren);
            checkIfSuccess(6);
        }
    }

    private void checkIfSuccess(int expectedNumber){
        int locatorElementSize = webDriver.findElements(By.cssSelector(TravelOptionData.SUCCESS_CSS)).size();
        Assert.assertEquals(locatorElementSize,expectedNumber);
    }

    public String getPriceInStandardProtection() {
        waitForWebElement(standardProtection);
        return standardProtection.getText();
    }

    public String getPriceInFullComfort() {
        waitForWebElement(fullComfort);
        return fullComfort.getText();
    }

    public String getPriceInPrestigiusJourney() {
        waitForWebElement(standardProtection);
        return prestigiousJourney.getText();
    }
}
