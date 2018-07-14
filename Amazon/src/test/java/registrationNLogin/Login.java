package registrationNLogin;

import CommonAPI.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utility.DataReader;

import java.io.IOException;

/**
 * Created by shamimchy on 9/3/16.
 */
public class Login extends Base {

    public DataReader dr = new DataReader();
    String path = "../Amazon/data/file2.xls";


    @Test
    public void signIn()throws IOException, InterruptedException{

        boolean result = false;
        try {
            System.out.print("We are here inside ---------------------- \n");
            String [][] value = dr.fileReader1(path);
            System.out.println("File reader------------------------- "+ value.length +" ///////// "+ value[1][0]);
            System.out.println("////////"+ value[1][1] +" ///////// "+ value[2][0]+"/////////"+value[2][1]);
            sleepFor(2);
           clickByCss("#nav-tools #nav-link-accountList  .nav-line-1");
            sleepFor(2);
            String emailLocator =  value[1][0];
            String email =   value[1][1];
            String passLocator =  value[2][0];
            String pass = value[2][1];
            System.out.print("------------------Email---"+ email +"\n");
            System.out.print("------------------Pass---"+ pass +"\n");

            System.out.println(driver.getCurrentUrl());
             typeByCss(emailLocator, email);
            clickByCss("input#continue.a-button-input");
            typeByCss(passLocator, pass);
             clickByCss("#signInSubmit");
            sleepFor(4);

            result = true;
        }catch(Exception ex){
            result = false;
        }
        if(result==true) {
            System.out.println("---PASS--");
            dr.writeBack("pass");
        }else{
            System.out.println("---FAIL--");
            dr.writeBack("fail");
        }

    }
}

