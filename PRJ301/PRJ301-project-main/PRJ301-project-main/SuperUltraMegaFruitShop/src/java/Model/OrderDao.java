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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duonn
 */
public class OrderDao {

    public int createReturnID(Orders od) {
        try {
            String sql = "INSERT INTO [dbo].[orders]\n"
                    + "           ([a_id]\n"
                    + "           ,[totalPrice]\n"
                    + "           ,[shipping_id]\n"
                    + "           ,[note])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, od.getA_id());
            ps.setDouble(2, od.getTotalPrice());
            ps.setInt(3, od.getS_id());
            ps.setString(4, od.getNote());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ShipngDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
