/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import consts.Operations;
import domain.Polaganje;
import domain.Rok;
import domain.Student;
import java.util.ArrayList;
import logic.ClientController;
import models.ModelPolaganja;
import servercommunication.Communication;
import tranfer.Request;

/**
 *
 * @author Bosko
 */
public class FrmStudent extends javax.swing.JFrame {
    static Student student;
    /**
     * Creates new form FrmStudent
     */
    public FrmStudent(Student student) {
        initComponents();
        this.student=student;
        lblUlogovani.setText(student.getIme()+" "+student.getPrezime()+" "+student.getBrIndexa());
        ClientController.getInstance().setFs(this);
        ClientController.getInstance().setPrijavljeniStudent(student);
        srediTabele();
        srediCB();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUlogovani = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNeocenjeni = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOcenjeni = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cbRok = new javax.swing.JComboBox();
        btnOsvezi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblESPB = new javax.swing.JLabel();
        lblProsek = new javax.swing.JLabel();
        btnOdjaviSe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblNeocenjeni.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblNeocenjeni);

        tblOcenjeni.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblOcenjeni);

        jButton1.setText("Pogledaj rok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbRok.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnOsvezi.setText("Osvezi");
        btnOsvezi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsveziActionPerformed(evt);
            }
        });

        jLabel1.setText("Ukupno ESPB:");

        jLabel2.setText("Prosek:");

        btnOdjaviSe.setText("Odjavi se");
        btnOdjaviSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOdjaviSeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lblUlogovani, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(cbRok, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOdjaviSe, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProsek, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblESPB, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOsvezi, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cbRok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOdjaviSe))
                .addGap(18, 18, 18)
                .addComponent(lblUlogovani)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblESPB)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnOsvezi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblProsek))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Rok r=(Rok) cbRok.getSelectedItem();
        ClientController.getInstance().setIzabraniRok(r);
        FrmRok fr=new FrmRok(this, rootPaneCheckingEnabled);
        fr.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOsveziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsveziActionPerformed
        srediTabele();
    }//GEN-LAST:event_btnOsveziActionPerformed

    private void btnOdjaviSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOdjaviSeActionPerformed
        Request req=new Request();
        req.setOperation(Operations.ODJAVISE);
        req.setStudent(ClientController.getInstance().getPrijavljeniStudent());
        Communication.getInstance().sendRequest(req);
    }//GEN-LAST:event_btnOdjaviSeActionPerformed

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
            java.util.logging.Logger.getLogger(FrmStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new FrmStudent(student).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOdjaviSe;
    private javax.swing.JButton btnOsvezi;
    private javax.swing.JComboBox cbRok;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblESPB;
    private javax.swing.JLabel lblProsek;
    private javax.swing.JLabel lblUlogovani;
    private javax.swing.JTable tblNeocenjeni;
    private javax.swing.JTable tblOcenjeni;
    // End of variables declaration//GEN-END:variables

    private void srediTabele() {
        ModelPolaganja mn=new ModelPolaganja();
        tblNeocenjeni.setModel(mn);
        ModelPolaganja mn1=new ModelPolaganja();
        tblOcenjeni.setModel(mn1);
        Request req= new Request();
        req.setOperation(Operations.VRATIODGOVARAJUCAPOLAGANJA);
        req.setStudent(student);
        Communication.getInstance().sendRequest(req);
    }

    public void postaviTabelePolaganja(ArrayList<Polaganje> listaPolaganja) {
        int brojac=0;
        int espb=0;
        int zbir=0;
        double prosek=0;
        ModelPolaganja mn=(ModelPolaganja) tblNeocenjeni.getModel();
        ModelPolaganja mn1=(ModelPolaganja) tblOcenjeni.getModel();
        ArrayList<Polaganje> pomocnaNeocenjena=new ArrayList<>();
        ArrayList<Polaganje> pomocnaOcenjena=new ArrayList<>();
        for(Polaganje p:listaPolaganja){
            if(p.getOcena()>0){
                pomocnaOcenjena.add(p);
                if(p.getOcena()>5){
                    brojac++;
                    espb+=p.getTermin().getPredmet().getESPB();
                    zbir+=p.getOcena();
                }
            }else{
                pomocnaNeocenjena.add(p);
            }
            
        }
        if(espb>0){
            prosek=zbir/brojac;
            lblProsek.setText(""+prosek);
        }
        lblESPB.setText(""+espb);
        mn.postaviListu(pomocnaNeocenjena);
        mn1.postaviListu(pomocnaOcenjena);
    }

    private void srediCB() {
        cbRok.removeAllItems();
        Request req=new Request();
        req.setOperation(Operations.VRATIOVOGODISNJEROKOVE);
        req.setStudent(ClientController.getInstance().getPrijavljeniStudent());
        Communication.getInstance().sendRequest(req);
    }

    public void postaviCBRokova(ArrayList<Rok> listaRokova) {
        for(Rok r:listaRokova){
            cbRok.addItem(r);
        }
    }

    public void odjaviSe() {
        ClientController.getInstance().setFs(null);
        ClientController.getInstance().setPrijavljeniStudent(null);
        ClientController.getInstance().getFl().setVisible(true);
        this.dispose();
    }
}
