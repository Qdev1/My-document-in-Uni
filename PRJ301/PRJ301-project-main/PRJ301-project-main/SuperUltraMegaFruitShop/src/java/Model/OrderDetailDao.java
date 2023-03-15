/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duonn
 */
public class OrderDetailDao {

    public void saveCart(int OrderId, Map<Integer, Cartt> carts) {
        try {
            String sql = "INSERT INTO [dbo].[order_detail]\n"
                    + "           ([order_id]\n"
                    + "           ,[productName]\n"
                    + "           ,[productImage]\n"
                    + "           ,[productPrice]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, OrderId);
            for (Map.Entry<Integer, Cartt> entry : carts.entrySet()) {
                Integer productId = entry.getKey();
                Cartt cart = entry.getValue();
                ps.setString(2, cart.getProduct().getP_name());
                ps.setString(3, cart.getProduct().getP_img());
                ps.setDouble(4, cart.getProduct().getP_price());
                ps.setInt(5, cart.getQuantity());
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            Logger.getLogger(ShipngDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
