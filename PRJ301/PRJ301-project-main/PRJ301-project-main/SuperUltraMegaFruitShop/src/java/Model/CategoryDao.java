/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author duonn
 */
public class CategoryDao {

    public List<Categorise> getAllCate() {
        try {
            List<Categorise> list = new ArrayList<>();
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "select * from categories ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categorise cate = new Categorise(rs.getInt(1), rs.getString(2));
                list.add(cate);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    

    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        List<Categorise> listSkills = dao.getAllCate();
        for (Categorise listSkill : listSkills) {
            System.out.println(listSkill);
        }

    }

    public String getCateNameById(int c_id) {
        switch (c_id) {
            case 1:
                return "Frozen";
            case 2:
                return "Fresh";
            case 3:
                return "Dried";
            default:
                throw new AssertionError();
        }
    }
}
