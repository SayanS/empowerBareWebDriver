package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.customElements.Table;

import java.util.List;

public class ProductSearchResultPage extends BasePage{
    private WebDriver webDriver;

    public ProductSearchResultPage(WebDriver webDriver){
        super(webDriver);
        this.webDriver=webDriver;
    }

    @FindBy(how= How.XPATH, using = ".//table[@id='DataTables_Table_0']")
    private WebElement productsTable;

    public List<String> getColumnValues(String columnName){
        return (new Table(productsTable)).getColumnValues(columnName);
    }



}
