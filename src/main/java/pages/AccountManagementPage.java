package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.customElements.Table;

public class AccountManagementPage extends BasePage{
    private WebDriver webDriver;
    private Table table;
    public AccountManagementPage(WebDriver webDriver){
        super(webDriver);
        this.webDriver=webDriver;
    }

    @FindBy(how=How.XPATH, using=".//p[@class='navigation-navbar-heading']")
    private WebElement title;

    @FindBy(how = How.XPATH, using = "//table[@id='favoritesTable']")
    private WebElement accountsTable;

    public String getTitle(){
     return title.getText();
    }

    public String getColumnNameOfFavoritesAccountsTable(Integer index){
        return (new Table(accountsTable)).getColumnName(index);
    }
}
