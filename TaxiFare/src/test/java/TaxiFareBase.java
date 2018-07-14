import CommonAPI.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shamimchy on 9/1/16.
 */
public class TaxiFareBase extends Base {

    @Test
    public  void  homePage() throws  InterruptedException,IOException {
        String url = driver.getCurrentUrl();
        System.out.println("Main " + url);
        // driver.findElement(By.xpath(".//*[@id='smartphonelink']/a")).click();
        clickByCss("#smartphonelink ");
        // driver.findElement(By.xpath(".//*[@id='smartphonelink']/a")).click();
        String url1 = driver.getCurrentUrl();
        System.out.println("Mobile  " + url1);
        Thread.sleep(1000);
        new Select(driver.findElement(By.name("countrySelect"))).selectByVisibleText("United States of America");
        driver.findElement(By.cssSelector("option[value=\"438 8th Ave New York, NY 10001\"]")).click();
        driver.findElement(By.cssSelector("#toDirectory > option[value=\"1000 Fifth Avenue, New York, NY 10028\"]")).click();
        driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Regular Site")).click();

        System.out.println("this is a test from shamim chowdhury");
        String url2 = driver.getCurrentUrl();
        System.out.println("Main  " + url2);
        Thread.sleep(1000);


        int i = 0;

        //  String title = driver.getTitle();


        Thread.sleep(1000);
        new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText("United States of America");
        driver.findElement(By.id("fromAddress")).clear();
        driver.findElement(By.id("fromAddress")).sendKeys("1000, 5th ave, ny");

        new Select(driver.findElement(By.id("toDirectory"))).selectByVisibleText("Shea Stadium");
        driver.findElement(By.name("submit")).click();


        Thread.sleep(1000);

        driver.findElement(By.linkText("Mobile")).click();
        Thread.sleep(2000);
        new Select(driver.findElement(By.name("countrySelect"))).selectByVisibleText("United States of America");
        driver.findElement(By.cssSelector("option[value=\"438 8th Ave New York, NY 10001\"]")).click();
        driver.findElement(By.cssSelector("#toDirectory > option[value=\"1000 Fifth Avenue, New York, NY 10028\"]")).click();
        driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);


        System.out.println("This line of printing is from mobile site");

        driver.findElement(By.id("fromAddress")).clear();
//        System.out.println("The Title of The web is (Smoke_test) 000000000  " );
//       String class_s = String.valueOf(driver.findElement(By.id("fromAddress")).getClass());
//
//        System.out.println("The Title of The web is (Smoke_test)  til here 01010101001" + class_s);
        driver.findElement(By.id("fromAddress")).sendKeys("2053 McGraw Ave, Bornx, NY");

        System.out.println("The Title of The web is (Smoke_test)  til here 11111111");

        driver.findElement(By.id("toAddress")).clear();
        driver.findElement(By.id("toAddress")).sendKeys("JFK International Airport, Queens, NY");
        driver.findElement(By.name("submit")).click();

        System.out.println("----------------The Smoke Test Finish--------------");


        driver.findElement(By.linkText("Home")).click();
        //   driver.findElement(By.cssSelector("img[alt=\"Home\"]")).click();

        Thread.sleep(1000);

        driver.findElement(By.linkText("Rules")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Home")).click();
        Thread.sleep(1000);
        List<String> list = getListOfTextByCss("#countrySelect option");
        displayText(list);
        System.out.println(" length " + list.size());


        System.out.println("/////////////2222222//////////// ");

        List<String> listMenu = searchDropDownMenu("#countrySelect option");
        displayText(listMenu);
        int x = 1;
        for (String item : listMenu) {
            System.out.println("Entered in the text Box: " + (x++) + "-------|" + item + "|---");
        }

        System.out.println("\n\n ---listMenu.size()-----" + listMenu.size());

        for (int y = 0; y < listMenu.size() - 1; y++) {

            //   new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(listMenu.get(y));


//            if (y == 0) {
            new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(listMenu.get(y));
            System.out.println("__________" + listMenu.get(y));
            List<String> listUSACity = searchDropDownMenu("#cityselect option");
            displayText(listUSACity);
            int k = 0;
            if (y == 0) {
                new Select(driver.findElement(By.id("cityselect"))).selectByVisibleText(listUSACity.get(k));
                System.out.println("  --- Country Name:  " + listMenu.get(y) + "  City Name: " + listUSACity.get(k));

                driver.findElement(By.id("fromAddress")).clear();
                driver.findElement(By.id("fromAddress")).sendKeys("1000, 5th ave, ny");

                // new Select(driver.findElement(By.id("toDirectory"))).selectByVisibleText("1900 5th Ave, New York, NY 10065");
                driver.findElement(By.id("toAddress")).clear();
                driver.findElement(By.id("toAddress")).sendKeys("1900 5th Ave, New York, NY 10065");
                driver.findElement(By.name("submit")).click();
                Thread.sleep(1000);

            //    driver.manage().window().setSize(new Dimension(500, 800));
                 driver.manage().window().setPosition(new Point((0),0));


                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                //The below method will save the screen shot in d drive with name "screenshot.png"
                FileUtils.copyFile(scrFile, new File("/Users/shamimchy/Documents/screenshot/screenshot_" + (y + 1) + "_" + k + "_a.png"));
                Thread.sleep(2000);


                String from = "";
                String to = "";
                //----------------------------- FRom Directory---------------
                int index = 0;
                WebElement select1 = driver.findElement(By.id("fromDirectory"));
                List<WebElement> options = select1.findElements(By.tagName("option"));
                for (WebElement option : options) {
                    System.out.println(" Select from Directory : " + option.getText());
                    // if(select.getAttribute("value") != "") {
                    //         System.out.println(select.getAttribute("value"));
//                    index++;
//
//                    if (index == 3) {
//                        //  System.out.println(" First Enter" + option.getText());
//                        //   driver.findElement(By.id("fromAddress")).clear();
//                        //  driver.findElement(By.id("fromAddress")).sendKeys(select.getAttribute("value"));
//                        //  Thread.sleep(2000);
//                        // option.click();
//                        //  from = select1.getAttribute("value");
//                        from = option.getText();
//                        System.out.println(" Before Conver Select_enter  FRom : " + from);
//
//
//                    }
//                    if (index == 5) {
//                        System.out.println(" Select From  : " + option.getText());
//                        driver.findElement(By.id("fromAddress")).clear();
//                        //  driver.findElement(By.id("fromAddress")).sendKeys(select.getAttribute("value"));
//                        //  Thread.sleep(2000);
//                        option.click();
//
//
//                    }


                    //  System.out.println( option.getText() + " ----- GetAttribute-----" + select.getAttribute("value"));System.out.print(option.getText());
                    //     System.out.println(select.getAttribute("value"));
//                  if ("Upper West Side (85th & Amst)".equals(option.getText()))
//                      System.out.print(option.getText());

//                          if(option.getLocation().equals(1)){
//                              System.out.print("");
//
//                          }
//                  option.click();
                    // }

                }


                //   Thread.sleep(2000);
                //}
            }
        }
    }
}
