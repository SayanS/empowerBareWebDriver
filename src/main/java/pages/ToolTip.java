package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ToolTip extends BasePage{

    @FindBy(how=How.XPATH, using=".//div[@class='introjs-tooltip']")
    WebElement toolTip;

    public ToolTip(WebDriver webDriver){
        super(webDriver);
    }

    public HomePage skip(){
        toolTip.findElement(By.xpath(".//a[.='Skip']")).click();
        return new HomePage(getWebDriver());
    }


}
