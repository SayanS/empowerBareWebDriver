import org.junit.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.InvoicePage;

import java.util.Arrays;
import java.util.List;

public class SmokeTests extends BaseTests {

    @Test
    public void isAccountInFavorite(){
        Assert.assertTrue(getAccountManagementPage().getColumnValuesOfFavoritesAccountsTable("Account No.").contains("2244410"));
    }

    @Test
    public void signIn() {
        Assert.assertEquals("Account Management", getAccountManagementPage().getTitle());
        Assert.assertEquals("Account Name", getAccountManagementPage().getColumnNameOfFavoritesAccountsTable(2));
    }

    @Test
    public void isDisplayedColumnNamesOfAccountTable() {
        List<String> expectedColumnNamesOfAccountTable = Arrays.asList("Account No.", "Account Name", "City", "State", "Sales Org.", "Sales Channel", "", "");
        Assert.assertEquals(expectedColumnNamesOfAccountTable, getAccountManagementPage().getAllColumsNameOfFavoritesAccountsTable());
        Assert.assertTrue(getAccountManagementPage().getAllRowsValuesOfFavoritesAccountsTable() != null);
    }

    @Test
    public void isInvoicePageOpened() throws InterruptedException {
        HomePage homePage;
        InvoicePage invoicePage;
        List<String> expectedInvoiceTableHeaderColumnsName = Arrays.asList("Invoice No.", "PO No.", "GE Order No.", "Invoice Date", "Original Amount", "Current Balance", "Invoice Status", "Due Date", "Cash Discount");
        homePage = getAccountManagementPage().clickOnHeaderLogo();
        invoicePage=(InvoicePage) homePage.selectItemFromHeaderMenu("Invoice");
        invoicePage.getAllColumnNamesOfInvoicesTable();

        Assert.assertTrue(invoicePage.getColumnValuesOfInvoicesTable("Invoice No.").contains("501303480"));

        homePage=(HomePage) invoicePage.selectItemFromHeaderMenu("Home");
        homePage.getTitleOfVidgets();


    }


}
