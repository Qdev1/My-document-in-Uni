/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duonn
 */
public class ProductDAO {

    Connection cnn; //ket noi co so du lieu(CSDL)
    Statement stm; //thuc hien cac cau lenh sql
    ResultSet rs;

    public ProductDAO() {
        connectDB();
    }

    public List<Products> getTop() {
        try {
            List<Products> list = new ArrayList<>();
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT TOP(3) * FROM products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<Products> getAllProducts() {
        try {
            List<Products> list = new ArrayList<>();
            //mo ket noi
            String sql = "SELECT * FROM products";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public List<Products> getProductsByCategoryId(int categoryId) {
        List<Products> list = new ArrayList<>();
        try {
            String sql = "select * from products where category_id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Products> search(String keyword) {
        List<Products> list = new ArrayList<>();
        try {
            String sql = "select * from products  where [product_name] like ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Products getProductById(int productId) {
        try {
            String sql = "select *  from Products where product_id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                return product;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Products> getProductsWithPagging(int page, int PAGE_SIZE) {
        List<Products> list = new ArrayList<>();
        try {
            String sql = "select * from products order by product_id \n"
                    + "offset (?-1)*? row fetch next ? rows only ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int getTotalProducts() {
        try {
            String sql = "select count(product_id) from products";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private void connectDB() {
        try {
            cnn = (new DBContext()).getConnection();
            System.out.println("Connect successfully");
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }

    public List<Products> getProductRandom(int cateID,int productID) {
        try {
            List<Products> list = new ArrayList<>();
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT TOP 3 *\n"
                    + "FROM products\n"
                    + "where category_id='"+cateID+"'\n"
                    + "and product_id <> '"+productID+"'\n"
                    + "ORDER BY NEWID()";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    public List<Products> getProductRandom() {
        try {
            List<Products> list = new ArrayList<>();
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT TOP 3 *\n"
                    + "FROM products\n"
                    + "ORDER BY NEWID()";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
                        rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
                list.add(product);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
        
        
    
//    public static void main(String[] args) {
//        ProductDAO dao = new ProductDAO();
//        List<Products> listAllProducts = dao.getProductRandom();
//        for (Products listAllProduct : listAllProducts) {
//            System.out.println(listAllProduct);
//        }
//
//    }

    public void deleteProduct(String id) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "Delete from products where product_id='" + id + "'";
            stm.execute(strDelete);

        } catch (Exception e) {
            System.out.println("DelbyID error " + e.getMessage());
        }
    }

    public void updateProduct(String p_id, String pname, String qty, String cate, String date_manu, String Date_Exp, String price, String img) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "update products set product_name='" + pname + "',product_quantity='"+qty+"',category_id='"+cate+"',date_manufacture='"+date_manu+"',date_expriration='"+Date_Exp+"',list_price='"+price+"',product_img='"+img+"' where product_id='" + p_id + "'";
            System.out.println(strUpdate);
            stm.execute(strUpdate);
            System.out.println("Update success");
        } catch (Exception e) {
            System.out.println("update Error:" + e.getMessage());
        }
    }
    

}
