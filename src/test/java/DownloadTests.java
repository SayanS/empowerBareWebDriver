import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Set;

//http://johnfergusonsmart.com/configuring-chromedriver-easily-with-serenity-bdd/
public class DownloadTests {

    WebDriver webDriver;

    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "./target/");
        chromePrefs.put("profile.default_content_settings.popups", 0);

        ChromeOptions options = new ChromeOptions();
        //HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        options.setExperimentalOption("prefs", chromePrefs);
        //options.addArguments("--test-type");
        //options.addArguments("--disable-extensions"); //to disable browser extension popup

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
    public void checkDownLoad() throws IOException, InterruptedException {
        File file;
        String fileName;
        webDriver.navigate().to("http://www.seleniumhq.org/download/");
        (new WebDriverWait(webDriver, 5)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@href='http://selenium-release.storage.googleapis.com/3.5/selenium-java-3.5.0.zip']")));
        webDriver.findElement(By.xpath(".//a[@href='http://selenium-release.storage.googleapis.com/3.5/selenium-java-3.5.0.zip']")).click();

        fileName=webDriver.findElement(By.xpath(".//a[@href='http://selenium-release.storage.googleapis.com/3.5/selenium-java-3.5.0.zip']")).getAttribute("href");
        file=new File("./target/"+fileName.replace(fileName.substring(0,fileName.lastIndexOf("/")+1),""));
        Thread.sleep(5000);
        Assert.assertTrue(file.exists());

        Assert.assertTrue(Files.deleteIfExists(file.toPath()));
    }

    @Test
    public void checkUploadFile() throws InterruptedException, AWTException {
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

        Set handles = webDriver.getWindowHandles();
        // Pass a window handle to the other window
        for (String handle1 : webDriver.getWindowHandles()) {
            System.out.println(handle1);
            webDriver.switchTo().window(handle1);
        }

        Robot robot = new Robot();
        String keys = "./target/chrome_debug.log";
        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.delay(100);
            robot.keyRelease(keyCode);
            robot.delay(100);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        int i = 0;
    }

}
