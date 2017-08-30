import org.junit.Assert;
import org.testng.annotations.Test;
import pages.AccountManagementPage;
import pages.HomePage;
import pages.InvoicePage;
import pages.OrderStatusPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmokeTests extends BaseTests {
    HomePage homePage;
    InvoicePage invoicePage;
    OrderStatusPage orderStatusPage;
    AccountManagementPage accountManagementPage;

    @Test(groups = {"run"})
    public void isAccountInFavorite(){
        accountManagementPage=getBasePage().openAccountManagementPage();
        Assert.assertTrue(accountManagementPage.getColumnValuesOfFavoritesAccountsTable("Account No.").contains("2244410"));
    }

    @Test(groups = {"run"})
    public void isDisplayedColumnNamesOfAccountTable() {
        List<String> expectedColumnNamesOfAccountTable = Arrays.asList("Account No.", "Account Name", "City", "State", "Sales Org.", "Sales Channel", "", "");
        accountManagementPage=getBasePage().openAccountManagementPage();
        Assert.assertEquals(expectedColumnNamesOfAccountTable, accountManagementPage.getAllColumsNameOfFavoritesAccountsTable());
        Assert.assertTrue(accountManagementPage.getAllRowsValuesOfFavoritesAccountsTable() != null);
    }

    @Test(groups = {"run"})
    public void isInvoiceExist() throws InterruptedException {
        List<String> expectedInvoiceTableHeaderColumnsName = Arrays.asList("Invoice No.", "PO No.", "GE Order No.", "Invoice Date", "Original Amount", "Current Balance", "Invoice Status", "Due Date", "Cash Discount");
        homePage = getBasePage().clickOnHeaderLogo();
        invoicePage=(InvoicePage) homePage.selectItemFromHeaderMenu("Invoice");
        invoicePage.getAllColumnNamesOfInvoicesTable();

        Assert.assertTrue(invoicePage.getColumnValuesOfInvoicesTable("Invoice No.").toString()+ " isn't contains " + "501303480",
                          invoicePage.getColumnValuesOfInvoicesTable("Invoice No.").contains("501303480"));
    }

    @Test(groups = {"run"})
    public void searchForOrder() {
        String orderNumber="150598320";
        List<String> searchResult=new ArrayList<>();

        homePage=getBasePage().clickOnHeaderLogo();
        orderStatusPage =homePage.searchForOrder(orderNumber);
        searchResult= orderStatusPage.getColumnValuesOfOrdersTable("GE Order No.");
        Assert.assertTrue("Search result contained more then 1 result - "+searchResult.toString(), searchResult.size()==1);
        Assert.assertEquals(orderStatusPage.getColumnValuesOfOrdersTable("GE Order No.").toString() +" isn't equal to " +orderNumber,searchResult.get(0), orderNumber);
    }

    @Test(groups = {"run"})
    public void checkTotalsOnOrderStatusVidget() throws InterruptedException {
        String[] statuses=new String[]{"Open","Cancelled","Shipped","On Hold"};
        String expectedOpenedOrders;
        String displayedOpenedOrders;
        for(String status:statuses) {
            homePage = getBasePage().clickOnHeaderLogo();
            expectedOpenedOrders = homePage.getNumberOfOrdersWithStatus(status);
            orderStatusPage = homePage.openOrdersWithStatus(status);
            displayedOpenedOrders=orderStatusPage.getAmountOfDisplayedOrders();
            Assert.assertEquals("Expected amount of order with status- " + expectedOpenedOrders + "but on result page - " + displayedOpenedOrders, expectedOpenedOrders, displayedOpenedOrders);
        }
    }

}
