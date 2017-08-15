import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;


public class DownloadTests {
    WebDriver webDriver;

    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "./target/");
        //chromePrefs.put("profile.default_content_settings.popups", 0);

        ChromeOptions options = new ChromeOptions();
        //HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        options.setExperimentalOption("prefs", chromePrefs);
        //options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        // cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        // cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        this.webDriver = new ChromeDriver(cap);
//        options.AddArgument("--start-maximized");
//        options.AddArgument("--ignore-certificate-errors");
//        options.AddArgument("--disable-popup-blocking");
//        options.AddArgument("--incognito");
    }

    @Test
    public void checkDownLoad() {
        webDriver.navigate().to("http://www.seleniumhq.org/download/");
        (new WebDriverWait(webDriver, 5)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@href='http://selenium-release.storage.googleapis.com/3.5/selenium-java-3.5.0.zip']")));
        webDriver.findElement(By.xpath(".//a[@href='http://selenium-release.storage.googleapis.com/3.5/selenium-java-3.5.0.zip']")).click();
    }

    @Test
    public void checkUploadFile() throws InterruptedException {
        webDriver.navigate().to("https://drive.google.com/drive/my-drive");
        webDriver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys("garmsayan@gmail.com");
        webDriver.findElement(By.xpath(".//*[@id='identifierNext']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath(".//input[@name='password']")).sendKeys("Rfhfylfitkm12r");
        webDriver.findElement(By.xpath(".//*[@id='passwordNext']")).click();
        Thread.sleep(2000);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//div[(.='My Drive')and(@class='h-sb-Ic h-R-w-d-ff')]/ancestor::div[1]"))).click().perform();
        webDriver.switchTo().activeElement();
        actions.moveToElement(webDriver.findElement(By.xpath(".//*[contains(text(),'Upload files')]"))).click().perform();
        webDriver.findElement(By.xpath(".//*[contains(text(),'Upload files')]")).sendKeys("./target/chrome_debug.log");
    }

}
