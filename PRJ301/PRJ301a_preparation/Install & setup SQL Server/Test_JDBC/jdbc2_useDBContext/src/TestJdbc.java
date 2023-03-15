import java.sql.*;
public class TestJdbc extends DBContext {
    Connection con;
    public TestJdbc() {
       con = connection; 
    }
    public void display() {
       PreparedStatement ps;
       ResultSet rs;
       String xSql = "select * from student";
       String xRollno, xName; float xMark;
       try {
         ps = con.prepareStatement(xSql);
         rs = ps.executeQuery();
         while(rs.next()) {
            xRollno = rs.getString("rollno");
            xName = rs.getString("name");
            xMark = rs.getFloat("mark");
            System.out.println(xRollno + " " + xName + " " + xMark);
         }
        rs.close();
        ps.close();
        con.close();
       }
       catch(Exception e) {
         System.out.println(e.getMessage());
       }
        
    }
}
