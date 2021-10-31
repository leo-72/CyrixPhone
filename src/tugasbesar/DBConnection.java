package tugasbesar;

import tugasbesar.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnection {
    private static Connection MySQLConfig;
    
    Connection con;
    Statement stm;
    
    public void config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tugasbesarpbo", "root", "");
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal!"+e.getMessage());
        }
    }
    
    public static Connection configDB() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/tugasbesarpbo";
        String user = "root";
        String pass = "";
        return MySQLConfig;
    }
    
    
}
