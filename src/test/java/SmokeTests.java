import org.junit.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;

import java.util.Arrays;
import java.util.List;

public class SmokeTests extends BaseTests {

    @Test
    public void signIn() {
        Assert.assertEquals("Account Management", getAccountManagementPage().getTitle());
        Assert.assertEquals("Account Name", getAccountManagementPage().getColumnNameOfFavoritesAccountsTable(2));
    }

    @Test
    public void isDisplayedColumnNamesOfAccountTable() {
        List<String> expectedColumnNamesOfAccountTable = Arrays.asList("Account No.", "Account Name", "City", "State", "Sales Org.", "Sales Channel", "", "");
        Assert.assertEquals(expectedColumnNamesOfAccountTable, getAccountManagementPage().getAllColumsNameOfFavoritesAccountsTable());
        Assert.assertTrue(getAccountManagementPage().getAllRowsValues() != null);
    }

    @Test
    public void isInvoicePageOpened() throws InterruptedException {
        HomePage homePage;
        InvoicePage invoicePage;
        List<String> expectedInvoiceTableHeaderColumnsName = Arrays.asList("Invoice No.", "PO No.", "GE Order No.", "Invoice Date", "Original Amount", "Current Balance", "Invoice Status", "Due Date", "Cash Discount");
        homePage = getAccountManagementPage().clickOnHeaderLogo();
        invoicePage=(InvoicePage) homePage.selectItemFromHeaderMenu("Invoice");
        invoicePage.getAllColumnNamesOfInvoicesTable();

        homePage=(HomePage) invoicePage.selectItemFromHeaderMenu("Home");
        homePage.getTitleOfVidgets();

    }


}
