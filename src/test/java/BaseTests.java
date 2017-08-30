import org.openqa.selenium.Cookie;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.AccountManagementPage;
import pages.BasePage;
import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class BaseTests {
    private WebDriver webDriver;
    private BasePage basePage;

//    @BeforeSuite
//    public void setUp() throws IOException {
//        this.webDriver = new ChromeDriver();
//        webDriver.manage().window().maximize();
//    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"browserName", "platform"})
    public void signIn(String browserName, String platform) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;

        //System.setProperty("webdriver.gecko.driver","/usr/local/share/geckodriver.sh");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browserName);
        cap.setPlatform(Platform.extractFromSysProperty(platform));
        URL hostURL = new URL("http://192.168.10.61:4445/wd/hub");
        this.webDriver = new RemoteWebDriver(hostURL, cap);
        this.webDriver.manage().window().maximize();

        input = new FileInputStream("./configureTests.properties");
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
        if(webDriver.getCurrentUrl().contains("my-account/manage-accounts")){
            return new AccountManagementPage(webDriver);
        }

        return new AccountManagementPage(webDriver);
    }

    public Set<Cookie> getCookie(){
        return webDriver.manage().getCookies();
    }


}
