import org.junit.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;

import java.util.Arrays;
import java.util.List;

public class SmokeTests extends BaseTests {

    AccountManagementPage accountManagementPage;

//    @Test
//    public void signIn() {
//        loginPage = openLoginPage("https://qa.geempower.com/geempower");
//        accountManagementPage = loginPage.login("testempadmin", "test123test");
//        Assert.assertEquals("Account Management", accountManagementPage.getTitle());
//        Assert.assertEquals("Account Name", accountManagementPage.getColumnNameOfFavoritesAccountsTable(2));
//    }

//    @Test
//    public void isDisplayedColumnNamesOfAccountTable() {
//        List<String> expectedColumnNamesOfAccountTable = Arrays.asList("Account No.", "Account Name", "City", "State", "Sales Org.", "Sales Channel", "", "");
//        Assert.assertEquals(expectedColumnNamesOfAccountTable, accountManagementPage.getAllColumsNameOfFavoritesAccountsTable());
//        Assert.assertTrue(accountManagementPage.getAllRowsValues() != null);
//    }

    @Test
    public void isInvoicePageOpened() throws InterruptedException {
        LoginPage loginPage;
        loginPage = openLoginPage("https://qa.geempower.com/geempower");
        accountManagementPage = loginPage.login("testempadmin", "test123test");


        HomePage homePage;
        InvoicePage invoicePage;
        List<String> expectedInvoiceTableHeaderColumnsName = Arrays.asList("Invoice No.", "PO No.", "GE Order No.", "Invoice Date", "Original Amount", "Current Balance", "Invoice Status", "Due Date", "Cash Discount");
        homePage = accountManagementPage.clickOnHeaderLogo();
        invoicePage=(InvoicePage) homePage.selectItemFromHeaderMenu("Invoice");
        invoicePage.getAllColumnNamesOfInvoicesTable();

        homePage=(HomePage) invoicePage.selectItemFromHeaderMenu("Home");
        homePage.getTitleOfVidgets();

        int i=0;
    }


}
