package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    @FindBy(how = How.XPATH, using = "//div[@class='panel-heading clearfix']/h3")
    private List<WebElement> titleOfVidgets;

    @FindBy(how = How.XPATH, using = ".//input[@name='orderNum']")
    private WebElement orderSearchField;

    @FindBy(how = How.XPATH, using = ".//input[@value='Search']")
    private WebElement searchButton;

    @FindBy(how = How.XPATH, using = ".//*[.='$Status']/preceding-sibling::*[1]/ancestor::a")
    private WebElement totalOrderSummaryButton;


    public List<String> getTitleOfVidgets() {
        List<String> titlesOfVidgets = new ArrayList<>();
        for (WebElement title : titleOfVidgets) {
            titlesOfVidgets.add(title.getText());
        }
        return titlesOfVidgets;
    }

    public OrderStatusPage searchForOrder(String orderNumber) {
        orderSearchField.sendKeys(orderNumber);
        searchButton.click();
        return new OrderStatusPage(webDriver);
    }

    public String getNumberOfOrdersWithStatus(String status) {
        waitVisibility(10, ".//*[.='"+status+"']/preceding-sibling::*[1]");
        return webDriver.findElement(By.xpath(".//*[.='"+status+"']/preceding-sibling::*[1]")).getText();
    }
    public OrderStatusPage openOrdersWithStatus(String status) {
        webDriver.findElement(By.xpath(".//*[.='"+status+"']/preceding-sibling::*[1]/ancestor::a")).click();
        return new OrderStatusPage(webDriver);
    }

}
