/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author user
 */
public class inputData {
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null; 
    
    public String sql,nama,alamat,noHp,kodeBarang,smartphone,type,harga;
    
    public void save() throws SQLException{
        con = koneksi.getKoneksi();
        sql = "INSERT INTO datatabel(nama,alamat,noHp,kodeBarang,smartphone,type,harga)VALUE(?,?,?,?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, nama);
        ps.setString(2, alamat);
        ps.setString(3, noHp);
        ps.setString(4, kodeBarang);
        ps.setString(5, smartphone);
        ps.setString(6, type);
        ps.setString(7, harga);
        ps.execute();
        ps.close();
    }
    
//    public void save() throws SQLException{
//        String sql = "INSERT INTO datatabel(nama,alamat,noHp,kodeBarang,smartphone,type,harga)VALUE(?,?,?,?,?,?,?)";
//        con = DriverManager.getConnection("jdbc:mysql://localhost/tugasbesarpbo","root","");
//        ps = con.prepareStatement(sql);
//        ps.setString(1, namaPembeli.getText());
//    }
 
    public ResultSet updateData()throws SQLException{
        con = koneksi.getKoneksi();
        sql = "select * from datatabel";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }
    
    public void delete() throws SQLException{
        con = koneksi.getKoneksi();
        String sql = "delete from datatabel where kodeBarang=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, kodeBarang);
            ps.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Tersambung");
        }
    }
    
    public ResultSet cariData(String nama)throws SQLException{
        con = (Connection) koneksi.getKoneksi();
        sql = "SELECT * FROM datatabel nama like ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, nama);
        rs = ps.executeQuery();
        return rs;
    }
}
