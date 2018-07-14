import CommonAPI.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by shamimchy on 8/28/17.
 */
public class TaxiFareDropDown1 extends Base{
    @Test
    public void HomePageDropDown() throws  InterruptedException,IOException {
        String url = driver.getCurrentUrl();
        System.out.println(" /////////  Main URL of the website : " + url);
        System.out.println(" ///////// Title of website  "+  driver.getTitle());


       System.out.println("//////////---List of Countries -------//////////////////////");
//        List<String> list = getListOfTextByCss("#countrySelect option");
//        displayText(list);
//
//
//        System.out.println("/////////////2222222////listMenu=listCountry//////// ");

        List<String> listCountry = searchDropDownMenu("#countrySelect option");
      //  displayText(listCountry);
        int x = 1;
        for (String item : listCountry) {
            System.out.println(" Country (" + (x++) + ") : |" + item + "|");
        }



        for (int y = 0; y < listCountry.size() - 1; y++) {     // y = size of counrty (listCountry.size())

            //   new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(listMenu.get(y));


                new Select(driver.findElement(By.id("countrySelect"))).selectByVisibleText(listCountry.get(y));
                System.out.println("---------------List of Cities-------------");
                System.out.println("\n  Country Name :" + listCountry.get(y) + "\n");
                List<String> listOfCity = searchDropDownMenu("#cityselect option");
                displayText(listOfCity);

                Thread.sleep(2000);

                System.out.println("---------------List of Cities finish--------");



                //  if (y == 0) {
                for (int k = 1; k < listOfCity.size(); k++) {     //  k is the list of  city

                    //  }
                    new Select(driver.findElement(By.id("cityselect"))).selectByVisibleText(listOfCity.get(k));
                    System.out.println("  --- Country Name:  " + listCountry.get(y) + "  City Name: " + listOfCity.get(k));

                    Thread.sleep(2000);

                    //--------------------------///////////////// ENTER INPUT /////////------------------------------------

                    // ------------------------------enter adrres in adress line-----------------------------------

                    System.out.println("\n ////////////////////////START ENTER//////////////////////////////");
                    driver.manage().window().maximize();

                    driver.findElement(By.id("fromAddress")).clear();
                    driver.findElement(By.id("fromAddress")).sendKeys("2053 McGraw Ave ,Bronx, NY 10462");

                    String st = driver.findElement(By.id("fromAddress")).getAttribute("value");
                    System.out.println(" Entered In FRom Address:: ..." + st);

                    // new Select(driver.findElement(By.id("toDirectory"))).selectByVisibleText("1900 5th Ave, New York, NY 10065");
                    driver.findElement(By.id("toAddress")).clear();
                    driver.findElement(By.id("toAddress")).sendKeys("1000 5th Ave, New York, NY 10065");
                    String end = driver.findElement(By.id("toAddress")).getAttribute("value");
                    System.out.println("Enetred In to Address::" + end);
                    Thread.sleep(2000);
                    driver.findElement(By.name("submit")).click();
                    Thread.sleep(2000);


                    String totalFare1 = driver.findElement(By.id("message")).getText();
                    String drDistance1 = driver.findElement(By.id("message2")).getText();

                    System.out.println(" " + totalFare1);
                    System.out.println("  " + drDistance1);
                    System.out.println("//////////////////////////FINISH ENTER////////////////////////////////// \n");

                    //--------------------------///////////////// FINISH  INPUT  /////////------------------------------------


                    // driver.manage().window().setSize(new Dimension(500, 400));
                    //  driver.manage().window().setPosition(new Point((0),0));
                    //   driver.manage().window().maximize();

                    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    //The below method will save the screen shot in d drive with name "screenshot.png"
                    FileUtils.copyFile(scrFile, new File("/Users/shamimchy/Documents/screenshot/screenshot_" + (y + 1) + "_" + k + "_a.png"));
                    Thread.sleep(2000);


                    String from = "";
                    String to = "";
                    //----------------------------- FRom Directory---------------

                    System.out.println("----------------listOfAddress_fromDirectory----------------------");
                    List<String> listOfAddress_fromDirectory = searchDropDownMenu("#fromDirectory option");
                   // displayText(listOfAddress_fromDirectory);
                    Thread.sleep(2000);

                    System.out.println("----------------listOfAddress_toDirectory----------------------");
                    List<String> listOfAddress_toDirectory = searchDropDownMenu("#toDirectory option");
                   // displayText(listOfAddress_toDirectory);
                    Thread.sleep(2000);

                    ///////////////////////////////////////////////////////////////////////////////////////////////////

                    int k_k = 1;  // supposed to be k_k = 1   size of fromDirectory address
                    while (k_k < listOfAddress_fromDirectory.size()) {


                        driver.findElement(By.id("fromAddress")).clear();
                        new Select(driver.findElement(By.id("fromDirectory"))).selectByVisibleText(listOfAddress_fromDirectory.get(k_k));
                           System.out.println(" Starting Address: " + listOfAddress_fromDirectory.get(k_k));


//                    System.out.println("----------------fromAddress----------------------");
//
//                    int index = 0;
//                    WebElement select1 = driver.findElement(By.id("fromDirectory"));
//                    List<WebElement> options = select1.findElements(By.tagName("option"));
//                    for (WebElement option : options) {
//                        System.out.println(" Select from Directory : " + option.getText());
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

                        //  }


                        //-------------toDirectory---------------------------

//                    System.out.println("----------------listOfAddress_toDirectory----------------------");
//                    List<String> listOfAddress_toDirectory = searchDropDownMenu("#toDirectory option");
//                    displayText(listOfAddress_toDirectory);
//                    Thread.sleep(2000);


                        WebElement selectTo = driver.findElement(By.id("toDirectory"));
                        List<WebElement> optionsTo = selectTo.findElements(By.tagName("option"));

                        int c = 1;          // supposed to be c=1
//                for (WebElement option : optionsTo) {         // start option
//
//                    System.out.println( (c++)+ " listOfAddress_toDirectory size   :"+ listOfAddress_toDirectory.size()+"  To Directory   : ------" + option.getText());


                        // city Name from index
                        while (c < listOfAddress_toDirectory.size()) {
                           // if ( c != k_k) {
                                driver.findElement(By.id("toAddress")).clear();
                                new Select(driver.findElement(By.id("toDirectory"))).selectByVisibleText(listOfAddress_toDirectory.get(c));

                                System.out.println("  "+ c + " == "+ k_k+"  Starting Address: " + listOfAddress_fromDirectory.get(k_k));
                                System.out.println(" Ending Address: " + listOfAddress_toDirectory.get(c));
                               Thread.sleep(1000);
                              if ( c == k_k) {
                                  System.out.println(" same  start end finish address");
                              }
                            else {
                                  try {

                                      driver.findElement(By.name("submit")).click();
                                      Thread.sleep(5000);

                                      Alert alert = driver.switchTo().alert();
                                       alert.accept();

                                 }
                                  catch (NoAlertPresentException e)
                                  {
                                      driver.findElement(By.name("submit")).click();
//                                      System.out.println("alert is not present");
                                  }



                                  FileUtils.copyFile(scrFile, new File("/Users/shamimchy/Documents/screenshot/screenshot_" + (y + 1) + "_" + c + "_a.png"));
                                  System.out.println("        get Atrribut   " + selectTo.getAttribute("value"));

                                  Thread.sleep(1000);

                                  String totalFare = driver.findElement(By.id("message")).getText();
                                  String drDistance = driver.findElement(By.id("message2")).getText();

                                  System.out.println(" ..." + totalFare);
                                  System.out.println(" ...  \n" + drDistance);

                              }
                                System.out.println("........................................................");

                                if (c == 4) {
                                    break;
                                }

                                c++;
                                Thread.sleep(1000);
//                            }
//
//                            System.out.println(c + "  Starting Address: " + listOfAddress_fromDirectory.get(k_k));
//                            System.out.println(" Ending Address: " + listOfAddress_toDirectory.get(c));
//                            System.out.println(c + "///////////// Starting end ending in the same Location ///////// " + k_k);
//                            Thread.sleep(1000);
//                            c++;
                        }

                        k_k++;
                        if (k_k == 2) {
                            k_k = listOfAddress_fromDirectory.size();
                        }


                        // if(select.getAttribute("value") != "") {
                        //         System.out.println(select.getAttribute("value"));
                        //   indexTo++;


//                    if (indexTo == 4) {
////                                System.out.print("Second Enter : " + optionTo.getText());
////                                driver.findElement(By.id("toAddress")).clear();
////                                 driver.findElement(By.id("toAddress")).sendKeys(select.getAttribute("value"));
////                              //  optionTo.click();
////
////                                driver.findElement(By.name("submit")).click();
////
////                                Thread.sleep(2000);
//                        // to = selectTo.getAttribute("value");
//                        to = optionTo.getText();
//                        System.out.println(" Before Convert Select_Enter to : " + to);
//
//                    }

//                    if (indexTo == 6) {
//                        System.out.println(" Select to : " + optionTo.getText());
//                        driver.findElement(By.id("toAddress")).clear();
//                        // driver.findElement(By.id("toAddress")).sendKeys(select.getAttribute("value"));
//                        optionTo.click();
//
//                        driver.findElement(By.name("submit")).click();
//
//                        File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                        //The below method will save the screen shot in d drive with name "screenshot.png"
//                        FileUtils.copyFile(scrFile1, new File("/Users/shamimchy/Documents/screenshot/screenshot_" + (y + 1) + "_" + y + "_b.png"));
//
//
//                        Thread.sleep(2000);
//
//                        Thread.sleep(1000);
//
//                    }

                        Thread.sleep(1000);
                        //  System.out.println( option.getText() + " ----- GetAttribute-----" + select.getAttribute("value"));System.out.print(option.getText());
                        //       System.out.println(select.getAttribute("value"));
//                  if ("Upper West Side (85th & Amst)".equals(option.getText()))
//                      System.out.print(option.getText());

//                          if(option.getLocation().equals(1)){
//                              System.out.print("");
//
//                          }
//                  option.click();
                        // }

                        //   }     //finish the option


                        //   Thread.sleep(2000);
                        //}
                    }
                }
            }
        }
    }

