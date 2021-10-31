package tugasbesar;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static tugasbesar.Contact.textBuy6;
import static tugasbesar.Contact.textContact6;
import static tugasbesar.Contact.textHome6;
import static tugasbesar.Contact.textSignOut6;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Buy extends javax.swing.JFrame {
    int x,y;
    DefaultTableModel model = new DefaultTableModel();
    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    HashMap map = new HashMap();
    JasperReport jr;
    JasperPrint jp;
    JasperDesign jd;
    
    public void sizeKolom(){ 
        TableColumn column;
        tabelPhone.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
        column = tabelPhone.getColumnModel().getColumn(0); 
        column.setPreferredWidth(148); 
        column = tabelPhone.getColumnModel().getColumn(1); 
        column.setPreferredWidth(150); 
        column = tabelPhone.getColumnModel().getColumn(2); 
        column.setPreferredWidth(120); 
        column = tabelPhone.getColumnModel().getColumn(3); 
        column.setPreferredWidth(90); 
        column = tabelPhone.getColumnModel().getColumn(4); 
        column.setPreferredWidth(110); 
        column = tabelPhone.getColumnModel().getColumn(5); 
        column.setPreferredWidth(140); 
        column = tabelPhone.getColumnModel().getColumn(6); 
        column.setPreferredWidth(110);
    }
    
    private void cariData(String key){
        DefaultTableModel model;
        try{
            Object[] judul_kolom = {"Nama", "Alamat", "Nomor Handphone", "Kode Barang", "Merk Smartphone", "Type Smartphone", "Harga Barang"};
            model=new DefaultTableModel(null,judul_kolom);
            tabelPhone.setModel(model);
            
            con =(Connection)koneksi.getKoneksi();
            st = con.createStatement();
            model.getDataVector().removeAllElements();
            
            rs=st.executeQuery("SELECT * FROM datatabel WHERE nama LIKE '%"+key+"%' OR kodeBarang LIKE '%"+key+"%' OR noHp LIKE '%"+key+"%'");  
            while(rs.next()){
                Object[] data={
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("noHp"),
                    rs.getString("kodeBarang"),
                    rs.getString("smartphone"),
                    rs.getString("type"),
                    rs.getString("harga")
                };
               model.addRow(data);
            }                
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void showData(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Nomor Handphone");
        model.addColumn("Kode Barang");
        model.addColumn("Merk Smartphone");
        model.addColumn("Type Smartphone");
        model.addColumn("Harga Barang");
        
        try{
            String sql = "SELECT * FROM datatabel";
            con = (Connection) koneksi.getKoneksi();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), 
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }
            tabelPhone.setModel(model);
        }catch(SQLException e){
            System.out.println("ERROR :"+e.getMessage());
        }
    }
    
    
    public void Kosongkan(){
        namaPembeli.setText(null);
        alamatPembeli.setText(null);
        nohpPembeli.setText(null);
        kodeBrg.setText(null);
        combType.removeAllItems();
        combPhone.setSelectedItem(null);
        textHarga.setText(null);
        textSearch.setText(null);
    }
    
    public void tampil_combo(){
        try{
            String sql = "select * from datatabel";
            java.sql.Connection con = (Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                combPhone.addItem(rs.getString("smartphone"));
            }
            
            rs.last();
            int jumlahData = rs.getRow();
            rs.first();
            
        }catch(Exception e){
            
        }
    }
    public RenderingKanan kanan  = new RenderingKanan();    
    public RenderingKiri kiri  = new RenderingKiri();    
    public RenderingTengah tengah = new RenderingTengah();
    
    /**
     * Creates new form Product
     */
    public Buy() {
        initComponents();
        
        showData();
        sizeKolom();
        
        this.setLocationRelativeTo(null);
        
        Dimension screenSize = 
         Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = this.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }
            this.setLocation(
                    (screenSize.width - frameSize.width) / 2, 
                    (screenSize.height - frameSize.height) / 2);
    }
    
    public void viewData(){
            inputData id = new inputData();
            try{
                tabelPhone.setModel(DbUtils.resultSetToTableModel(id.updateData()));
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    
//    public void tampil(){
//        DBConnection koneksi = new DBConnection();
//        try{
//            con=koneksi.configDB();
//            st=con.createStatement();
//            rs=st.executeQuery("SELECT * FROM datatabel");
//            while(rs.next()){
//                
//            }
//        }catch(SQLException ex){
//            System.out.print(ex.getMessage());
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combPhone = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        combType = new javax.swing.JComboBox<>();
        textWarning = new javax.swing.JLabel();
        textHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        namaPembeli = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        alamatPembeli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nohpPembeli = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        kodeBrg = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cariData = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();
        cetakNota = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPhone = new javax.swing.JTable();
        textWarningDelete = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        textCyrix5 = new javax.swing.JLabel();

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buy ");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 180, 240));

        jPanel4.setBackground(new java.awt.Color(0, 180, 240));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel2.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        jLabel2.setText("Buy");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Smartphone :");

        combPhone.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        combPhone.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Phone . . .", "Samsung", "Oppo", "Xiaomi", "Apple", "Asus" }));
        combPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combPhoneActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Type :");

        combType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        textWarning.setForeground(java.awt.Color.red);
        textWarning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Masukkan Harga :");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Kode Barang :");

        namaPembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaPembeliActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Nama Pembeli :");

        alamatPembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatPembeliActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Alamat :");

        nohpPembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nohpPembeliActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Nomor HP :");

        btnSubmit.setBackground(java.awt.Color.green);
        btnSubmit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSubmit.setForeground(java.awt.Color.red);
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/submitIcon.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnReset.setBackground(java.awt.Color.red);
        btnReset.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnReset.setForeground(java.awt.Color.green);
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/resetIcon.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnEdit.setBackground(java.awt.Color.orange);
        btnEdit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/editIcon.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        kodeBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeBrgActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Rp");

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnDelete.setForeground(java.awt.Color.green);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delteIcon.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setBackground(java.awt.Color.green);
        btnSave.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSave.setForeground(java.awt.Color.red);
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/saveIcon.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setBackground(java.awt.Color.red);
        btnCancel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCancel.setForeground(java.awt.Color.green);
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/canceIcon.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cariData.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cariData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/searchIcon.png"))); // NOI18N
        cariData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cariDataMouseClicked(evt);
            }
        });

        textSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textSearchKeyReleased(evt);
            }
        });

        cetakNota.setBackground(java.awt.Color.green);
        cetakNota.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cetakNota.setForeground(java.awt.Color.red);
        cetakNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/printIcon.png"))); // NOI18N
        cetakNota.setText("CETAK NOTA !");
        cetakNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakNotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textHarga)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cariData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textSearch))
                            .addComponent(cetakNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combPhone, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kodeBrg)
                            .addComponent(textWarning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alamatPembeli)
                            .addComponent(nohpPembeli)
                            .addComponent(namaPembeli))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alamatPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nohpPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(combPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combType, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariData, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cetakNota, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 180, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel5.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        jLabel5.setText("Tabel");

        tabelPhone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPhone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPhoneMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPhone);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        textWarningDelete.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        textWarningDelete.setForeground(java.awt.Color.red);
        textWarningDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel10.setBackground(new java.awt.Color(0, 240, 221));
        jPanel10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel10MouseDragged(evt);
            }
        });
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel10MousePressed(evt);
            }
        });

        textCyrix5.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        textCyrix5.setText("CyrixPhone");
        textCyrix5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textCyrix5MousePressed(evt);
            }
        });

        textHome6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textHome6.setText("Home");
        textHome6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textHome6MousePressed(evt);
            }
        });

        textBuy6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textBuy6.setText("Buy");
        textBuy6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textBuy6MousePressed(evt);
            }
        });

        textContact6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textContact6.setText("Contact");
        textContact6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textContact6MousePressed(evt);
            }
        });

        textSignOut6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textSignOut6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logoutIcon.png"))); // NOI18N
        textSignOut6.setText("SignOut");
        textSignOut6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textSignOut6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textCyrix5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textHome6)
                .addGap(18, 18, 18)
                .addComponent(textBuy6)
                .addGap(18, 18, 18)
                .addComponent(textContact6)
                .addGap(18, 18, 18)
                .addComponent(textSignOut6)
                .addGap(42, 42, 42))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCyrix5)
                    .addComponent(textHome6)
                    .addComponent(textBuy6)
                    .addComponent(textContact6)
                    .addComponent(textSignOut6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(textWarningDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(409, 409, 409)
                        .addComponent(textWarningDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1552, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void combPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combPhoneActionPerformed
        // TODO add your handling code here:
        try{
            if(combPhone.getSelectedItem().equals("Samsung")){
                combType.removeAllItems();
                combType.addItem("Select Type . . .");
                combType.addItem("Galaxy A52 5G");
                combType.addItem("Galaxy A72");
                combType.addItem("Galaxy S21 Ultra 5G");
            }else if(combPhone.getSelectedItem().equals("Oppo")){
                combType.removeAllItems();
                combType.addItem("Select Type . . .");
                combType.addItem("Reno 6 Pro+ 5G");
                combType.addItem("Find X3 Pro");
                combType.addItem("F19 Pro+ 5G");
            }else if(combPhone.getSelectedItem().equals("Xiaomi")){
                combType.removeAllItems();
                combType.addItem("Select Type . . .");
                combType.addItem("Redmi Note 8");
                combType.addItem("Poco M3 Pro 5G");
                combType.addItem("Mi 11X Pro");
            }else if(combPhone.getSelectedItem().equals("Apple")){
                combType.removeAllItems();
                combType.addItem("Select Type . . .");
                combType.addItem("iPad Pro 11");
                combType.addItem("iPhone 12 Pro Max");
                combType.addItem("Watch Series 6 Aluminium");
            }else if(combPhone.getSelectedItem().equals("Asus")){
                combType.removeAllItems();
                combType.addItem("Select Type . . .");
                combType.addItem("Zenfone 8 Flip");
                combType.addItem("Zenfone 7 Pro");
                combType.addItem("ROG 5 Ultimate");
            }else{
                textWarning.setText("Please Select the Phone!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_combPhoneActionPerformed
    
    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabelPhone.getModel();
        if(namaPembeli.getText().trim().equals("") || alamatPembeli.getText().trim().equals("") || nohpPembeli.getText().trim().equals("") || kodeBrg.getText().trim().equals("") || combPhone.getSelectedItem().equals("") || combType.getSelectedItem().equals("") || textHarga.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null, "Data Tidak Lenkgap!");
        }else{
            model.addRow(new Object[]{namaPembeli.getText(),alamatPembeli.getText(),nohpPembeli.getText(),kodeBrg.getText(),combPhone.getSelectedItem(),combType.getSelectedItem(),textHarga.getText()});
        }
        sizeKolom();
        
//        tabelPhone.setValueAt(namaPembeli.getText(),x,0);
//        tabelPhone.setValueAt(alamatPembeli.getText(),x,1);
//        tabelPhone.setValueAt(nohpPembeli.getText(),x,2);
//        tabelPhone.setValueAt(kodeBarang.getText(),x,3);
//        tabelPhone.setValueAt(combPhone.getSelectedItem(),x,4);
//        tabelPhone.setValueAt(combType.getSelectedItem(),x,5);
//        tabelPhone.setValueAt(textHarga.getText(),x,6);
//        x=x+1;
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        showData();
        Kosongkan();
        sizeKolom();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

//        WORK
//        String ObjButtons[]={"yes","no"};
//        int PromptResult = JOptionPane.showOptionDialog(null, "Apakah Anda ingin Menghapus Data?", "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, ObjButtons, ObjButtons[1]);
//        
//        if(PromptResult == 0){
//            inputData id = new inputData();
//            id.kodeBarang=kodeBrg.getText();
//            try{
//                id.delete();
//                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
//                viewData();
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(null, "ERROR!");
//            }
//        }

        try{
            String sql = "DELETE FROM datatabel WHERE nama = '"+namaPembeli.getText()+"'";
            java.sql.Connection con = (Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Data!");
        }
        showData();
        sizeKolom();
        Kosongkan();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
            
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            inputData id = new inputData();
            id.nama = namaPembeli.getText();
            id.alamat = alamatPembeli.getText();
            id.noHp = nohpPembeli.getText();
            id.kodeBarang = kodeBrg.getText();
            id.smartphone = (String) combPhone.getSelectedItem();
            id.type = (String) combType.getSelectedItem();
            id.harga = textHarga.getText();
            
            try{
                id.save();
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
                Kosongkan();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Masih Kosong!");
                Kosongkan();
            }
            showData();
            sizeKolom();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void namaPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaPembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaPembeliActionPerformed

    private void alamatPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatPembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatPembeliActionPerformed

    private void nohpPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nohpPembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nohpPembeliActionPerformed

    private void kodeBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeBrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeBrgActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sql = "UPDATE datatabel SET nama='"+namaPembeli.getText()+"', alamat='"+alamatPembeli.getText()+"', noHp='"+nohpPembeli.getText()+"', kodeBarang='"+kodeBrg.getText()+"', smartphone='"+combPhone.getSelectedItem()+"', type='"+combType.getSelectedItem()+"', harga='"+textHarga.getText()+"' WHERE nama = '"+namaPembeli.getText()+"'";
            con = (Connection) koneksi.getKoneksi();
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Update Data!");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Update Data!");
        }
        showData();
        sizeKolom();
        Kosongkan();
//        try{
//            String sql = "INSERT datatabel set nama='"+namaPembeli.getText()+"' ,alamat='"+alamatPembeli.getText()+"' ,noHp='"+nohpPembeli.getText()+"' ,kodeBarang='"+kodeBrg.getText()+"' ,smartphone='"+combPhone.getSelectedItem()+"' ,type='"+combType.getSelectedItem()+"' ,harga='"+textHarga.getText()+"' On Duplicate Key Update nama = '"+namaPembeli.getText()+"' ,noHp = '"+nohpPembeli.getText()+"' ,kodeBarang = '"+kodeBrg.getText()+"' ,smartphone = '"+combPhone.getSelectedItem()+"' ,type = '"+combType.getSelectedItem()+"' ,harga = '"+textHarga.getText()+"'";
//            con = (Connection)koneksi.getKoneksi();
//            ps = con.prepareStatement(sql);
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "Data Berhasil diUpdate!");
//        }catch(HeadlessException | SQLException e){
//            JOptionPane.showMessageDialog(null, "ERROR");
//        }
        
        
//        try{
//            con = DriverManager.getConnection("jdbc:mysql://localhost/tugasbesarpbo","root","");
//            String sql = "UPDATE datatabel SET datatabel(nama,alamat,noHp,kodeBarang,smartphone,type,harga)VALUE(?,?,?,?,?,?,?)";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, namaPembeli.getText());
//            ps.setString(2, alamatPembeli.getText());
//            ps.setString(3, nohpPembeli.getText());
//            ps.setString(4, kodeBrg.getText());
//            ps.setString(5, (String) combPhone.getSelectedItem());
//            ps.setString(6, (String) combType.getSelectedItem());
//            ps.setString(7, textHarga.getText());
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(this, "Berhasil Update Data");
//        }catch(SQLException e){
//            JOptionPane.showMessageDialog(this, "Gagal Update Data");
//        }

//        DefaultTableModel model = new DefaultTableModel();
//        if(tabelPhone.getSelectedRow() == 1){
//            String nama = namaPembeli.getText();
//            String alamat = alamatPembeli.getText();
//            String noHp = nohpPembeli.getText();
//            String kodeBarang = kodeBrg.getText();
//            String smartphone = (String) combPhone.getSelectedItem();
//            String type = (String) combType.getSelectedItem();
//            String harga = textHarga.getText();
//            
//            model.setValueAt(nama, tabelPhone.getSelectedRow(), 0);
//            model.setValueAt(alamat, tabelPhone.getSelectedRow(), 1);
//            model.setValueAt(noHp, tabelPhone.getSelectedRow(), 2);
//            model.setValueAt(kodeBarang, tabelPhone.getSelectedRow(), 3);
//            model.setValueAt(smartphone, tabelPhone.getSelectedRow(), 4);
//            model.setValueAt(type, tabelPhone.getSelectedRow(), 5);
//            model.setValueAt(harga, tabelPhone.getSelectedRow(), 6);
//            
//            JOptionPane.showMessageDialog(this, "Data Berhasil Diupdate");
//        }else{
//            if(tabelPhone.getRowCount() == 0){
//                JOptionPane.showMessageDialog(this, "Tabel Masih Kosong!");
//            }else{
//                JOptionPane.showMessageDialog(this, "Pilih Salah Satu Baris untuk Diupdate");
//            }
//        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnCancelActionPerformed

    private void textCyrix5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCyrix5MousePressed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_textCyrix5MousePressed

    private void textHome6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textHome6MousePressed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_textHome6MousePressed

    private void textBuy6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textBuy6MousePressed
        // TODO add your handling code here:
        new Buy().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_textBuy6MousePressed

    private void textContact6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textContact6MousePressed
        // TODO add your handling code here:
        new Contact().setVisible(true);
        dispose();
    }//GEN-LAST:event_textContact6MousePressed

    private void textSignOut6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSignOut6MousePressed
        // TODO add your handling code here:
        new formLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_textSignOut6MousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void tabelPhoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPhoneMouseClicked
        // TODO add your handling code here:
        //        WORK
        int baris = tabelPhone.rowAtPoint(evt.getPoint());

        String nm = tabelPhone.getValueAt(baris, 0).toString();
        namaPembeli.setText(nm);

        String al = tabelPhone.getValueAt(baris, 1).toString();
        alamatPembeli.setText(al);

        String no = tabelPhone.getValueAt(baris, 2).toString();
        nohpPembeli.setText(no);

        String kb = tabelPhone.getValueAt(baris, 3).toString();
        kodeBrg.setText(kb);

        String sm = tabelPhone.getValueAt(baris, 4).toString();
        combPhone.setSelectedItem(sm);

        String ty = tabelPhone.getValueAt(baris, 5).toString();
        combType.setSelectedItem(ty);

        String hrg = tabelPhone.getValueAt(baris, 6).toString();
        textHarga.setText(hrg);
    }//GEN-LAST:event_tabelPhoneMouseClicked

    private void cariDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariDataMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cariDataMouseClicked

    private void textSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSearchKeyReleased
        // TODO add your handling code here:
        String key=textSearch.getText();
        System.out.println(key);  
        
        if(key!=""){
            cariData(key);
            sizeKolom();
        }else{
            showData();
            sizeKolom();
        }
        sizeKolom();
    }//GEN-LAST:event_textSearchKeyReleased

    private void cetakNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakNotaActionPerformed
        // TODO add your handling code here:

        try{
            String namaFile = "src/tugasbesar/reportPhone.jasper";
            String url = "jdbc:mysql://localhost:3306/";
            String db = "tugasbesarpbo";
            String user = "root";
            String pass = "";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url + db, user, pass);
            HashMap map = new HashMap();
            map.put("Nama", namaPembeli.getText());
            
            JasperPrint jp = JasperFillManager.fillReport(namaFile, map, con);
            JasperViewer.viewReport(jp, false);
        }catch(Exception ex){
            System.out.println(ex);
        }

    }//GEN-LAST:event_cetakNotaActionPerformed

    private void jPanel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel10MousePressed

    private void jPanel10MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_jPanel10MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Buy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatPembeli;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel cariData;
    private javax.swing.JButton cetakNota;
    private javax.swing.JComboBox<String> combPhone;
    private javax.swing.JComboBox<String> combType;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kodeBrg;
    private javax.swing.JTextField namaPembeli;
    private javax.swing.JTextField nohpPembeli;
    private javax.swing.JTable tabelPhone;
    public static final javax.swing.JLabel textBuy6 = new javax.swing.JLabel();
    public static final javax.swing.JLabel textContact6 = new javax.swing.JLabel();
    private javax.swing.JLabel textCyrix5;
    private javax.swing.JTextField textHarga;
    public static final javax.swing.JLabel textHome6 = new javax.swing.JLabel();
    private javax.swing.JTextField textSearch;
    public static final javax.swing.JLabel textSignOut6 = new javax.swing.JLabel();
    private javax.swing.JLabel textWarning;
    private javax.swing.JLabel textWarningDelete;
    // End of variables declaration//GEN-END:variables
}
