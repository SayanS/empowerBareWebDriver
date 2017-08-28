package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriver webDriver;

    protected BasePage(WebDriver webdriver) {
        this.webDriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    @FindBy(how = How.XPATH, using = ".//img[@class='img-responsive img-circle']")
    private WebElement headerLogo;

    public HomePage clickOnHeaderLogo() {
        headerLogo.click();
        try {
            waitVisabilityBy(".//div[@class='introjs-tooltip']//a[.='Skip']");
            webDriver.findElement(By.xpath(".//div[@class='introjs-tooltip']//a[.='Skip']")).click();
        }catch(Exception e){
            webDriver.findElement(By.xpath(".//div[@class='introjs-tooltip']//a[.='Skip']")).click();
        }
        waitSpinnerInvisability(10);
        return new HomePage(webDriver);
    }

    @FindBy(how = How.XPATH, using = ".//div[@id='navbar']/ul/li")
    private WebElement headerMenu;

    protected WebDriver getWebDriver() {
        return this.webDriver;
    }

    public BasePage selectItemFromHeaderMenu(String itemName) throws InterruptedException {
        Thread.sleep(10000);
        moveTo("//*[contains(text(),'$itemName')]".replace("$itemName", itemName)).click();
        switch (itemName) {
            case "Home":
                return new HomePage(webDriver);
            case "Invoice":
                return new InvoicePage(webDriver);
            default:
                return null;
        }
    }


    protected WebElement moveTo(String xpath) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath(xpath))).perform();
        return webDriver.findElement(By.xpath(xpath));
    }

    protected void waitForInvisability(WebElement webElement) {
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected void waitVisabilityBy(String xpath) {
        (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected void waitSpinnerInvisability(Integer second){
        //id="FullScreenProgressIndicatorModalDialog"
        (new WebDriverWait(webDriver, second)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@id='progress']")));
    }

}
