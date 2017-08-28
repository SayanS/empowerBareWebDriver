import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pages.AccountManagementPage;
import pages.BasePage;
import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTests {
    private WebDriver webDriver;
    private BasePage basePage;

    @BeforeSuite
    public void setUp() throws IOException {
        this.webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @BeforeClass
    public void signIn() throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("./src/test/java/configureTests.properties");
        prop.load(input);
        LoginPage loginPage=new LoginPage(webDriver);
        webDriver.navigate().to(prop.getProperty("baseURL"));
        basePage=loginPage.login(prop.getProperty("login"), prop.getProperty("password"));
    }

    @AfterClass
    public void tearDown(){
        this.webDriver.close();
    }

    public BasePage getBasePage(){
        return basePage;
    }

    public AccountManagementPage getAccountManagementPage(){
        return new AccountManagementPage(webDriver);
    }


}
