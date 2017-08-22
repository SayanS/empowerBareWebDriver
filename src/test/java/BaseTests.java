import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pages.AccountManagementPage;
import pages.LoginPage;

import java.io.*;
import java.util.Properties;

public class BaseTests {
    private WebDriver webDriver;
    private AccountManagementPage accountManagementPage;

    @BeforeSuite
    public void setUp() throws IOException {
        this.webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("./src/test/java/configureTests.properties");
        prop.load(input);
        LoginPage loginPage=new LoginPage(webDriver);
        webDriver.navigate().to(prop.getProperty("baseURL"));
        accountManagementPage=loginPage.login(prop.getProperty("login"), prop.getProperty("password"));
    }

    @BeforeClass
    public void signIn() throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("./src/test/java/configureTests.properties");
        prop.load(input);
        LoginPage loginPage=new LoginPage(webDriver);
        webDriver.navigate().to(prop.getProperty("baseURL"));
        accountManagementPage=loginPage.login(prop.getProperty("login"), prop.getProperty("password"));
    }

    @AfterClass
    public void tearDown(){
        this.webDriver.close();
    }

    public AccountManagementPage getAccountManagementPage(){
        return this.accountManagementPage;
    }


}
