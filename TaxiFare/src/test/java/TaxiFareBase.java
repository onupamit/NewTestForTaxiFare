import CommonAPI.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by shamimchy on 9/1/16.
 */
public class TaxiFareBase extends Base {

    @Test
    public  void  homePage() throws  InterruptedException{
        driver.findElement(By.xpath(".//*[@id='smartphonelink']/a")).click();
        String url = driver.getCurrentUrl();
        System.out.println(url);
        Thread.sleep(1000);
        System.out.println("this is a test from shamim chowdhury");
        driver.navigate().back();
        Thread.sleep(1000);
    }
}
