package w3schools;

import CommonAPI.Base;
import org.testng.annotations.Test;

/**
 * Created by shamimchy on 9/2/16.
 */
public class TableData extends Base {

    @Test
    public void getTableData(){
        for(int i=1; i<=6; i++){
            String text = getTextByCss(".table-responsive tbody tr:nth-child("+i+")");
            System.out.println( text);
        }
    }
}
