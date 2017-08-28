import org.junit.Assert;
import org.testng.annotations.Test;
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

//    @Test
//    public void isAccountInFavorite(){
//        Assert.assertTrue(getAccountManagementPage().getColumnValuesOfFavoritesAccountsTable("Account No.").contains("2244410"));
//    }

//    @Test
//    public void isDisplayedColumnNamesOfAccountTable() {
//        List<String> expectedColumnNamesOfAccountTable = Arrays.asList("Account No.", "Account Name", "City", "State", "Sales Org.", "Sales Channel", "", "");
//        Assert.assertEquals(expectedColumnNamesOfAccountTable, getAccountManagementPage().getAllColumsNameOfFavoritesAccountsTable());
//        Assert.assertTrue(getAccountManagementPage().getAllRowsValuesOfFavoritesAccountsTable() != null);
//    }

    @Test
    public void isInvoiceExist() throws InterruptedException {
        List<String> expectedInvoiceTableHeaderColumnsName = Arrays.asList("Invoice No.", "PO No.", "GE Order No.", "Invoice Date", "Original Amount", "Current Balance", "Invoice Status", "Due Date", "Cash Discount");
        homePage = getAccountManagementPage().clickOnHeaderLogo();
        invoicePage=(InvoicePage) homePage.selectItemFromHeaderMenu("Invoice");
        invoicePage.getAllColumnNamesOfInvoicesTable();

        Assert.assertTrue(invoicePage.getColumnValuesOfInvoicesTable("Invoice No.").toString()+ " isn't contains " + "501303480",
                          invoicePage.getColumnValuesOfInvoicesTable("Invoice No.").contains("501303480"));
    }

    @Test
    public void searchForOrder() {
        String orderNumber="150598320";
        List<String> searchResult=new ArrayList<>();

        homePage=getBasePage().clickOnHeaderLogo();
        orderStatusPage =homePage.searchForOrder(orderNumber);
        searchResult= orderStatusPage.getColumnValuesOfOrdersTable("GE Order No.");
        Assert.assertTrue("Search result contained more then 1 result - "+searchResult.toString(), searchResult.size()==1);
        Assert.assertEquals(orderStatusPage.getColumnValuesOfOrdersTable("GE Order No.").toString() +" isn't equal to " +orderNumber,searchResult.get(0), orderNumber);
    }

    @Test
    public void checkAmountOfOpenedOrders() throws InterruptedException {
        String expectedOpenedOrders;
        homePage=getBasePage().clickOnHeaderLogo();
        expectedOpenedOrders=homePage.getNumberOfOrdersWithStatus("Open");
        orderStatusPage=homePage.openOrdersWithStatus("Open");
        Assert.assertEquals("Expected amount - "+expectedOpenedOrders+"but on result page - "+orderStatusPage.getAmountOfDisplayedOrders(),expectedOpenedOrders,orderStatusPage.getAmountOfDisplayedOrders());
    }

}
