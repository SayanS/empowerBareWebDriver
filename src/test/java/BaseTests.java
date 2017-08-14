import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.AccountManagementPage;
import pages.BasePage;
import pages.LoginPage;

public class BaseTests {
    private WebDriver webDriver;
    @BeforeClass
    public void setUp(){
        this.webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

    }

    @AfterClass
    public void tearDown(){
        this.webDriver.close();
    }

    public LoginPage openLoginPage(String url){
        webDriver.navigate().to(url);
        return new LoginPage(webDriver);
    }

}
