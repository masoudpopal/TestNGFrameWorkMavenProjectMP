package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser(){
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                //System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
                // from now on we don't need to mention our webdriver as above we can do as follows!
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                //System.out.println("browser not available");
                break;
            default:
                throw new RuntimeException("invalid browser name");
        }
            driver.get(ConfigReader.getPropertyValue("url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        }

        public static void sendText(WebElement element, String textToSend){
        element.clear(); // in this website they are giving ID No by default so everytime we create a new user we have to clear the previous ID in order to have a new one
        element.sendKeys(textToSend);

        }
        public static  WebDriverWait getWait(){
        WebDriverWait wait =new WebDriverWait(driver,Constants.EXPLICIT_WAIT);
        return wait;

        }
        public static void waitForClickAbility(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));

        }

        public static void click(WebElement element){
        waitForClickAbility(element);
        element.click();

        }

        public static JavascriptExecutor getJSExecutor(){
            JavascriptExecutor js=(JavascriptExecutor) driver; // in here javascriptExecutor is an interface and we can not create object of it so that is why we are not using (new)
            return js;
        }
        public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();", element);
        }

        public static void takeScreenshot(String fileName) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp("yyyy_MM_dd_HH_mm_ss") + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String getTimeStamp(String pattern){
        Date date = new Date();

        // pattern YYYY-MM-DD-MM-SS-MS
        //to format the date according to our choice we have a function
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    }





