package dataToSearch;


import utility.DataReader;
import java.util.List;
import java.io.IOException;


/**
 * Created by rrt on 1/3/2016.
 */
public class ItemsToBeSearched {

    // Option 2, supply data from External source like excel files.
    DataReader dr = new DataReader();
    public String [] getData()throws IOException{
        String path = System.getProperty("user.dir")+"/data/file1.xls";
        String [] st = dr.fileReader(path);
     return st;
    }
}
