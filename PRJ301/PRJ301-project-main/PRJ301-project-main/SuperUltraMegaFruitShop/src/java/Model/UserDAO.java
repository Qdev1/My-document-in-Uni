/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nezio
 */
public class UserDAO {

//    static final String account = "user01";
    public User getUserInfo(String account) {
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (account.contains("@")) {
                sql = "select * from account where email = '" + account + "'";
            } else {
                sql = "select * from account where username ='" + account + "'";
            }
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(5));
                return u;
            }
        } catch (Exception e) {
            System.out.println("Check credential err");
        }

        return null;
    }

    Connection cnn; //ket noi co so du lieu(CSDL)
    Statement stm; //thuc hien cac cau lenh sql
    ResultSet rs;

    public boolean checkCredential(String account, String password) {
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (account.contains("@")) {
                sql = "select * from account where email = '" + account + "' and password ='" + password + "'";
            } else {
                sql = "select * from account where username ='" + account + "' and password ='" + password + "'";
            }
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Check credential err");
        }

        return false;

    }

    public UserDAO() {
        connectDB();
    }

    private void connectDB() {
        try {
            cnn = (new DBContext()).getConnection();
            System.out.println("Connect successfully");
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }

    public boolean checkIfAccountExist(String account) {
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (account.contains("@")) {
                sql = "select * from account where email = '" + account + "'";
            } else {
                sql = "select * from account where username ='" + account + "'";
            }
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Check credential err");
        }

        return false;
    }

    int createID() {
        int id = 0;
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            sql = "select * from account";

            rs = stm.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("a_id");
            }
        } catch (Exception e) {
            System.out.println("Create ID error");
        }

        return id + 1;
    }

    public void saveCredential(String username, String email, String password) {
        int id = createID();
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            sql = "insert into account (a_id,username,password, email,role) values ('" + id + "',N'" + username + "',N'" + password + "',N'" + email + "',N'customer')";

            stm.execute(sql);
        } catch (Exception e) {
            System.out.println("Save error:" + e.getMessage());
        }
    }

    public boolean checkPhone(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
                return false;
            }

        }
        return true;
    }

    public void saveCredential(String username, String password, String fullname, String phone, String email, String address, String role) {
        int id = createID();
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            sql = "insert into account values ('" + id + "',N'" + username + "',N'" + password + "',N'" + fullname + "',N'" + phone + "',N'" + email + "',N'" + address + "',N'" + role + "')";

            stm.execute(sql);
        } catch (Exception e) {
            System.out.println("Save error:" + e.getMessage());
        }
    }

    public void updatePass(String email, String newPass) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "update account set password='" + newPass + "' where email='" + email + "'";
            stm.execute(strUpdate);
            System.out.println("Update pass success");
        } catch (Exception e) {
            System.out.println("changepass Error:" + e.getMessage());
        }
    }

    public String checkRole(String account) {
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (account.contains("@")) {
                sql = "select * from account where email = '" + account + "'";
            } else {
                sql = "select * from account where username ='" + account + "'";
            }
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("role");
            }

        } catch (Exception e) {
            System.out.println("Check credential err");
        }
        return "";
    }

    public ArrayList<User> getAll() {
        ArrayList<User> list = new ArrayList<User>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from account";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id, username, password, fullname, phone, email, city, role;
                id = String.valueOf(rs.getInt("a_id"));
                username = rs.getString("username");
                password = rs.getString("password");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                email = rs.getString("email");
                city = rs.getString("city");
                role = rs.getString("role");
                User u = new User(id, username, password, fullname, email, city, role, phone);
                list.add(u);
                System.out.println(u.toString());
            }
        } catch (Exception e) {
            System.out.println("getAll err: " + e.getMessage());
        }

        return list;
    }

    public void deleteUser(String id) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "Delete from account where a_id='" + id + "'";
            stm.execute(strDelete);

        } catch (Exception e) {
            System.out.println("DelbyID error " + e.getMessage());
        }
    }
    public User getUserById(String id) {
        try {
            String sql;
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sql = "select * from account where a_id ='" + id + "'";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(5));
                return u;
            }
        } catch (Exception e) {
            System.out.println("getByidErr"+e.getMessage());
        }

        return null;
    }

   

    public void updateUser(String uID, String Username, String Fullname, String phone, String email, String city, String role) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "update account set username='" + Username + "',fullname='"+Fullname+"',phone='"+phone+"',email='"+email+"',city='"+city+"',role='"+role+"' where a_id='" + uID + "'";
            stm.execute(strUpdate);
            System.out.println("Update success");
        } catch (Exception e) {
            System.out.println("update Error:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User u = dao.getUserInfo("user03");
        System.out.println(u.getUserID());
        int i = Integer.parseInt(u.getUserID());
        System.out.println(i);
    }

}
