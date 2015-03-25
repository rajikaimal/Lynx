/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lynxux;

/**
 *
 * @author rajika
 */
import java.awt.HeadlessException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Stack;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.DTDConstants;
import java.util.Date; //To convert date format
public class Staff extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    String type = null;
    /**
     * Creates new form Staff
     */
    public Staff() {
        initComponents();
        loggedInAs.setText("You're logged in as "+ Login.loggedInUser + "(" + getEmpName() + ")");
        addToTable(); //cancel leaves
        addToTable2();
        pendingAn(); //pending annual leaves
        pendingCas(); //pending casual leaves
        checkAnnLeaves(); //available annual leaves
        checkCasLeaves(); //available casual leaves
        this.setResizable(false);
        this.setLocationRelativeTo(null); //centers the JFrame
    }
    private String getEmpName(){
        String name = null;
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Name from Employees where EmpId=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            name = rst.getString("Name");
            
        }
        catch(Exception e){
            
        }
        return name;
    }
    public void checkCasLeaves(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Casual from Leaves where Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            int count = rst.getInt("Casual");
            int available = 5 - count;
            casLeaves.setText(Integer.toString(available));
            
        }
        catch(Exception e){
        
        }
    }
    public void checkAnnLeaves(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Annual from Leaves where Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            int count = rst.getInt("Annual");
            int available = 20 - count;
            annLeaves.setText(Integer.toString(available));
 
        }
        catch(Exception e){
        
        }
    }
    public void pendingCas(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Casual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            pendingCas.setText(rst.getString("count")); 
        }
        catch(Exception e){
        
        }
    }
    public void pendingAn(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Annual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            pendingAnn.setText(rst.getString("count"));
        }
        catch(Exception e){
        
        }
    }
    public void limitCas(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Casual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            if(Integer.parseInt(rst.getString("count"))>5){
                reason.setVisible(true);
            }
            else{
                reason.setVisible(false);
            }
        }
        catch(Exception e){
        
        }
    }
    public void limitAnn(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Annual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            if(Integer.parseInt(rst.getString("count"))>20){
                reason.setVisible(true);
            }
            else{
                reason.setVisible(false);
            }
        }
        catch(Exception e){
        
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateComponentFormatter1 = new net.sourceforge.jdatepicker.impl.DateComponentFormatter();
        sqlDateModel1 = new net.sourceforge.jdatepicker.impl.SqlDateModel();
        jDatePickerUtil1 = new net.sourceforge.jdatepicker.util.JDatePickerUtil();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        logout = new javax.swing.JButton();
        JFrame = new javax.swing.JTabbedPane();
        requestLeaves = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Reason = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        reason = new javax.swing.JTextArea();
        submit = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        radiocas = new javax.swing.JRadioButton();
        radioann = new javax.swing.JRadioButton();
        dateFor = new com.toedter.calendar.JDateChooser();
        cancelLeaves = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pendingLeaves = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pendingCas = new javax.swing.JLabel();
        pendingAnn = new javax.swing.JLabel();
        notifications = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        availability = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        casLeaves = new javax.swing.JLabel();
        annLeaves = new javax.swing.JLabel();
        loggedInAs = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        report1 = new javax.swing.JButton();
        repPara = new javax.swing.JTextField();
        genRep = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lynxux/logout-icon.png"))); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        JFrame.setBorder(null);

        requestLeaves.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        requestLeaves.setMaximumSize(new java.awt.Dimension(20000, 32767));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setText("Date");
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 20));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("Type");

        Reason.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        Reason.setText("Reason");

        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        reason.setColumns(20);
        reason.setRows(5);
        jScrollPane1.setViewportView(reason);

        submit.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        submit.setIcon(new javax.swing.ImageIcon("/home/rajika/Downloads/Accept-icon.png")); // NOI18N
        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        radiocas.setText("Casual");
        radiocas.setFocusable(false);
        radiocas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radiocas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radiocas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiocasMouseClicked(evt);
            }
        });
        radiocas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radiocasStateChanged(evt);
            }
        });
        radiocas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radiocasItemStateChanged(evt);
            }
        });
        radiocas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiocasActionPerformed(evt);
            }
        });

        radioann.setText("Annual");
        radioann.setFocusable(false);
        radioann.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radioann.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radioann.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioannMouseClicked(evt);
            }
        });
        radioann.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioannActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radiocas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(radioann)
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioann)
                    .addComponent(radiocas))
                .addContainerGap())
        );

        dateFor.setDateFormatString(" yyyy-MM-dd");

        javax.swing.GroupLayout requestLeavesLayout = new javax.swing.GroupLayout(requestLeaves);
        requestLeaves.setLayout(requestLeavesLayout);
        requestLeavesLayout.setHorizontalGroup(
            requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestLeavesLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(Reason, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(91, 91, 91)
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestLeavesLayout.createSequentialGroup()
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(requestLeavesLayout.createSequentialGroup()
                            .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jButton3))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        requestLeavesLayout.setVerticalGroup(
            requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestLeavesLayout.createSequentialGroup()
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, requestLeavesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(48, 48, 48))
                    .addGroup(requestLeavesLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reason)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submit)
                    .addComponent(jButton3))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        JFrame.addTab("Request Leave", new javax.swing.ImageIcon(getClass().getResource("/lynxux/Office-Customer-Male-Light-icon.png")), requestLeaves); // NOI18N

        cancelLeaves.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setBackground(new java.awt.Color(250, 185, 170));
        jTable1.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Type", "Reason"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout cancelLeavesLayout = new javax.swing.GroupLayout(cancelLeaves);
        cancelLeaves.setLayout(cancelLeavesLayout);
        cancelLeavesLayout.setHorizontalGroup(
            cancelLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelLeavesLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        cancelLeavesLayout.setVerticalGroup(
            cancelLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelLeavesLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        JFrame.addTab("Cancel Leave", new javax.swing.ImageIcon(getClass().getResource("/lynxux/delete-icon.png")), cancelLeaves); // NOI18N

        pendingLeaves.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setText("Casual Leaves");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setText("Annual Leaves");

        pendingCas.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        pendingAnn.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        javax.swing.GroupLayout pendingLeavesLayout = new javax.swing.GroupLayout(pendingLeaves);
        pendingLeaves.setLayout(pendingLeavesLayout);
        pendingLeavesLayout.setHorizontalGroup(
            pendingLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingLeavesLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(pendingLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addGroup(pendingLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pendingCas, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendingAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
        );
        pendingLeavesLayout.setVerticalGroup(
            pendingLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingLeavesLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(pendingLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pendingCas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(66, 66, 66)
                .addGroup(pendingLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(pendingAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(248, Short.MAX_VALUE))
        );

        JFrame.addTab("Pending", new javax.swing.ImageIcon(getClass().getResource("/lynxux/hp-pending-icon.png")), pendingLeaves); // NOI18N

        notifications.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable3.setBackground(new java.awt.Color(250, 185, 170));
        jTable3.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Status", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setEnabled(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable3MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout notificationsLayout = new javax.swing.GroupLayout(notifications);
        notifications.setLayout(notificationsLayout);
        notificationsLayout.setHorizontalGroup(
            notificationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationsLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        notificationsLayout.setVerticalGroup(
            notificationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationsLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        JFrame.addTab("Notifications", new javax.swing.ImageIcon(getClass().getResource("/lynxux/Apps-Notifications-icon.png")), notifications); // NOI18N

        availability.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        availability.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("Casual Leaves");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setText("Annual Leaves");

        casLeaves.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        annLeaves.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        javax.swing.GroupLayout availabilityLayout = new javax.swing.GroupLayout(availability);
        availability.setLayout(availabilityLayout);
        availabilityLayout.setHorizontalGroup(
            availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availabilityLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addGroup(availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(casLeaves, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(annLeaves, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(118, 118, 118))
        );
        availabilityLayout.setVerticalGroup(
            availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availabilityLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(casLeaves, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(annLeaves, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        JFrame.addTab("Availability", new javax.swing.ImageIcon(getClass().getResource("/lynxux/Actions-go-next-icon.png")), availability); // NOI18N

        loggedInAs.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N

        report1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lynxux/Reports-icon.png"))); // NOI18N
        report1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report1ActionPerformed(evt);
            }
        });

        genRep.setText("Generate Report");
        genRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(JFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(481, 481, 481)
                        .addComponent(loggedInAs, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 58, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(genRep, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(report1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(repPara, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedInAs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(report1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(62, 62, 62)
                        .addComponent(repPara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(genRep)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        super.dispose();
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    private void radiocasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiocasActionPerformed
        // TODO add your handling code here:
        type = "Casual";
    }//GEN-LAST:event_radiocasActionPerformed

    private void radioannActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioannActionPerformed
        // TODO add your handling code here:
        type = "Annual";
    }//GEN-LAST:event_radioannActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        String sql = "Insert into RequestedLeaves (EmpId,Date,Type,Reason)"+"values(?,?,?,?)";
        try{
            
       //     String datev = new SimpleDateFormat("yyyy-MM-dd").format(dateX.getText());
       //     String dateme = datev.toString();
            pst = conn.prepareStatement(sql);
            pst.setString(1,Login.loggedInUser);
            pst.setString(2,dateFor.toString());
            pst.setString(3,type);
            pst.setString(4,reason.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Your submission is successfull");
            super.dispose();
            Staff w = new Staff();
            w.setVisible(true);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_submitActionPerformed
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void radiocasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiocasMouseClicked
        // TODO add your handling code here:
        limitCas();
    }//GEN-LAST:event_radiocasMouseClicked

    private void radioannMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioannMouseClicked
        // TODO add your handling code here:
        limitAnn();
    }//GEN-LAST:event_radioannMouseClicked

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null,"Do you want to cancel you request","Confirm",JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            int row = jTable1.getSelectedRow();
            
            String dateX = jTable1.getModel().getValueAt(row,0).toString();
            
            try{
                conn = MySqlConnect.ConnectDB();
                String sql = "Delete from RequestedLeaves where EmpId=? AND Date=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,Login.loggedInUser);
                pst.setString(2,dateX);
                pst.execute();
                JOptionPane.showMessageDialog(null,"Request cancelled");
                this.dispose();
                Staff st = new Staff();
                st.setVisible(true);
            }
            catch(Exception e){
                
            }
            
        }
        else if(result == JOptionPane.NO_OPTION){
            
        }
        
    }//GEN-LAST:event_jTable1MouseReleased

    private void report1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report1ActionPerformed
        // TODO add your handling code here:
//        MyiReportViewer viewer=new MyiReportViewer("/home/rajika/NetBeansProjects/LynxUX/src/lynxux/Lynx/Lynx.jasper");
//        viewer.setVisible(true);
        Ireport_staff ir = new Ireport_staff();
        Staff st = new Staff();
    }//GEN-LAST:event_report1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //SimpleDateFormat formatter5=new SimpleDateFormat("yyyy-MM-dd");
     //   String formats1 = formatter5.format(dateX.getText());
       // String datev = (Date)new SimpleDateFormat("yyyy-MM-dd").format(dateX.getText());
        JOptionPane.showMessageDialog(null, dateFor);
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void radiocasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radiocasStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_radiocasStateChanged

    private void radiocasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radiocasItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_radiocasItemStateChanged

    private void genRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genRepActionPerformed
        // TODO add your handling code here:
        String para = repPara.getText();
        Ireport_staff2 rp2 = new Ireport_staff2(para);
    }//GEN-LAST:event_genRepActionPerformed
    public void addToTable(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select * from RequestedLeaves where EmpId=?";
        String date;
        String type;
        String reason;
        try{ 
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            
            while(rst.next()){
                date = rst.getString("Date");
                type = rst.getString("Type");
                reason = rst.getString("Reason");
                
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{date,type,reason});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void addToTable2(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Date,Status,Type from DefaultLeaves where EmpId=?";
        String date;
        String status;
        String type;
        try{
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            while(rst.next()){
                date = rst.getString("Date");
                status = rst.getString("Status");
                type = rst.getString("Type");
                DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
                model.addRow(new Object[]{date,status,type});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public String[] loadForCombo2(){
        String [] emps = null;
        Stack st = new Stack();
        conn = MySqlConnect.ConnectDB();
        String sql2 = "Select EmpId from Employees";
        try{
            pst = conn.prepareStatement(sql2);
            rst = pst.executeQuery();
            rst.next();
            int a = 0;
            while(rst.next()){
                st.push(rst.getString("EmpId"));
            }
            int size = st.size();
            JOptionPane.showMessageDialog(null, st.size());
            emps = new String[size];
            int x = 0;
            while(st.empty()){
                emps[x] = (String)st.pop();
                x++;
            }
        }
        catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return emps;
    }
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
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane JFrame;
    private javax.swing.JLabel Reason;
    private javax.swing.JLabel annLeaves;
    private javax.swing.JPanel availability;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JPanel cancelLeaves;
    private javax.swing.JLabel casLeaves;
    private javax.swing.JTextField date;
    private net.sourceforge.jdatepicker.impl.DateComponentFormatter dateComponentFormatter1;
    private com.toedter.calendar.JDateChooser dateFor;
    private javax.swing.JButton genRep;
    private javax.swing.JButton jButton3;
    private net.sourceforge.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel loggedInAs;
    private javax.swing.JButton logout;
    private javax.swing.JPanel notifications;
    private javax.swing.JLabel pendingAnn;
    private javax.swing.JLabel pendingCas;
    private javax.swing.JPanel pendingLeaves;
    private javax.swing.JRadioButton radioann;
    private javax.swing.JRadioButton radiocas;
    private javax.swing.JTextArea reason;
    private javax.swing.JTextField repPara;
    private javax.swing.JButton report1;
    private javax.swing.JPanel requestLeaves;
    private net.sourceforge.jdatepicker.impl.SqlDateModel sqlDateModel1;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
