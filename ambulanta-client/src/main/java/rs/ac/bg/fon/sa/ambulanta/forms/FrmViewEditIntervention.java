/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.forms;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.sa.ambulanta.model.*;
import rs.ac.bg.fon.sa.ambulanta.threads.*;
import rs.ac.bg.fon.sa.ambulanta.controller.*;



/**
 *
 * @author Korisnik
 */
public class FrmViewEditIntervention extends javax.swing.JDialog {
    
    int mode;
    Intervention intervention;
    EditInterventionThread editThread;
    
    public FrmViewEditIntervention(Frame parent, boolean modal, int mode, Intervention intervention) throws Exception{
        super(parent, modal);
        initComponents();
        
        this.mode=mode;
        this.intervention=intervention;
        
        
        prepareView();// priprema formu u zavisnosti od moda
        
        editThread = new EditInterventionThread(this);
        editThread.start();
        
        setLocationRelativeTo(null);
    }

    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIDIntervention = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFirstnameVeterinarian = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtLastnameVeterinarian = new javax.swing.JTextField();
        txtPhoneVeterinarian = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIdVeterinarian = new javax.swing.JTextField();
        txtBirthdayVeterinarian = new javax.swing.JTextField();
        cbVeterinarian = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtSpeciesAnimal = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtYearOfBirthAnimal = new javax.swing.JTextField();
        txtGenderAnimal = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtIdAnimal = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNameAnimal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtAddressOwner = new javax.swing.JTextField();
        txtEmailOwner = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPhoneOwner = new javax.swing.JTextField();
        txtNameOwner = new javax.swing.JTextField();
        txtJmbgOwner = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chkBoxLoyalityCardOwner = new javax.swing.JCheckBox();
        cbAnimal = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServices = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        txtDiscountForLoyalty = new javax.swing.JTextField();
        txtDiscountForNumberOfServices = new javax.swing.JTextField();
        txtTotalAmountWithoutDiscount = new javax.swing.JTextField();
        txtTotalAmountWithDiscount = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        cbService = new javax.swing.JComboBox();
        btnAddService = new javax.swing.JButton();
        btnDeleteService = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("ID intervencije:");

        jLabel2.setText("Datum:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Usluge:");

        jLabel7.setText("Napomena:");

        jLabel8.setText("Popust za lojalnost:");

        jLabel9.setText("Popust za broj usluga:");

        jLabel10.setText("Ukupan iznos bez popusta:");

        jLabel11.setText("Ukupan iznos sa popustom:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setPreferredSize(new java.awt.Dimension(850, 274));

        jPanel1.setBackground(new java.awt.Color(239, 255, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Veterinar:");

        jLabel26.setText("prezime:");

        jLabel25.setText("datum rođenja:");

        jLabel22.setText("ID:");

        jLabel24.setText("telefon:");

        jLabel23.setText("ime:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhoneVeterinarian, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtIdVeterinarian, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtBirthdayVeterinarian, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtFirstnameVeterinarian, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtLastnameVeterinarian, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbVeterinarian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtIdVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtFirstnameVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtLastnameVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtBirthdayVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPhoneVeterinarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(239, 255, 253));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Životinja:");

        jLabel27.setText("vrsta:");

        jLabel28.setText("godina rođenja:");

        jLabel29.setText("pol:");

        jLabel30.setText("ID:");

        jLabel31.setText("nadimak:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel32.setText("email:");

        jLabel33.setText("adresa:");

        jLabel18.setText("jmbg:");

        jLabel21.setText("telefon:");

        jLabel19.setText("ime i prezime:");

        jLabel20.setText("kartica lojalnosti:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Vlasnik:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAnimal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSpeciesAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYearOfBirthAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGenderAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmailOwner, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(chkBoxLoyalityCardOwner)
                            .addComponent(txtAddressOwner)
                            .addComponent(txtPhoneOwner)
                            .addComponent(txtNameOwner)
                            .addComponent(txtJmbgOwner))
                        .addGap(14, 14, 14))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtIdAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtNameAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtSpeciesAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtYearOfBirthAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtGenderAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAddressOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel18)
                                            .addComponent(txtJmbgOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19)
                                            .addComponent(txtNameOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel21))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(chkBoxLoyalityCardOwner)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPhoneOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel32))
                                    .addComponent(txtEmailOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel33)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tblServices.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblServices);

        txtNotes.setBackground(new java.awt.Color(242, 242, 242));
        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane2.setViewportView(txtNotes);

        jLabel12.setText("%");

        jLabel13.setText("%");

        jLabel14.setText("din");

        jLabel15.setText("din");

        btnClose.setText("Zatvori");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnAddService.setText("Dodaj");
        btnAddService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddServiceActionPerformed(evt);
            }
        });

        btnDeleteService.setText("Obriši");
        btnDeleteService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteServiceActionPerformed(evt);
            }
        });

        btnSave.setText("Sačuvaj");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel16.setText("Intervencija");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDate)
                            .addComponent(txtIDIntervention, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 738, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnAddService)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteService))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(312, 312, 312))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiscountForLoyalty)
                    .addComponent(txtDiscountForNumberOfServices)
                    .addComponent(txtTotalAmountWithoutDiscount)
                    .addComponent(txtTotalAmountWithDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(552, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIDIntervention, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddService)
                    .addComponent(btnDeleteService))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDiscountForLoyalty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDiscountForNumberOfServices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTotalAmountWithoutDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTotalAmountWithDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );

        pack();
    }// </editor-fold>                        

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }                                        

    private void btnAddServiceActionPerformed(java.awt.event.ActionEvent evt) {                                              
        try {
            if(cbService.getSelectedIndex()!=-1){
            Service service = (Service) cbService.getSelectedItem();
            TableModelInterventionItem tmi = (TableModelInterventionItem) tblServices.getModel();
            tmi.addInterventionItem(intervention, service, intervention.getAnimal());
            } 
            else throw new Exception("Izaberite uslugu iz padajućeg menija!");
               
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }                                             

    private void btnDeleteServiceActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        try {
            int rowSelected = tblServices.getSelectedRow();
            TableModelInterventionItem tmi = (TableModelInterventionItem) tblServices.getModel();
            if(rowSelected>=0){
                tmi.removeInterventionItem(rowSelected, intervention.getAnimal());
            } else {
                throw new Exception("Niste selektovali uslugu!");
            }
        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }                                                

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                        
       try{
            intervention.setNotes(txtNotes.getText());

            Intervention intervent = Controller.getInstance().editIntervention(intervention);

            JOptionPane.showMessageDialog(this, "Sistem je zapamtio intervenciju ["+intervent.getId()+"]", "Informacija", JOptionPane.INFORMATION_MESSAGE);

            this.setVisible(false);

            FrmViewEditIntervention viewInterventionForm = new FrmViewEditIntervention( (Frame)this.getParent(),true,FormMode.FORM_VIEW_MODE, intervent);
            viewInterventionForm.setVisible(true);
        
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti intervenciju", "Greška", JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
        }
    }                                       


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAddService;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDeleteService;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbAnimal;
    private javax.swing.JComboBox cbService;
    private javax.swing.JComboBox cbVeterinarian;
    private javax.swing.JCheckBox chkBoxLoyalityCardOwner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblServices;
    private javax.swing.JTextField txtAddressOwner;
    private javax.swing.JTextField txtBirthdayVeterinarian;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDiscountForLoyalty;
    private javax.swing.JTextField txtDiscountForNumberOfServices;
    private javax.swing.JTextField txtEmailOwner;
    private javax.swing.JTextField txtFirstnameVeterinarian;
    private javax.swing.JTextField txtGenderAnimal;
    private javax.swing.JTextField txtIDIntervention;
    private javax.swing.JTextField txtIdAnimal;
    private javax.swing.JTextField txtIdVeterinarian;
    private javax.swing.JTextField txtJmbgOwner;
    private javax.swing.JTextField txtLastnameVeterinarian;
    private javax.swing.JTextField txtNameAnimal;
    private javax.swing.JTextField txtNameOwner;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtPhoneOwner;
    private javax.swing.JTextField txtPhoneVeterinarian;
    private javax.swing.JTextField txtSpeciesAnimal;
    private javax.swing.JTextField txtTotalAmountWithDiscount;
    private javax.swing.JTextField txtTotalAmountWithoutDiscount;
    private javax.swing.JTextField txtYearOfBirthAnimal;
    // End of variables declaration                   

    private void prepareView() throws Exception{
        try {
            if(mode==FormMode.FORM_VIEW_MODE){

                setFields();
                
                cbVeterinarian.setVisible(false);
                cbAnimal.setVisible(false);
                cbService.setVisible(false);
                btnAddService.setVisible(false);
                btnDeleteService.setVisible(false);
                btnSave.setVisible(false);
        } 
            
            
            if(mode==FormMode.FORM_EDIT_MODE){

                setFields();
                txtNotes.setEditable(true);
                tblServices.setEnabled(true);
                
                cbVeterinarian.setVisible(true);
                cbAnimal.setVisible(true);
                cbService.setVisible(true);
                btnAddService.setVisible(true);
                btnDeleteService.setVisible(true);
                btnSave.setVisible(true);

                List<Veterinarian> veterinarians = new ArrayList<>();
                cbVeterinarian.removeAllItems();
                cbVeterinarian.setModel(new DefaultComboBoxModel(Controller.getInstance().getAllVeterinarians().toArray()));
                for (int i = 0; i < cbVeterinarian.getItemCount(); i++) {
                    Veterinarian v = (Veterinarian) cbVeterinarian.getItemAt(i);
                    if (v.getId() == intervention.getVeterinarian().getId()) {
                        cbVeterinarian.setSelectedIndex(i);
                        break;
                    }
                }
                cbVeterinarian.setSelectedItem(intervention.getVeterinarian());
                

                List<Animal> animals = new ArrayList<>(); 
                cbAnimal.removeAllItems();
                cbAnimal.setModel(new DefaultComboBoxModel(Controller.getInstance().getAllAnimals().toArray()));
                for (int i = 0; i < cbAnimal.getItemCount(); i++) {
                    Animal a = (Animal) cbAnimal.getItemAt(i);
                    if (a.getId() == intervention.getAnimal().getId()) {
                        cbAnimal.setSelectedIndex(i);
                        break;
                    }
                }
                cbAnimal.setSelectedItem(intervention.getAnimal());
                
                List<Service> services = new ArrayList<>();
                cbService.removeAllItems();
                cbService.setModel(new DefaultComboBoxModel(Controller.getInstance().getAllServices().toArray()));
                cbService.setSelectedIndex(-1);
            
            }
         
        } catch (Exception ex) {
//            ex.printStackTrace();
            System.out.println("Neuspesno ucitavanje podataka!");
        }
    }
    
    private void setFields(){
            txtIDIntervention.setText(intervention.getId().toString());
            txtDate.setText(intervention.getDate().toString());
            txtIdVeterinarian.setText(intervention.getVeterinarian().getId().toString());
            txtFirstnameVeterinarian.setText(intervention.getVeterinarian().getFirstname());
            txtLastnameVeterinarian.setText(intervention.getVeterinarian().getLastname());
            txtBirthdayVeterinarian.setText(intervention.getVeterinarian().getBirthday().toString());
            txtPhoneVeterinarian.setText(intervention.getVeterinarian().getPhone());
            txtIdAnimal.setText(intervention.getAnimal().getId().toString());
            txtNameAnimal.setText(intervention.getAnimal().getName());
            txtSpeciesAnimal.setText(intervention.getAnimal().getSpecies().toString());
            txtYearOfBirthAnimal.setText(String.valueOf(intervention.getAnimal().getYearOfBirth()));
            txtGenderAnimal.setText(String.valueOf(intervention.getAnimal().getGender()));
            txtJmbgOwner.setText(intervention.getAnimal().getOwner().getJmbg().toString());
            txtNameOwner.setText(intervention.getAnimal().getOwner().getFirstname()+" "+intervention.getAnimal().getOwner().getLastname());
            if(intervention.getAnimal().getOwner().getLoyaltyCard()==true){
            chkBoxLoyalityCardOwner.setSelected(true);
            }
            txtPhoneOwner.setText(intervention.getAnimal().getOwner().getPhone());
            txtEmailOwner.setText(intervention.getAnimal().getOwner().getEmail());
            txtAddressOwner.setText(intervention.getAnimal().getOwner().getAddress());
            tblServices.setModel(new TableModelInterventionItem(intervention));
            tblServices.getColumnModel().getColumn(1).setPreferredWidth(300);
            txtNotes.setText(intervention.getNotes());
            txtDiscountForLoyalty.setText(String.valueOf(intervention.getDiscountForLoyalty()));
            txtDiscountForNumberOfServices.setText(String.valueOf(intervention.getDiscountForNumberOfServices()));
            txtTotalAmountWithoutDiscount.setText(String.valueOf(intervention.getTotalAmountWithoutDiscount()));
            txtTotalAmountWithDiscount.setText(String.valueOf(intervention.getTotalAmountWithDiscount()));
            
            txtIDIntervention.setEditable(false);
            txtDate.setEditable(false);
            txtIdVeterinarian.setEditable(false);
            txtFirstnameVeterinarian.setEditable(false);
            txtLastnameVeterinarian.setEditable(false);
            txtBirthdayVeterinarian.setEditable(false);
            txtPhoneVeterinarian.setEditable(false);
            txtIdAnimal.setEditable(false);
            txtNameAnimal.setEditable(false);
            txtSpeciesAnimal.setEditable(false);
            txtYearOfBirthAnimal.setEditable(false);
            txtGenderAnimal.setEditable(false);
            txtJmbgOwner.setEditable(false);
            txtNameOwner.setEditable(false);
            chkBoxLoyalityCardOwner.setEnabled(false);
            txtPhoneOwner.setEditable(false);
            txtEmailOwner.setEditable(false);
            txtAddressOwner.setEditable(false);
            tblServices.setEnabled(false);
            txtNotes.setEditable(false);
            txtDiscountForLoyalty.setEditable(false);
            txtDiscountForNumberOfServices.setEditable(false);
            txtTotalAmountWithoutDiscount.setEditable(false);
            txtTotalAmountWithDiscount.setEditable(false);
    }


    public void updateVeterinarianAnimalOwner() {
            Veterinarian veterinarian = (Veterinarian) cbVeterinarian.getSelectedItem();
            if(veterinarian!=null){
                intervention.setVeterinarian(veterinarian);//!!!!!!
                txtIdVeterinarian.setText(veterinarian.getId().toString());
                txtFirstnameVeterinarian.setText(veterinarian.getFirstname());
                txtLastnameVeterinarian.setText(veterinarian.getLastname());
                txtBirthdayVeterinarian.setText(veterinarian.getBirthday().toString());
                txtPhoneVeterinarian.setText(veterinarian.getPhone());
            }
        
            Animal animal = (Animal) cbAnimal.getSelectedItem();
            if(animal!=null){
                intervention.setAnimal(animal);//!!!!!!
                txtIdAnimal.setText(animal.getId().toString());
                txtNameAnimal.setText(animal.getName());
                txtSpeciesAnimal.setText(animal.getSpecies().toString());
                txtYearOfBirthAnimal.setText(animal.getYearOfBirth()+"");
                txtGenderAnimal.setText(animal.getGender().toString());
                Owner owner = animal.getOwner();
                txtJmbgOwner.setText(owner.getJmbg());
                txtNameOwner.setText(owner.getFirstname()+" "+owner.getLastname());
                if(owner.getLoyaltyCard()==true){
                        chkBoxLoyalityCardOwner.setSelected(true);
                }
                txtPhoneOwner.setText(owner.getPhone());
                txtEmailOwner.setText(owner.getEmail());
                txtAddressOwner.setText(owner.getAddress());
            }
            
            TableModelInterventionItem itm = (TableModelInterventionItem) tblServices.getModel();
            itm.calculateTotals(animal);
    }

    public void populateTextFieldsForDiscountAndTotals() {
        txtDiscountForLoyalty.setText(intervention.getDiscountForLoyalty()+"");
        txtDiscountForNumberOfServices.setText(intervention.getDiscountForNumberOfServices()+"");
        txtTotalAmountWithoutDiscount.setText(intervention.getTotalAmountWithoutDiscount()+"");
        txtTotalAmountWithDiscount.setText(intervention.getTotalAmountWithDiscount()+"");
    }
    
}
