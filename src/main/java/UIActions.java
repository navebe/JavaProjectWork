import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIActions extends Base {

    @Step
    public static void click(WebElement element) {
        click(element, 5);
    }

    @Step
    public static void click(WebElement element, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Step
    public static void sendKeys(WebElement element, String inputText) {
        sendKeys(element, inputText, 5);
    }

    @Step
    public static void sendKeys(WebElement element, String inputText, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(inputText);
    }
}
