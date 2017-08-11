package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{
    private WebDriver webDriver;
    public LoginPage(WebDriver webDriver){
        super(webDriver);
        this.webDriver=webDriver;
    }

    @FindBy(how= How.XPATH, using=".//*[@id='userId']")
    private WebElement userId;

    @FindBy(how= How.XPATH, using=".//*[@id='password']")
    private WebElement password;

    @FindBy(how= How.XPATH, using="//button[@type='submit']")
    private WebElement signInButton;

    public AccountManagementPage login(String userId, String password){
        this.userId.sendKeys(userId);
        this.password.sendKeys(password);
        this.signInButton.click();
        return new AccountManagementPage(webDriver);
    }


}
