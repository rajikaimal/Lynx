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
import java.util.Calendar;
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
        buttonGroup(); //group radio buttons
        loggedInAs.setText("You're logged in as "+ Login.loggedInUser + "(" + getEmpName() + ")");
        cancelleave(); //cancel leaves
        notifications(); //notifications
        updateleaves(); //update leaves
        pendingAn(); //pending annual leaves
        pendingCas(); //pending casual leaves
        checkAnnLeaves(); //available annual leaves
        checkCasLeaves(); //available casual leaves
        this.setResizable(false);
        this.setLocationRelativeTo(null); //centers the JFrame
    }
    private void buttonGroup(){
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(radioann);
        bg1.add(radiocas);
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
            if(Integer.parseInt(rst.getString("count"))>=5){
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
            if(Integer.parseInt(rst.getString("count"))>=20){
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        logout = new javax.swing.JButton();
        JFrame = new javax.swing.JTabbedPane();
        requestLeaves = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Reason = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reason = new javax.swing.JTextArea();
        submit = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        radiocas = new javax.swing.JRadioButton();
        radioann = new javax.swing.JRadioButton();
        jDateText = new com.toedter.calendar.JDateChooser();
        cancelLeaves = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cancelLeave = new javax.swing.JTable();
        pendingLeaves = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pendingCas = new javax.swing.JLabel();
        pendingAnn = new javax.swing.JLabel();
        notifications = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        notificationsTable = new javax.swing.JTable();
        availability = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        casLeaves = new javax.swing.JLabel();
        annLeaves = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        updateReqTable = new javax.swing.JTable();
        Datereq = new javax.swing.JLabel();
        Typereq = new javax.swing.JLabel();
        Reasonreq = new javax.swing.JLabel();
        dateload = new javax.swing.JTextField();
        typeload = new javax.swing.JTextField();
        reasonload = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        loggedInAs = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fromEmployee = new javax.swing.JButton();
        genRep = new javax.swing.JButton();
        repPara = new javax.swing.JComboBox();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logout.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lynxux/logout-icon.png"))); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        JFrame.setBorder(null);
        JFrame.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N

        requestLeaves.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        requestLeaves.setMaximumSize(new java.awt.Dimension(20000, 32767));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setText("Date");
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 20));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("Type");

        Reason.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        Reason.setText("Reason");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radiocas)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addComponent(radiocas))
        );

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

        jDateText.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout requestLeavesLayout = new javax.swing.GroupLayout(requestLeaves);
        requestLeaves.setLayout(requestLeavesLayout);
        requestLeavesLayout.setHorizontalGroup(
            requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestLeavesLayout.createSequentialGroup()
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestLeavesLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Reason)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(requestLeavesLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(radioann))
                            .addGroup(requestLeavesLayout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(requestLeavesLayout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(requestLeavesLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        requestLeavesLayout.setVerticalGroup(
            requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestLeavesLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestLeavesLayout.createSequentialGroup()
                        .addComponent(jDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioann))
                        .addGap(34, 34, 34))
                    .addGroup(requestLeavesLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(requestLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reason))
                .addGap(56, 56, 56)
                .addComponent(submit)
                .addGap(45, 45, 45))
        );

        JFrame.addTab("Request Leave", new javax.swing.ImageIcon(getClass().getResource("/lynxux/Office-Customer-Male-Light-icon.png")), requestLeaves); // NOI18N

        cancelLeaves.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cancelLeave.setBackground(new java.awt.Color(250, 185, 170));
        cancelLeave.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N
        cancelLeave.setModel(new javax.swing.table.DefaultTableModel(
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
        cancelLeave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelLeaveMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelLeaveMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(cancelLeave);

        javax.swing.GroupLayout cancelLeavesLayout = new javax.swing.GroupLayout(cancelLeaves);
        cancelLeaves.setLayout(cancelLeavesLayout);
        cancelLeavesLayout.setHorizontalGroup(
            cancelLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelLeavesLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(317, Short.MAX_VALUE))
        );
        cancelLeavesLayout.setVerticalGroup(
            cancelLeavesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelLeavesLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
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
                .addContainerGap(244, Short.MAX_VALUE))
        );

        JFrame.addTab("Pending", new javax.swing.ImageIcon(getClass().getResource("/lynxux/hp-pending-icon.png")), pendingLeaves); // NOI18N

        notifications.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        notificationsTable.setBackground(new java.awt.Color(250, 185, 170));
        notificationsTable.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N
        notificationsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        notificationsTable.setEnabled(false);
        notificationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                notificationsTableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationsTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(notificationsTable);

        javax.swing.GroupLayout notificationsLayout = new javax.swing.GroupLayout(notifications);
        notifications.setLayout(notificationsLayout);
        notificationsLayout.setHorizontalGroup(
            notificationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationsLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        notificationsLayout.setVerticalGroup(
            notificationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationsLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
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
                .addGroup(availabilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
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
                .addContainerGap(263, Short.MAX_VALUE))
        );

        JFrame.addTab("Availability", new javax.swing.ImageIcon(getClass().getResource("/lynxux/Actions-go-next-icon.png")), availability); // NOI18N

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        updateReqTable.setBackground(new java.awt.Color(137, 253, 125));
        updateReqTable.setFont(new java.awt.Font("URW Gothic L", 1, 18)); // NOI18N
        updateReqTable.setModel(new javax.swing.table.DefaultTableModel(
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
        updateReqTable.setCellSelectionEnabled(true);
        updateReqTable.setEditingRow(1);
        updateReqTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                updateReqTableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateReqTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(updateReqTable);

        Datereq.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        Datereq.setText("Date");

        Typereq.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        Typereq.setText("Type");

        Reasonreq.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        Reasonreq.setText("Reason");

        dateload.setEditable(false);
        dateload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateloadActionPerformed(evt);
            }
        });

        typeload.setEditable(false);

        jButton1.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lynxux/Apps-system-software-update-icon (1).png"))); // NOI18N
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Datereq)
                            .addComponent(Typereq)
                            .addComponent(Reasonreq))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateload, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(typeload)
                            .addComponent(reasonload))
                        .addContainerGap(75, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Datereq)
                            .addComponent(dateload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Typereq)
                            .addComponent(typeload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Reasonreq)
                            .addComponent(reasonload, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        JFrame.addTab("Update Request", new javax.swing.ImageIcon(getClass().getResource("/lynxux/edit-validated-icon.png")), jPanel1); // NOI18N

        loggedInAs.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N

        fromEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lynxux/Reports-icon.png"))); // NOI18N
        fromEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromEmployeeActionPerformed(evt);
            }
        });

        genRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lynxux/SEO-icon.png"))); // NOI18N
        genRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genRepActionPerformed(evt);
            }
        });

        repPara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Accepted", "Rejected" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(531, 531, 531)
                        .addComponent(loggedInAs, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(JFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 18, Short.MAX_VALUE)
                                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fromEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(repPara, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(genRep, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                        .addGap(74, 74, 74)
                        .addComponent(fromEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(62, 62, 62)
                        .addComponent(repPara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(genRep, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        String dateUI = ((JTextField)jDateText.getDateEditor().getUiComponent()).getText();
        String month = dateUI.substring(5,7);
        int monthUI = Integer.parseInt(month);
        Calendar cal = Calendar.getInstance();
        int curmonth = cal.get(Calendar.MONTH) + 1;
        if(monthUI>=curmonth){
            conn = MySqlConnect.ConnectDB();
            String sql = "Insert into RequestedLeaves (EmpId,Date,Type,Reason)"+"values(?,?,?,?)";
            String sql2 = "Insert into DefaultLeaves(EmpId,Date,Type) values(?,?,?)";
            String sql3 = "Insert into AdditionalLeaves(EmpId,Date,Type,Reason) values(?,?,?,?)";
            try{
                pst = conn.prepareStatement(sql);
                pst.setString(1,Login.loggedInUser);
                pst.setString(2,((JTextField)jDateText.getDateEditor().getUiComponent()).getText());
                pst.setString(3,type);
                pst.setString(4,reason.getText());
                pst.execute();
                if(reason.getText().equals("")){
                    pst = conn.prepareStatement(sql2);
                    pst.setString(1,Login.loggedInUser);
                    pst.setString(2,((JTextField)jDateText.getDateEditor().getUiComponent()).getText());
                    pst.setString(3,type);

                    pst.execute();
                }
                else{
                    pst = conn.prepareStatement(sql3);
                    pst.setString(1,Login.loggedInUser);
                    pst.setString(2,((JTextField)jDateText.getDateEditor().getUiComponent()).getText());
                    pst.setString(3,type);
                    pst.setString(4, reason.getText());
                    pst.execute();
                }


                JOptionPane.showMessageDialog(null, "Your submission is successfull");
                super.dispose();
                Staff w = new Staff();
                w.setVisible(true);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Month");
        }
    }//GEN-LAST:event_submitActionPerformed
    
    private void cancelLeaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelLeaveMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cancelLeaveMouseClicked

    private void radiocasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiocasMouseClicked
        // TODO add your handling code here:
        limitCas();
    }//GEN-LAST:event_radiocasMouseClicked

    private void radioannMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioannMouseClicked
        // TODO add your handling code here:
        limitAnn();
    }//GEN-LAST:event_radioannMouseClicked

    private void cancelLeaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelLeaveMouseReleased
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null,"Do you want to cancel you request","Confirm",JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            int row = cancelLeave.getSelectedRow();
            
            String dateX = cancelLeave.getModel().getValueAt(row,0).toString();
            
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
        
    }//GEN-LAST:event_cancelLeaveMouseReleased

    private void fromEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromEmployeeActionPerformed
        // TODO add your handling code here:
        String button = "fromEmployee";
        IreportUni rp=new IreportUni("Select * from RequestedLeaves where EmpId=?",button);
//        viewer.setVisible(true);
     //   IreportUni ir = new IreportUni();
       // Staff st = new Staff();
    }//GEN-LAST:event_fromEmployeeActionPerformed

    private void notificationsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTableMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_notificationsTableMouseReleased

    private void notificationsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_notificationsTableMouseClicked

    private void radiocasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radiocasStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_radiocasStateChanged

    private void radiocasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radiocasItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_radiocasItemStateChanged

    private void genRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genRepActionPerformed
        // TODO add your handling code here:
        String para = repPara.getSelectedItem().toString();
        Ireport rp2 = new Ireport(para);
    }//GEN-LAST:event_genRepActionPerformed

    private void updateReqTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateReqTableMouseReleased
        // TODO add your handling code here:
        
            int row = updateReqTable.getSelectedRow();
            
            String dateReq = updateReqTable.getModel().getValueAt(row,0).toString();
            String TypeReq = updateReqTable.getModel().getValueAt(row,1).toString();
            String ReasonReq = updateReqTable.getModel().getValueAt(row,2).toString();
            dateload.setText(dateReq);
            typeload.setText(TypeReq);
            reasonload.setText(ReasonReq);

    }//GEN-LAST:event_updateReqTableMouseReleased

    private void updateReqTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateReqTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_updateReqTableMouseClicked

    private void dateloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateloadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateloadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String date = dateload.getText();
        String reason = reasonload.getText();
        String type = typeload.getText();
        try{
                conn = MySqlConnect.ConnectDB();
                String sql = "Update RequestedLeaves SET Reason=?,Type=? where EmpId=? and Date=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,reason);
                pst.setString(2,type);
                pst.setString(3,Login.loggedInUser);
                pst.setString(4,date);
                pst.execute();
                JOptionPane.showMessageDialog(null,"Request Updated");
                this.dispose();
                Staff st = new Staff();
                st.setVisible(true);
            }
        catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                
            }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    public void cancelleave(){
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
                
                DefaultTableModel model = (DefaultTableModel) cancelLeave.getModel();
                model.addRow(new Object[]{date,type,reason});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void notifications(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Date,Status,Type from DefaultLeaves where EmpId=? UNION Select Date,Status,Type from AdditionalLeaves where EmpId=?";
        String date;
        String status;
        String type;
        try{
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            pst.setString(2, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            while(rst.next()){
                date = rst.getString("Date");
                status = rst.getString("Status");
                type = rst.getString("Type");
                DefaultTableModel model = (DefaultTableModel) notificationsTable.getModel();
                model.addRow(new Object[]{date,status,type});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void updateleaves(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select * from RequestedLeaves where EmpId=? and Reason !=''";
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
                
                DefaultTableModel model = (DefaultTableModel) updateReqTable.getModel();
                model.addRow(new Object[]{date,type,reason});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
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
    private javax.swing.JLabel Datereq;
    private javax.swing.JTabbedPane JFrame;
    private javax.swing.JLabel Reason;
    private javax.swing.JLabel Reasonreq;
    private javax.swing.JLabel Typereq;
    private javax.swing.JLabel annLeaves;
    private javax.swing.JPanel availability;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTable cancelLeave;
    private javax.swing.JPanel cancelLeaves;
    private javax.swing.JLabel casLeaves;
    private javax.swing.JTextField dateload;
    private javax.swing.JButton fromEmployee;
    private javax.swing.JButton genRep;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel loggedInAs;
    private javax.swing.JButton logout;
    private javax.swing.JPanel notifications;
    private javax.swing.JTable notificationsTable;
    private javax.swing.JLabel pendingAnn;
    private javax.swing.JLabel pendingCas;
    private javax.swing.JPanel pendingLeaves;
    private javax.swing.JRadioButton radioann;
    private javax.swing.JRadioButton radiocas;
    private javax.swing.JTextArea reason;
    private javax.swing.JTextField reasonload;
    private javax.swing.JComboBox repPara;
    private javax.swing.JPanel requestLeaves;
    private javax.swing.JButton submit;
    private javax.swing.JTextField typeload;
    private javax.swing.JTable updateReqTable;
    // End of variables declaration//GEN-END:variables
}
