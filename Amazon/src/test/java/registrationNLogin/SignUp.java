package registrationNLogin;

import CommonAPI.Base;
import org.testng.annotations.Test;
import utility.DataReader;


import utility.DataReader;
import java.util.List;
import java.io.IOException;

import java.io.IOException;

/**
 * Created by rrt on 4/12/2015.
 */
public class SignUp extends Base {
    public DataReader dr = new DataReader();
    String path = "../Amazon/data/file2.xls";

    @Test
    public void signIn()throws IOException,InterruptedException {
        boolean result = false;
        try {
            System.out.println("Here is the starting points////////111////");
            String [][] value = dr.fileReader1(path);
            sleepFor(2);
            String emailLocator = value[1][0];
            String email = value[1][1];
            String passLocator = value[2][0];
            String pass = value[2][1];
            clickByCss("#nav-tools #nav-link-accountList  .nav-line-1");
          //  clickByCss(".nav-action-inner");
            sleepFor(2);
            typeByCss(emailLocator, email);
            typeByCss(passLocator, pass);
            clickByCss("#signInSubmit");
            sleepFor(4);
            System.out.print("Finishing touch");
          //  sleepFor(4);
            result = true;
        }catch(Exception ex){
            result = false;
        }
        if(result==true) {
            dr.writeBack("pass");
        }else{
            dr.writeBack("fail");
        }

    }
}

