package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private WebDriver webDriver;

    public BasePage(WebDriver webdriver) {
        this.webDriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    @FindBy(how = How.XPATH, using = ".//img[@class='img-responsive img-circle']")
    private WebElement headerLogo;

    public HomePage clickOnHeaderLogo() {
        headerLogo.click();
        return new HomePage(webDriver);
    }

    @FindBy(how = How.XPATH, using = ".//div[@id='navbar']/ul/li")
    private WebElement headerMenu;

    public BasePage selectItemFromHeaderMenu(String itemName) throws InterruptedException {
        Thread.sleep(5000);
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

    public WebElement moveTo(String xpath){
        Actions actions=new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath(xpath))).perform();
        return webDriver.findElement(By.xpath(xpath));
    }


}
