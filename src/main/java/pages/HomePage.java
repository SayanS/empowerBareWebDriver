package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    private WebDriver webDriver;
    public HomePage(WebDriver webDriver){
        super(webDriver);
        this.webDriver=webDriver;
    }

    @FindBy(how=How.XPATH, using = "//div[@class='panel-heading clearfix']/h3")
    private List<WebElement> titleOfVidgets;

    public List<String> getTitleOfVidgets(){
        List<String> titlesOfVidgets= new ArrayList<>();
        for(WebElement title:titleOfVidgets){
            titlesOfVidgets.add(title.getText());
        }
        return titlesOfVidgets;
    }

}
