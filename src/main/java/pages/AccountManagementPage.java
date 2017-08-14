package pages;


import models.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.customElements.Table;

import java.util.ArrayList;
import java.util.List;

public class AccountManagementPage extends BasePage {
    private WebDriver webDriver;

    public AccountManagementPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    @FindBy(how = How.XPATH, using = ".//p[@class='navigation-navbar-heading']")
    private WebElement title;

    @FindBy(how = How.XPATH, using = "//table[@id='favoritesTable']")
    private WebElement accountsTable;

    public String getTitle() {
        return title.getText();
    }

    public String getColumnNameOfFavoritesAccountsTable(Integer index) {
        return (new Table(accountsTable)).getColumnName(index);
    }

    public List<String> getAllColumsNameOfFavoritesAccountsTable() {
        return (new Table(accountsTable)).getAllColumnName();
    }

    public List<Account> getAllRowsValues() {
        List<Account> allRowsValues = new ArrayList<>();
        Account account = new Account();
        Table table = new Table(accountsTable);
        for (int i = 1; i <= table.getRowSize(); i++) {
            account.setAccountNumber(table.getRowByLine(i).get(0));
            account.setAccountName(table.getRowByLine(i).get(1));
            account.setCity(table.getRowByLine(i).get(2));
            account.setState(table.getRowByLine(i).get(3));
            account.setSalesOrg(table.getRowByLine(i).get(4));
            account.setSalesChannel(table.getRowByLine(i).get(5));
            allRowsValues.add(new Account(account));
        }
        return allRowsValues;
    }

}

