package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountManagementPage extends BasePage{
    private WebDriver webDriver;
    public AccountManagementPage(WebDriver webDriver){
        super(webDriver);
        this.webDriver=webDriver;
    }

    @FindBy(how=How.XPATH, using=".//p[@class='navigation-navbar-heading']")
    private WebElement title;

    public String getTitle(){
     return title.getText();
    }
}
