package CommonAPI;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;





/**
 * Created by shamimchy on 8/31/16.
 */
public class Base {
    public WebDriver driver = null;
    public static Logger logger = Logger.getLogger(Base.class);


    @Parameters({"useCloudeEnv","userName","key","os","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") Boolean useCloudeEnv, @Optional("rahmanww")String userName,
                      @Optional("ssk")String key, @Optional("WIN8")String os,@Optional("firefox") String browserName,
                      @Optional("35")String browserVersion,@Optional("http://piit.us") String url)throws IOException{

        if (useCloudeEnv==true){
            logger.setLevel(Level.INFO);
            logger.info("Test is Running on Cloude Environment");
            getCloudeEnvironment(userName, key, os, browserName, browserVersion);
            System.out.println(" Test is running on SauceLabs, Please wait for result");
        }
        else {
            logger.setLevel(Level.INFO);
            logger.info("Test is Running on Cloude Environment");
            getLocalDriver(os, browserName, browserVersion);
            System.out.println(" Test is running on Local Computer");
           }
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.navigate().to(url);
            driver.manage().window().maximize();

    }

    public WebDriver getCloudeEnvironment(String userName, String key,String os, String browserName,String browserVersion)throws IOException{
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browserName);
        cap.setCapability("version", browserVersion);
        cap.setCapability("platform", os);
        this.driver = new RemoteWebDriver(new URL("http://"+userName+":"+key+"@ondemand.saucelabs.com:80/wd/hub"),cap);

        return  driver;
    }

    public  WebDriver getLocalDriver(String os, String browserName,String browserVersion){
        if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();

        } else if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","/Users/shamimchy/IdeaProjects/NewTestForTaxiFare/Common/seleniumDriver/chromedriver");
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        } else if(browserName.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver" , "/Common/seleniumDriver/IEDriverServer");
            driver = new InternetExplorerDriver();

        }

        return  driver;

    }


    @AfterMethod
    public void cleanUp(){

        driver.quit();

    }


    //Helping methods
    public void clickByCss(String locator){

        driver.findElement(By.cssSelector(locator)).click();
    }
    public List<String> getTextFromWebElements(String locator){
        List<WebElement> element = new ArrayList<WebElement>();
        List<String> text = new ArrayList<String>();
        element = driver.findElements(By.cssSelector(locator));
        for(WebElement web:element){
            text.add(web.getText());
        }

        return text;
    }
    public void clearInput(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }
    public void keysInput(String locator){
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }

    public void clickByXpath(String locator){
        driver.findElement(By.xpath(locator)).click();
    }
    public void typeByCss(String locator, String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.CLEAR);
    }
    public void typeByCssThenEnter(String locator, String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.CLEAR, value, Keys.ENTER);
    }
    public void typeByXpath(String locator){
        driver.findElement(By.xpath(locator)).click();
    }
    public void clickById(String locator){
        driver.findElement(By.id(locator)).click();
    }
    public void clickByName(String locator){
        driver.findElement(By.id(locator)).click();
    }
    public void navigateBack(){
        driver.navigate().back();
    }
    public void navigateForward(){
        driver.navigate().forward();
    }
    public String getTextByCss(String locator){
        String st = driver.findElement(By.cssSelector(locator)).getText();

        return st;
    }
    public String getTextByXpath(String locator){
        String st = driver.findElement(By.xpath(locator)).getText();

        return st;
    }
    public String getTextById(String locator){
        return driver.findElement(By.id(locator)).getText();

    }
    public String getTextByName(String locator){
        String st = driver.findElement(By.name(locator)).getText();

        return st;
    }
    public WebElement getWebElementByCss(String locator){
        return driver.findElement(By.cssSelector(locator));

    }
    public List<WebElement> getWebElementsByCss(String locator){
        List<WebElement> elementList = new ArrayList<WebElement>();
        elementList = driver.findElements(By.cssSelector(locator));

        return elementList;
    }
    public WebElement getWebElementByXpath(String locator){
        WebElement element = driver.findElement(By.xpath(locator));

        return element;
    }

    //constant sleep time
    public void sleepFor(int sec)throws InterruptedException{
        Thread.sleep(1000*sec);
    }

    //Synchronization
    public void waitUntilClickAble(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitUntilSelectable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    //get WebElements
    public List<WebElement> getWebElements(String locator){
        List<WebElement> elements = driver.findElements(By.cssSelector(locator));
        return elements;
    }
    //get texts
    public List<String> getElementTexts(String  locator){
        List<String> texts = new ArrayList<String>();
        List<WebElement> element = new ArrayList<WebElement>();
        element = getWebElements(locator);
        for(WebElement st:element){
            texts.add(st.getText());
        }
        return texts;
    }

    //Keys
    public void clearInputBox(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }

    public void mouseHoverByCSS(String locator){
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }
    public void mouseHoverByXpath(String locator){
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }
    //handling Alert
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iframeHandle(WebElement element){
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow(){
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    //Taking Screen shots
    public void takeScreenShot()throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("screenShots.png"));
    }

}
