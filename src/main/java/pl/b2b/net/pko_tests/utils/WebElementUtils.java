package pl.b2b.net.pko_tests.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class WebElementUtils {

    protected WebDriverWait wait;
    protected WebDriver driver;
    protected WebElementUtils(WebDriverWait wait) {
        this.wait = wait;
    }

    protected void type(WebElement webElement, String text) {
        waitForWebElement(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void type(WebElement webElement, Keys keys) {
        waitForWebElement(webElement);
        webElement.clear();
        webElement.sendKeys(keys);
    }

    protected void typeDateRobot(WebElement webElement, String text) throws AWTException {
        waitForWebElement(webElement);
        webElement.click();
        webElement.clear();
        pasteRobot(webElement, text);
        webElement.sendKeys(Keys.ENTER);
    }

    protected void pasteRobot(WebElement element, String text) throws AWTException {
        Robot robot = new Robot();

        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

//        if (verifyEnteredData)
//            Assert.assertEquals(getElementsValue(element), text, "Text wasn't printed");
    }

    private void setAttribute(WebElement element, String attName, String attValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                element, attName, attValue);
    }

    protected void click(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    protected WebElement getWebElement(WebElement webElement) {
        waitForWebElement(webElement);
        return webElement;
    }

    protected void waitForWebElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected String getText(WebElement webElement) {
        waitForWebElement(webElement);
        return webElement.getAttribute("innerHTML");
    }

    protected boolean isDisplayed(WebElement webElement) {
        waitForWebElement(webElement);
        return webElement.isDisplayed();
    }

    protected void waitUntilInvisibilityOfElement(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
}