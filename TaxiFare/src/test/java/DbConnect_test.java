
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DbConnect_test{

    public static void main(String[] args) throws Exception {
      //  System.out.println("Hello_world, I am starting from today");
       // getConnection();
       // createTable();
      //  post();
        get();
    }

    private static ArrayList<String> get()throws Exception{
        try {
            Connection con = getConnection();
            PreparedStatement statement =  con.prepareStatement("SELECT first, last FROM  tableNewOne");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> arrayList = new ArrayList<String>();

            while (resultSet.next()){
                System.out.print(resultSet.getString("first"));
                System.out.print(" ");
                System.out.println(resultSet.getString("last"));

                arrayList.add(resultSet.getString("last"));
            }
            System.out.println("\n All record have been selected");
            return  arrayList;
        } catch (Exception e){
            System.out.print(e);
            return  null;
        }
    }

    public static void post()throws Exception{
        final  String var1 = "Nehla";
        final  String var2 = "Chowdhury";
        try {
            Connection con = getConnection();
            PreparedStatement posted =  con.prepareStatement("INSERT INTO tableNewOne(first, last) VALUES('"+var1+"','"+var2+"')");
            posted.executeUpdate();
        }catch (Exception e){
            System.out.print(e);
        }
        finally {
            System.out.println(" Insrt Completed ");
        }
    }

    public static void createTable()throws Exception{
        try {
            Connection con = getConnection();
//            String sqlString = = "CREATE TABLE MyGuests ( id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
//            firstname VARCHAR(30) NOT NULL,
//            lastname VARCHAR(30) NOT NULL,
//            email VARCHAR(50),
//                    reg_date TIMESTAMP
//            )";
            PreparedStatement create =  con.prepareStatement("CREATE TABLE  tableNewOne ( id int(6)  UNSIGNED AUTO_INCREMENT PRIMARY KEY,first varchar(250), last varchar(250) )");
            create.executeUpdate();
        }catch (Exception e) {
            System.out.println( e);
        }
            finally {
            System.out.println(" Function has been completed");
            }

       }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/test" ;
            String userName = "root";
            String passWord = "abcd1234";
            Class.forName(driver);

            Connection con =  DriverManager.getConnection(url,userName,passWord);

            System.out.println("The connection is pass \n" + con );
            return con;
        } catch(Exception e){
            System.out.println("Connection fail : " + e);

        }


        return null;

    }

}
