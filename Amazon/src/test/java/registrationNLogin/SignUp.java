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
            String [][] value = dr.fileReader1(path);
            sleepFor(2);
            clickByCss("#nav-tools #nav-link-yourAccount .nav-line-1");
          //  clickByCss(".nav-action-inner");
            sleepFor(2);
            String emailLocator = value[1][0];
            String email = value[1][1];
            String passLocator = value[2][0];
            String pass = value[2][1];
            typeByCss(emailLocator, email);
            typeByCss(passLocator, pass);
            sleepFor(4);
        }catch(Exception ex){
            result = true;
        }
        if(result==true) {
            dr.writeBack("pass");
        }else{
            dr.writeBack("fail");
        }

    }
}

