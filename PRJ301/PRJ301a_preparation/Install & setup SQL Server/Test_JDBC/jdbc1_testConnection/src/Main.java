import java.sql.*;
public class Main {

    public static void main(String[] args) {
   
       String xServer = "localhost";
       String xPort = "1433";
       String xDbName = "studentDB";
       //String xUrl = "jdbc:sqlserver://localhost:1433;databaseName = studentDB";
       String xUrl = "jdbc:sqlserver://" + xServer + ":" + xPort + ";databaseName = " + xDbName;
    
       String xUser = "sa";
       String xPW = "sa";
       Connection con = null; 
       try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         con = DriverManager.getConnection(xUrl,xUser,xPW);
         System.out.println("Connection successful.");
       }
       catch(Exception e) {
         System.out.println("Connection failed.");
       }
      
    }
 }
