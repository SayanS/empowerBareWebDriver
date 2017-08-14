package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.customElements.Table;

import java.util.List;

public class InvoicePage extends BasePage{

    private WebDriver webDriver;

    public InvoicePage(WebDriver webDriver){
        super(webDriver);
        this.webDriver=webDriver;
    }


    @FindBy(how= How.XPATH, using = ".//table[@id='DataTables_Table_0']")
    private WebElement invoicesTable;

    public List<String> getAllColumnNamesOfInvoicesTable(){
        return (new Table(invoicesTable)).getAllColumnName();
    }



}
