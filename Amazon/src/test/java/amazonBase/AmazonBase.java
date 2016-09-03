package amazonBase;

import CommonAPI.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by shamimchy on 9/1/16.
 */
public class AmazonBase extends Base {

    @Test
    public  void  homePage() throws  InterruptedException{
        driver.findElement(By.xpath(".//*[@id='nav-xshop']/a[2]"));
        String url = driver.getCurrentUrl();
        System.out.println(url);
        Thread.sleep(1000);
        System.out.println("this is a AmazonHome");
        driver.navigate().back();
        Thread.sleep(1000);
    }
}
