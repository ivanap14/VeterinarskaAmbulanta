/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.form;


import rs.ac.bg.fon.ambulanta.domain.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.sa.ambulanta.model.*;
import rs.ac.bg.fon.sa.ambulanta.threads.*;
import rs.ac.bg.fon.sa.ambulanta.threads.side.*;

/**
 *
 * @author Korisnik
 */
public class FrmMain extends javax.swing.JFrame {
    
    ServerThread serverThread;
    TableThread tableThread;
   
    
    public FrmMain() {
        initComponents();
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/image/logo.png")).getImage());
        setLocationRelativeTo(null);
        setTitle("Server");
        
        setTable();
        
        lblOnOffServer.setText("Server je isključen");
        //lblOnOffServer.setForeground(Color.red);
        lblOnOffServer.setForeground(new Color(178, 34, 34));
        btnStopServer.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStartServer = new javax.swing.JButton();
        btnStopServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoggedInUsers = new javax.swing.JTable();
        lblOnOffServer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStartServer.setText("Pokreni server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        btnStopServer.setText("Zaustavi server");
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopServerActionPerformed(evt);
            }
        });

        tblLoggedInUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ulogovani korisnici "
            }
        ));
        jScrollPane1.setViewportView(tblLoggedInUsers);

        lblOnOffServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOnOffServer.setText("       ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnStartServer)
                        .addGap(87, 87, 87)
                        .addComponent(btnStopServer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(lblOnOffServer, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer)
                    .addComponent(btnStopServer))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOnOffServer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
        try {
            if(serverThread==null || !serverThread.isAlive()){

                    serverThread=new ServerThread();
                    serverThread.start();
                    
                    tableThread=new TableThread(this);
                    tableThread.start();

                    btnStartServer.setEnabled(false);
                    btnStopServer.setEnabled(true);
                    lblOnOffServer.setText("Server je uključen");
                    lblOnOffServer.setForeground(new Color(34, 139, 34));
            }
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Neuspesno pokretanje servera!", "Greska", JOptionPane.ERROR_MESSAGE);
        }

        
    }//GEN-LAST:event_btnStartServerActionPerformed

    private void btnStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopServerActionPerformed
        try {
            if(!serverThread.getServerSocket().isClosed() && serverThread.getServerSocket().isBound()){
                    
                    serverThread.stopServer();

                    btnStartServer.setEnabled(true);
                    btnStopServer.setEnabled(false);
                    lblOnOffServer.setText("Server je isključen");

                    lblOnOffServer.setForeground(new Color(178, 34, 34));
            }
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Neuspesno zaustavljanje servera!", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStopServerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartServer;
    private javax.swing.JButton btnStopServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblOnOffServer;
    private javax.swing.JTable tblLoggedInUsers;
    // End of variables declaration//GEN-END:variables

    private void setTable() {
        List<Veterinarian> vets=new ArrayList<>();
        tblLoggedInUsers.setModel(new TableModelLoggedInUsers(vets));
    }

    public void refreshTable() {
        TableModelLoggedInUsers tm = (TableModelLoggedInUsers) tblLoggedInUsers.getModel();
        
        List<Veterinarian> vets = serverThread.getLoggedInClients();

        tm.setVeterinarians(vets);
 
    }
}
