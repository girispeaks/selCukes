package zoho.managers;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class WebDriverManager {

    WebDriver driver;
    ExtentTest test;
    Properties prop;
    SoftAssert softAssert;


    public WebDriverManager(){
        //initialize the properties file
        try{
            prop=new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/project.properties");
            prop.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        softAssert = new SoftAssert();
    }

    public void openBrowser(String browser){
        log("Opening browser "+browser);
        switch (browser) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Mozilla":
                driver = new FirefoxDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void navigate(String urlKey) {
       log("Navigating to "+getProperty(urlKey));
        driver.get(getProperty(urlKey));
    }

    public void click(String locatorKey){  //assuming locator to be xpath
        log("Clicking on "+locatorKey);
        findElement(locatorKey).click();
    }

    public void type(String locatorKey, String data){
        log("Typing "+data+" into "+locatorKey);
        findElement(locatorKey).sendKeys(data);
    }

    public WebElement findElement(String locatorKey){
        By locator = getLocator(locatorKey);
        WebElement element= null;
        try{
            //element has to be present and visible in dom
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element = driver.findElement(locator);
        } catch (Exception e) {
            //report failure
            logFailure("Object not found "+locatorKey, true);
        }
        return element;
    }

    public By getLocator(String locatorKey){
        if(locatorKey.endsWith("_id"))
            return By.id(getProperty(locatorKey));
        else if(locatorKey.endsWith("_xpath"))
            return By.xpath(getProperty(locatorKey));
        else if(locatorKey.endsWith("_css"))
            return By.cssSelector(getProperty(locatorKey));
        else if(locatorKey.endsWith("_name"))
            return By.name(getProperty(locatorKey));
        else if(locatorKey.endsWith("_linkText"))
            return By.linkText(getProperty(locatorKey));
        return null;
    }

/**************** Validate Functions********************************/
    public boolean verifyTitle(String expectedTitleKey){
        String expectedTitle = getProperty(expectedTitleKey);
        String actualTitle = driver.getTitle();
        if(expectedTitle.equals(actualTitle))
            return true;
        else
            return false;
    }

    //presence and visibility of element
    public boolean isElementPresent(String locatorKey){
        By locator = getLocator(locatorKey);
        try{
            //element has to be present and visible in dom
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

/**************** Utility Functions ********************************/
    public void init(ExtentTest test){
        this.test=test;
    }

    public String getProperty(String key){
        return prop.getProperty(key);
    }

    public void log(String msg){
        test.log(Status.INFO, msg);
    }

    public void logFailure(String msg, boolean stopExecution){
        System.out.println("Failure... "+msg);
        //screenshot of the page and embed it in the reports
        //fail in extent reports
        test.log(Status.FAIL, msg);
        //fail in testng-cucumber
        //Assert.fail(msg); //hard assertion
        softAssert.fail(msg);
        //stopExecution is used to stop the test case immediately and dont continue execution(in case of critical failure).
        if(stopExecution)
            softAssert.assertAll();
    }

    public void quit(){
        if(driver !=null)
            driver.quit();
        if(softAssert!=null)
            softAssert.assertAll();
    }

    public int getLeadRowNumberWithCellData(String leadName) {
        List<WebElement> names = driver.findElements(getLocator("leadnames_xpath"));
        for(int i=0;i<names.size();i++) {
            if(leadName.equalsIgnoreCase(names.get(i).getText()))
                return (i+1);
        }

        return -1;// not found
    }

    public void selectLeadCheckBox(int rowNum) {
        driver.findElement(By.cssSelector("lyte-exptable-tr:nth-child("+rowNum+") > lyte-exptable-td:nth-child(2) label")).click();

    }
//*[@class='lyteExpTableRowGroup']/lyte-exptable-tr

}
