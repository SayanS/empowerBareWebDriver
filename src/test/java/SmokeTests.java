import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.internal.reflect.AbstractMethodMatcher;
import pages.AccountManagementPage;
import pages.BasePage;
import pages.LoginPage;

public class SmokeTests extends BaseTests {
    BasePage page;
    AccountManagementPage accountManagementPage;

    @Test
    public void checkThatTitleOfAccountManagementPage(){
        LoginPage loginPage;
        AccountManagementPage accountManagementPage;
        loginPage= openLoginPage("https://qa.geempower.com/geempower");
        accountManagementPage=loginPage.login("testempadmin", "test123test");
        Assert.assertEquals("Account Management",accountManagementPage.getTitle());
    }


}
