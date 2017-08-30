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

    @FindBy(how = How.XPATH, using = ".//div[@id='navbar']/ul/li")
    private WebElement headerMenu;

    @FindBy(how = How.XPATH, using = ".//ul[@class='top-navbar-account-info']")
    private WebElement navbarAccountDropDown;

    @FindBy(how=How.XPATH, using = ".//input[@name='product']")
    private WebElement searchField;

    public HomePage clickOnHeaderLogo() {
        waitVisibility(10, headerLogo);
        moveTo(headerLogo);
        headerLogo.click();
        try {
            webDriver.findElement(By.xpath(".//div[@class='introjs-tooltip']//a[.='Skip']")).click();
        }catch(Exception e){
        }
        waitSpinnerInvisability(10);
        return new HomePage(webDriver);
    }

    public AccountManagementPage openAccountManagementPage(){
        if(webDriver.getCurrentUrl().contains("my-account/manage-accounts")){
            return new AccountManagementPage(webDriver);
        }
        waitVisibility(15,".//ul[@class='top-navbar-account-info']");
        moveTo(navbarAccountDropDown).click();
        moveTo(".//a[.='Change Account']").click();
        return new AccountManagementPage(webDriver);
    }

    public ProductSearchResultPage searchFor(String value){
        searchField.sendKeys(value);
        moveTo(".//a[@id='searchProductBut']").click();
        return new ProductSearchResultPage(webDriver);
    }

    protected WebDriver getWebDriver() {
        return this.webDriver;
    }

    public BasePage selectItemFromHeaderMenu(String itemName) throws InterruptedException {
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

    protected WebElement moveTo(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
        return webElement;
    }

    protected void waitForInvisibility(Integer second, WebElement webElement) {
        (new WebDriverWait(webDriver, second)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected void waitVisibility(Integer second, String xpath) {
        (new WebDriverWait(webDriver, second)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected void waitVisibility(Integer second, WebElement webElement) {
        (new WebDriverWait(webDriver, second)).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitSpinnerInvisability(Integer second){
        //id="FullScreenProgressIndicatorModalDialog"
        (new WebDriverWait(webDriver, second)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@id='progress']")));
    }


}
