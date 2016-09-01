package CommonAPI;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by shamimchy on 8/31/16.
 */
public class Base {
    public WebDriver driver = null;


    @Parameters({"url"})
    @BeforeMethod
    public void setUp(String url)throws IOException{
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();

    }


    @AfterMethod
    public void cleanUp(){

        driver.quit();

    }


}
