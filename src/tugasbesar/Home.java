/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author user
 */
public class Home extends javax.swing.JFrame {
    
//    public void tanggal(){
//        Date dt = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        textTgl1.setText(sdf.format(dt));
//    }
    
//   
    
    void showDate(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMMM yyyy");
        textDate.setText(sdf.format(dt));
    }
    
    void showTime(){
        new Timer (0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                textTime.setText(sdf.format(dt));
            }
        }).start();  
    }
    
    int x=0,y;
    /**
     * Creates new form Menu
     */
    public Home() {
        initComponents();
        
        showDate();
        showTime();
        
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
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textDate = new javax.swing.JLabel();
        textTime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        textCyrix = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home ??? CyrixPhone");
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 199, 255));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 48)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("CyrixPhone");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("\"Menjual mulai dari Smartphone Merk Apapun hingga Aksesorisya\"");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/gambarHome.png"))); // NOI18N

        textDate.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        textDate.setForeground(java.awt.Color.red);
        textDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textTime.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        textTime.setForeground(java.awt.Color.red);
        textTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnUser.setBackground(new java.awt.Color(199, 38, 30));
        btnUser.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exclamationIcon.png"))); // NOI18N
        btnUser.setText("Data User");
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        showUser.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        showUser.setForeground(java.awt.Color.red);
        showUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        showUser.setEnabled(false);
        showUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showUserActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 240, 221));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        textCyrix.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        textCyrix.setText("CyrixPhone");
        textCyrix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textCyrixMousePressed(evt);
            }
        });

        textHome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textHome.setText("Home");
        textHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textHomeMousePressed(evt);
            }
        });

        textBuy.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textBuy.setText("Buy");
        textBuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textBuyMousePressed(evt);
            }
        });

        textContact.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textContact.setText("Contact");
        textContact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textContactMousePressed(evt);
            }
        });

        textSignOut.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textSignOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logoutIcon.png"))); // NOI18N
        textSignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textSignOutMousePressed(evt);
            }
        });

        textWelcome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textWelcome.setForeground(java.awt.Color.red);
        textWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textCyrix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textHome)
                .addGap(18, 18, 18)
                .addComponent(textBuy)
                .addGap(18, 18, 18)
                .addComponent(textContact)
                .addGap(18, 18, 18)
                .addComponent(textSignOut)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCyrix)
                    .addComponent(textHome)
                    .addComponent(textBuy)
                    .addComponent(textContact)
                    .addComponent(textSignOut)
                    .addComponent(textWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textTime, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(227, 227, 227)
                                        .addComponent(jLabel1)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUser, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(showUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)))))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTime, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(showUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUser)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textCyrixMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCyrixMousePressed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_textCyrixMousePressed

    private void textSignOutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSignOutMousePressed
        // TODO add your handling code here:
        new formLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_textSignOutMousePressed

    private void textBuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textBuyMousePressed
        // TODO add your handling code here:
        new Buy().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_textBuyMousePressed

    private void textContactMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textContactMousePressed
        // TODO add your handling code here:
        new Contact().setVisible(true);
        dispose();
    }//GEN-LAST:event_textContactMousePressed

    private void textHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textHomeMousePressed
        // TODO add your handling code here:
        new Home().setVisible(true);
        dispose();
    }//GEN-LAST:event_textHomeMousePressed

    private void showUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showUserActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        // TODO add your handling code here:
        new dataUser().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnUserActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_jPanel1MouseDragged

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JButton btnUser = new javax.swing.JButton();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static final javax.swing.JTextField showUser = new javax.swing.JTextField();
    public static final javax.swing.JLabel textBuy = new javax.swing.JLabel();
    public static final javax.swing.JLabel textContact = new javax.swing.JLabel();
    private javax.swing.JLabel textCyrix;
    private javax.swing.JLabel textDate;
    public static final javax.swing.JLabel textHome = new javax.swing.JLabel();
    public static final javax.swing.JLabel textSignOut = new javax.swing.JLabel();
    private javax.swing.JLabel textTime;
    public static final javax.swing.JLabel textWelcome = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
