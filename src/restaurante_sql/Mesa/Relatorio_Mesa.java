/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante_sql.Mesa;

import Classes.Conta;
import Classes.Mesa;
import Classes.Usuario;
import DAO.Conexao;
import DAO.ContaDAO;
import DAO.MesaDAO;
import DAO.UsuarioDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restaurante_sql.Garcom.*;
import restaurante_sql.Produto.*;
import restaurante_sql.TelaInicial_Gerente;

/**
 *
 * @author Hoope
 */
public class Relatorio_Mesa extends javax.swing.JFrame {

    /**
     * Creates new form Visualizar_Produto
     */
    public Relatorio_Mesa() {
        initComponents();
        setSize(690,430);
        setLocationRelativeTo(this);
        setResizable(false);
        setTitle("Restaurante");
        Data();
        AtualizaTableData();
    }
    float totall = 0;
    List<Conta> c = new ArrayList<>();
    
    public void Data(){
        Date d = new Date();
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        
        JtfDataIni.setText(data.format(d));
        JtfDataFi.setText(data.format(d));
    }
    
    public void AtualizaTable(){
        Connection con = Conexao.AbrirConexao();
        MesaDAO sql2 = new MesaDAO(con);
        List<Mesa> lista2 = new ArrayList<>();
        lista2 = sql2.ConsultaMesa();
        
        DefaultTableModel tbm = (DefaultTableModel) Tabela.getModel();
        
        while(tbm.getRowCount() > 0){
            tbm.removeRow(0);
        }
        int i = 0;
        
        float total = 0;
        for (Mesa tab : lista2) {
            //Object object = arr[j];
            
            ContaDAO sql = new ContaDAO(con);
            List<Conta> lista = new ArrayList<>();
            lista = sql.ConsultaVendas(tab.getId());
            
            for(Conta tab2:lista){
               // if(tab2.getIdmesa() == tab.getId() && ida == 0){
                    
//                    
                     
                    total += Float.parseFloat(tab2.getTotal());
//                    Tabela.setValueAt(total, i, 1);

//                
            }
            
            tbm.addRow(new String[1]);;
            Tabela.setValueAt(tab.getId(), i, 0);
             NumberFormat nf = NumberFormat.getCurrencyInstance();// Iniciando formatação
             nf.setMaximumFractionDigits(2);
             nf.setMinimumFractionDigits(2);
            Tabela.setValueAt(nf.format(total), i, 1);
            total = 0;
            i++;
            
            
        }
        

        
        
        
        
        
//        System.out.println("R$:"+nf.format(total));
        //Valor.setText(nf.format(total));
        Conexao.FecharConexao(con);
    }
    
    public void AtualizaTableAberta(){
        this.totall = 0;
        Connection con = Conexao.AbrirConexao();
        MesaDAO sql2 = new MesaDAO(con);
        List<Mesa> lista2 = new ArrayList<>();
        lista2 = sql2.ConsultaMesa();
        
        DefaultTableModel tbm = (DefaultTableModel) Tabela.getModel();
        
        while(tbm.getRowCount() > 0){
            tbm.removeRow(0);
        }
        int i = 0;
        
        float total = 0;
        for (Mesa tab : lista2) {
            //Object object = arr[j];
            
            ContaDAO sql = new ContaDAO(con);
            List<Conta> lista = new ArrayList<>();
            lista = sql.ConsultaVendasAberto(tab.getId());
            
            for(Conta tab2:lista){
               // if(tab2.getIdmesa() == tab.getId() && ida == 0){
                    
//                    
                     
                    total += Float.parseFloat(tab2.getTotal());
//                    Tabela.setValueAt(total, i, 1);

//                
            }
            
            tbm.addRow(new String[1]);;
            Tabela.setValueAt(tab.getId(), i, 0);
             NumberFormat nf = NumberFormat.getCurrencyInstance();// Iniciando formatação
             nf.setMaximumFractionDigits(2);
             nf.setMinimumFractionDigits(2);
            Tabela.setValueAt(nf.format(total), i, 1);
            this.totall += total;
            total = 0;
            i++;
            
            
        }
        

        
        
        
        
        
//        System.out.println("R$:"+nf.format(total));
        //Valor.setText(nf.format(total));
        Conexao.FecharConexao(con);
    }
    
    public void AtualizaTableData(){
        this.totall = 0;
        Connection con = Conexao.AbrirConexao();
        MesaDAO sql2 = new MesaDAO(con);
        List<Mesa> lista2 = new ArrayList<>();
        lista2 = sql2.ConsultaMesa();
        
        DefaultTableModel tbm = (DefaultTableModel) Tabela.getModel();
        
        while(tbm.getRowCount() > 0){
            tbm.removeRow(0);
        }
        int i = 0;
        
        float total = 0;
        String[] data_Ini = JtfDataIni.getText().split("/");
        String[] data_Venc = JtfDataFi.getText().split("/");
        for (Mesa tab : lista2) {
            //Object object = arr[j];
            
            ContaDAO sql = new ContaDAO(con);
            List<Conta> lista = new ArrayList<>();
            lista = sql.ConsultaVendas(tab.getId());
            
            for(Conta tab2:lista){
                String[] data_con = tab2.getData().split("/");
               
               
                      if(Integer.parseInt(data_Ini[2])<=Integer.parseInt(data_con[2]) && Integer.parseInt(data_con[2])<=Integer.parseInt(data_Venc[2]) && Integer.parseInt(data_Ini[1])<Integer.parseInt(data_con[1]) && Integer.parseInt(data_con[1])<Integer.parseInt(data_Venc[1])){
                          total += Float.parseFloat(tab2.getTotal());
                      }else if((Integer.parseInt(data_Ini[2])==Integer.parseInt(data_con[2]) && Integer.parseInt(data_Ini[1])==Integer.parseInt(data_con[1]) && Integer.parseInt(data_Ini[0])<=Integer.parseInt(data_con[0]) ) || (Integer.parseInt(data_Venc[2])==Integer.parseInt(data_con[2]) && Integer.parseInt(data_con[1])==Integer.parseInt(data_Venc[1]) && Integer.parseInt(data_Venc[0])>=Integer.parseInt(data_con[0]))){
                          total += Float.parseFloat(tab2.getTotal());
                      }
                    
            }
            
            tbm.addRow(new String[1]);;
            Tabela.setValueAt(tab.getId(), i, 0);
             NumberFormat nf = NumberFormat.getCurrencyInstance();// Iniciando formatação
             nf.setMaximumFractionDigits(2);
             nf.setMinimumFractionDigits(2);
            Tabela.setValueAt(nf.format(total), i, 1);
            this.totall += total;
            total = 0;
            i++;
            
            
        }
        

        
        
        
        
        
//        System.out.println("R$:"+nf.format(total));
        //Valor.setText(nf.format(total));
        Conexao.FecharConexao(con);
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
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        JtfDataIni = new javax.swing.JFormattedTextField();
        JtfDataFi = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(690, 400));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 102, 0));
        jButton5.setText("Tela Inicial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 102, 0));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Relatório Mesas");

        jLabel4.setBackground(new java.awt.Color(255, 102, 0));
        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 0));
        jLabel4.setText("Data I.:");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 102, 0));
        jButton2.setText("Gerar Relatório");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Tabela.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        Tabela.setForeground(new java.awt.Color(255, 102, 0));
        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mesa", "Total"
            }
        ));
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Tabela);

        jLabel5.setBackground(new java.awt.Color(255, 102, 0));
        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText("Data F.:");

        try {
            JtfDataIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JtfDataIni.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N

        try {
            JtfDataFi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JtfDataFi.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 102, 0));
        jButton3.setText("Contas em aberto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 102, 0));
        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JtfDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JtfDataFi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(0, 35, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(JtfDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtfDataFi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(55, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, 681, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AtualizaTableAberta();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AtualizaTableData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Document document = new Document();
          try {
              String[] data = JtfDataIni.getText().split("/");
              File pdf = new File("Mesa/Relatòrio_Mesas_"+data[0]+"_"+data[1]+"_"+data[2]+".pdf");
              pdf.createNewFile();
              PdfWriter.getInstance(document, new FileOutputStream("Mesa/Relatòrio_Mesas_"+data[0]+"_"+data[1]+"_"+data[2]+".pdf"));
              document.open();
              document.add(new Paragraph("Relatório das mesas: "+JtfDataIni.getText()));
              document.add(new Paragraph("Intervalo de ("+JtfDataIni.getText()+") à ("+JtfDataFi.getText()+")"));
              for (int i = 0; i < Tabela.getRowCount(); i++) {;
                  int mesa =  (int) Tabela.getValueAt(i, 0);
                  String Valor = (String) Tabela.getValueAt(i, 1);
                  document.add(new Paragraph("Mesa ("+mesa+") vendeu "+Valor));
              }
              NumberFormat nf = NumberFormat.getCurrencyInstance();// Iniciando formatação
                nf.setMaximumFractionDigits(2);
                nf.setMinimumFractionDigits(2);
              document.add(new Paragraph("Total: "+nf.format(this.totall)));
              
              
              
              JOptionPane.showMessageDialog(null,"Salvo!","Restaurante",JOptionPane.INFORMATION_MESSAGE);
              new TelaInicial_Gerente().setVisible(true);
              dispose();
            }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }
          document.close();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new TelaInicial_Gerente().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Relatorio_Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Relatorio_Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Relatorio_Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorio_Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Relatorio_Mesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField JtfDataFi;
    private javax.swing.JFormattedTextField JtfDataIni;
    private javax.swing.JTable Tabela;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
