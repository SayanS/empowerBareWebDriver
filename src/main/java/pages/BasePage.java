package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage{
    private WebDriver webDriver;
    public BasePage(WebDriver webdriver){
        this.webDriver=webdriver;
        PageFactory.initElements(webdriver,this);
    }



}
