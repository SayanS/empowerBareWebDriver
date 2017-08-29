package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.customElements.PaginationPanel;
import pages.customElements.Table;

import java.util.List;

public class OrderStatusPage extends BasePage {
    WebDriver webDriver;

    protected OrderStatusPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    @FindBy(how = How.XPATH, using = ".//table[@id='DataTables_Table_0']")
    private WebElement ordersTable;

    @FindBy(how = How.XPATH, using = ".//*[@id='DataTables_Table_0_paginate']")
    private WebElement paginationPanel;

    public List<String> getColumnValuesOfOrdersTable(String columnName) {
        return (new Table(ordersTable)).getColumnValues(columnName);
    }

    public String getAmountOfDisplayedOrders() throws InterruptedException {
        Integer amountOfDisplayedOrders = (new Table(ordersTable)).getRowSize();
        if ( !(new PaginationPanel(paginationPanel)).getTotal().equals("")) {
            amountOfDisplayedOrders = (Integer.valueOf((new PaginationPanel(paginationPanel)).getTotal()) - 1) * 25;
            (new PaginationPanel(paginationPanel)).last();
            Thread.sleep(5000);
            return String.valueOf(amountOfDisplayedOrders + (new Table(ordersTable)).getRowSize());
        }
        return String.valueOf(amountOfDisplayedOrders);
    }
}
