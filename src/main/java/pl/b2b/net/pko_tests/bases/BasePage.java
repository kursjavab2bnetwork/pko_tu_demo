package pl.b2b.net.pko_tests.bases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.b2b.net.pko_tests.utils.WebElementUtils;

public class BasePage extends WebElementUtils {

    protected WebDriver webDriver;

    protected BasePage(WebDriverWait wait, WebDriver driver) {
        super(wait);
        this.webDriver = driver;
        PageFactory.initElements(driver,this);
    }
}
