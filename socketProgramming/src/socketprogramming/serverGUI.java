/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprogramming;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Dinith
 */
public class serverGUI extends javax.swing.JFrame {
    
    private ItemMap item_map;
    int ItemMapCount;
    //static JLabel l, l2;
    
    JLabel[] labels;// = new JLabel[4];
    JLabel[] timerLabels;
    JLabel[] userLabels = new JLabel[100];
    String dHour, dMinute, dSeconds;
    DecimalFormat dFormat = new DecimalFormat("00");
    Timer timer;
    

    /**
     * Creates new form serverGUI
     */
    public serverGUI(ItemMap item_map) {
        this.item_map = item_map;
        initComponents();
        setInitialVal();
        visible_timer();
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DataPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bidding Server");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        DataPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout DataPanelLayout = new javax.swing.GroupLayout(DataPanel);
        DataPanel.setLayout(DataPanelLayout);
        DataPanelLayout.setHorizontalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );
        DataPanelLayout.setVerticalGroup(
            DataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        userPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userPanel.setPreferredSize(new java.awt.Dimension(445, 356));

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(userPanel);

        jTextPane1.setEditable(false);
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(serverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(serverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(serverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(serverGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //this.setVisible(true);
            }
        });
    }
    
    public void setInitialVal(){
        ItemMapCount = this.item_map.size();
        this.labels = new JLabel[ItemMapCount];
        int i = 0;
        int v = 10;
        for(Map.Entry<String, Item> pair: this.item_map.entrySet()){
            labels[i] = new JLabel();
            labels[i].setBounds(40,v,500,100);
            DataPanel.add(labels[i]);
            labels[i].setText(""+pair.getKey()+" =>\t\t "+pair.getValue().get_price());
            v = v+30;
            i++;
            //DataPanel.add(new JLabel("hello"));
        }
        
        this.timerLabels = new JLabel[ItemMapCount];
        int j = 0;
        int w = 10;
        for(Map.Entry<String, Item> pair: this.item_map.entrySet()){
            timerLabels[j] = new JLabel();
            timerLabels[j].setBounds(200,w,500,100);
            DataPanel.add(timerLabels[j]);
            int hour = pair.getValue().get_hour();
            int minute = pair.getValue().get_minute();
            int seconds = pair.getValue().get_seconds();
            dHour = dFormat.format(hour);
            dMinute = dFormat.format(minute);
            dSeconds = dFormat.format(seconds);
            timerLabels[j].setText(""+dHour+":"+dMinute+":"+dSeconds);
            w = w+30;
            j++;
            //DataPanel.add(new JLabel("hello"));
        }
        
    }
    
    private void visible_timer(){
        ItemMapCount = this.item_map.size();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int j = 0;
                for(Map.Entry<String, Item> pair: item_map.entrySet()){
                    int hour = pair.getValue().get_hour();
                    int minute = pair.getValue().get_minute();
                    int seconds = pair.getValue().get_seconds();
                    dHour = dFormat.format(hour);
                    dMinute = dFormat.format(minute);
                    dSeconds = dFormat.format(seconds);
                    timerLabels[j].setText(""+dHour+":"+dMinute+":"+dSeconds);
                    j++;
                    //DataPanel.add(new JLabel("hello"));
                }
            }
        });
    }
    
    public void setVal(){
        int i = 0;
        for (Map.Entry<String, Item> pair: this.item_map.entrySet()) {
            labels[i].setText(""+pair.getKey()+" =>\t "+pair.getValue().get_price());
            i++;
        }
        
    }
    
    int userCount = 0;
    int vUser = 10;
    
    public void setClientUser(String name){
        userLabels[userCount] = new JLabel();
        userLabels[userCount].setBounds(40,vUser,500,100);
        userPanel.add(userLabels[userCount]);
        userLabels[userCount].setText("Client "+name+" logged into the system");
        vUser = vUser+30;
        userCount++;
    }
    
    public void setPubsUser(String name){
        userLabels[userCount] = new JLabel();
        userLabels[userCount].setBounds(40,vUser,500,100);
        userPanel.add(userLabels[userCount]);
        userLabels[userCount].setText("Publisher "+name+" logged into the system");
        vUser = vUser+30;
        userCount++;
    }
    
    public void outPubUser(String name){
        userLabels[userCount] = new JLabel();
        userLabels[userCount].setBounds(40,vUser,500,100);
        userPanel.add(userLabels[userCount]);
        userLabels[userCount].setText("Publisher "+name+" logged out from the system");
        vUser = vUser+30;
        userCount++;
    }
    
    public void outUser(String name){
        userLabels[userCount] = new JLabel();
        userLabels[userCount].setBounds(40,vUser,500,100);
        userPanel.add(userLabels[userCount]);
        userLabels[userCount].setText("Client "+name+" logged out from the system");
        vUser = vUser+30;
        userCount++;
    }
    
    public void makeLog(String log){
        jTextPane1.setText(jTextPane1.getText()+"\n"+log);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DataPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
