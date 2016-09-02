package CommonAPI;


import org.apache.commons.io.FileUtils;
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


    @Parameters({"useCloudeEnv","userName","key","os","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") Boolean useCloudeEnv, @Optional("rahmanww")String userName,
                      @Optional("ssk")String key, @Optional("WIN8")String os,@Optional("firefox") String browserName,
                      @Optional("35")String browserVersion,@Optional("http://piit.us") String url)throws IOException{

        if (useCloudeEnv==true){
            getCloudeEnvironment(userName, key, os, browserName, browserVersion);
            System.out.println(" Test is running on SauceLabs, Please wait for result");
        }
        else {

              getLocalDriver(os, browserName, browserVersion);
                System.out.println(" Test is running on Local Computer");
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();

    }

    public void getCloudeEnvironment(String userName, String key,String os, String browserName,String browserVersion)throws IOException{
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browserName);
        cap.setCapability("version", browserVersion);
        cap.setCapability("platform", os);
        this.driver = new RemoteWebDriver(new URL("http://"+userName+":"+key+"@ondemand.saucelabs.com:80/wd/hub"),cap);

    }

    public  void getLocalDriver(String os, String browserName,String browserVersion){
        if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();

        } else if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "../Common/selenium-driver/chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        } else if(browserName.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver" , "../Common/selenium-driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }



    }


    @AfterMethod
    public void cleanUp(){

        driver.quit();

    }



    public List<String> getTextFromWebElements(String locator){
        List<WebElement> element = new ArrayList<WebElement>();
        List<String> text = new ArrayList<String>();
        element = driver.findElements(By.cssSelector(locator));
        for(WebElement web:element) {
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
        driver.findElement(By.cssSelector(locator)).sendKeys(value,Keys.CLEAR);
    }






//Utilities Methods

    public void clickByCss(String locator){
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void clickByText(String locator){
        driver.findElement(By.linkText(locator)).click();
    }

    public void typeByXpath(String locator, String value){
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void typeAndEnterByCss(String locator, String value){
        driver.findElement(By.cssSelector(locator)).clear();
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }
    public void typeAndEnterByXpath(String locator, String value){
        driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
    }
    public List<WebElement> getWebElements(String locator){
        List<WebElement> element = driver.findElements(By.cssSelector(locator));

        return element;
    }
    public List<WebElement> getWebElementsByXpath(String locator){
        List<WebElement> element = driver.findElements(By.xpath(locator));

        return element;
    }

    public List<String> getListOfTextByCss(String locator){
        List<WebElement> element = driver.findElements(By.cssSelector(locator));
        List<String> text = new ArrayList<String>();

        for(WebElement st:element){
            text.add(st.getText());
        }
        return text;
    }

    public String getTextByCss(String locator){
        String text = driver.findElement(By.cssSelector(locator)).getText();

        return text;
    }

    public void displayText(List<String> text){
        for(String st:text){
            System.out.println(st);
        }
    }
    public void sleepFor(int sec)throws InterruptedException{
        Thread.sleep(sec * 1000);
    }
    public void mouseHover(String locator){
        WebElement element = driver.findElement(By.cssSelector(locator));
        Actions action = new Actions(driver);
        Actions hover = action.moveToElement(element);
    }

    public void selectElementByVisibleText(String locator, String value){
        WebElement element = driver.findElement(By.cssSelector(locator));
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
    public void iframeHandle(WebElement element){
        driver.switchTo().frame(element);
    }
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }
    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitUntilClickable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitUntilToBeSelected(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public void takeScreenShot() throws IOException{
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file, new File("screenShots.png"));
    }
    public void navigateBack(){
        driver.navigate().back();
    }

    public WebDriver clickByWebElement(WebElement element){
        element.click();

        return driver;
    }

}