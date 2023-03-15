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
public class ShipngDao {

    public int createReturnId(Shipng shipng) {
        try {
            String sql = "INSERT INTO [dbo].[shipping]\n"
                    + "           ([name]\n"
                    + "           ,[email]\n"
                    + "           ,[phone]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shipng.getName());
            ps.setString(2, shipng.getEmail());
            ps.setString(3, shipng.getPhone());
            ps.setString(4, shipng.getAddress());
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
