package w3schools;

import CommonAPI.Base;
import org.testng.annotations.Test;

/**
 * Created by shamimchy on 9/2/16.
 */
public class TableData extends Base {

    @Test
    public void getTableData(){
        System.out.println( "---------Test is Running-- shamim  10.46----------");
        for(int i=1; i<=6; i++){
            String text = getTextByCss(".w3-responsive tbody tr:nth-child("+i+")");
            System.out.println( text);
        }
    }
}
