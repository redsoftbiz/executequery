/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.executequery.gui.browser;

import liquibase.database.Database;
import org.executequery.GUIUtilities;
import org.executequery.components.table.BrowserTableCellRenderer;
import org.executequery.components.table.RoleTableModel;
import org.executequery.components.table.RowHeaderRenderer;
import org.executequery.databasemediators.DatabaseConnection;
import org.executequery.datasource.ConnectionManager;
import org.executequery.repository.DatabaseConnectionRepository;
import org.executequery.repository.RepositoryCache;
import org.firebirdsql.gds.ng.FbDatabase;
import org.firebirdsql.jca.FBManagedConnection;
import org.firebirdsql.jdbc.FBConnection;
import org.firebirdsql.management.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author mikhan808
 */
public class UserManagerPanel extends javax.swing.JPanel {

    /**
     * Creates new form UserManagerPanel
     */
    public UserManagerPanel() {

        Database db;


        gr=GUIUtilities.loadIcon(BrowserConstants.GRANT_IMAGE);
        no=GUIUtilities.loadIcon(BrowserConstants.NO_GRANT_IMAGE);
        adm=GUIUtilities.loadIcon(BrowserConstants.ADMIN_OPTION_IMAGE);
        //userManager.
        user_names=new Vector<String>();
        role_names=new Vector<String>();
        initComponents();
        listConnections=((DatabaseConnectionRepository) RepositoryCache.load(DatabaseConnectionRepository.REPOSITORY_ID)).findAll();
        this.userManager = new FBUserManager();
        execute_w=false;
        for(DatabaseConnection dc:listConnections)
        {
            databaseBox.addItem(dc.getName());
            if (dc.isConnected()) {
                execute_w=true;
                databaseBox.setSelectedItem(dc.getName());


            }
           // Connection connection = ConnectionManager.getConnection(dc);

           // Statement statement = connection.createStatement();
           // ResultSet dsfsdf = statement.executeQuery("dsfsdf");
        }
        if (!execute_w) {
            execute_w = true;
            databaseBox.setSelectedIndex(0);
        }
        userAdd=new FBUser();

    }
    Icon gr,no,adm;
boolean execute_w;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        databaseLabel = new javax.swing.JLabel();
        serverLabel = new javax.swing.JLabel();
        databaseBox = new javax.swing.JComboBox<>();
        serverBox = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        usersPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new JScrollPane(membershipTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        usersTable = new javax.swing.JTable();
        rolesTable = new JTable();
        addUserButton = new javax.swing.JButton();
        addRoleButton = new javax.swing.JButton();
        editUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        deleteRoleButton = new javax.swing.JButton();
        refreshUsersButton = new javax.swing.JButton();
        rolesPanel = new javax.swing.JPanel();
        membershipPanel = new javax.swing.JPanel();
        membershipTable = new javax.swing.JTable();
        grantButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        no_grantButton = new javax.swing.JButton();

        setName(""); // NOI18N

        jPanel1.setName("upPanel"); // NOI18N

        databaseLabel.setText("database");

        serverLabel.setText("server");

        databaseBox.setEditable(true);
        databaseBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseBoxActionPerformed(evt);
            }
        });

        serverBox.setEditable(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(databaseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(serverLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(serverBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(databaseBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(databaseLabel)
                                        .addComponent(databaseBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(serverLabel)
                                        .addComponent(serverBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.setToolTipText("");

        usersTable.setModel(new RoleTableModel(
                new Object [][] {

                },
                new String [] {
                        "User name", "First name", "Middle name", "Last name", "Active"
                }
        ));
        jScrollPane1.setViewportView(usersTable);
        rolesTable.setModel(new RoleTableModel(
                new Object [][] {

                },
                new String [] {
                        "Role name", "Owner"
                }
        ));
        jScrollPane2.setViewportView(rolesTable);

        addUserButton.setText("Add");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });
        addRoleButton.setText("Add");
        addRoleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoleButtonActionPerformed(evt);
            }
        });

        editUserButton.setText("Edit");
        editUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setText("Delete");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        deleteRoleButton.setText("Delete");
        deleteRoleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoleButtonActionPerformed(evt);
            }
        });

        refreshUsersButton.setText("Refresh");
        refreshUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshUserButtonActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
                usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(usersPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshUsersButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        usersPanelLayout.setVerticalGroup(
                usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(usersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addUserButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editUserButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteUserButton)
                                .addGap(18, 18, 18)
                                .addComponent(refreshUsersButton)
                                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Users", usersPanel);

        javax.swing.GroupLayout rolesPanelLayout = new javax.swing.GroupLayout(rolesPanel);
        rolesPanel.setLayout(rolesPanelLayout);
        rolesPanelLayout.setHorizontalGroup(
                rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rolesPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addRoleButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteRoleButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        rolesPanelLayout.setVerticalGroup(
                rolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(rolesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addRoleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteRoleButton)
                                .addGap(18, 18, 18)
                                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Roles", rolesPanel);



        membershipTable.setModel(new RoleTableModel(
                new Object [][] {

                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        membershipTable.setDefaultRenderer(Object.class,new BrowserTableCellRenderer());
        jScrollPane3.setViewportView(membershipTable);

        grantButton.setIcon(gr);
        grantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grantButtonActionPerformed(evt);
            }
        });

        adminButton.setIcon(adm);
        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonActionPerformed(evt);
            }
        });

        no_grantButton.setIcon(no);
        no_grantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_grantButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout membershipPanelLayout = new javax.swing.GroupLayout(membershipPanel);
        membershipPanel.setLayout(membershipPanelLayout);
        membershipPanelLayout.setHorizontalGroup(
                membershipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membershipPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(membershipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(grantButton)
                                        .addComponent(adminButton)
                                        .addComponent(no_grantButton))
                                .addGap(32, 32, 32)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );
        membershipPanelLayout.setVerticalGroup(
                membershipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membershipPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(membershipPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(membershipPanelLayout.createSequentialGroup()
                                                .addComponent(grantButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(adminButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(no_grantButton))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Membership", membershipPanel);



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Users");
    }// </editor-fold>
    Connection con;
    private void databaseBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if(execute_w) {
            if (listConnections.get(databaseBox.getSelectedIndex()).isConnected())
                refresh();
            else {
                usersTable.setModel(new RoleTableModel(
                        new Object[][]{

                        },
                        new String[]{
                                "User name", "First name", "Middle name", "Last name"
                        }
                ));
                JFrame frame_pass = new FrameLogin(this, listConnections.get(databaseBox.getSelectedIndex()).getUserName(),
                        listConnections.get(databaseBox.getSelectedIndex()).getUnencryptedPassword());
                frame_pass.setVisible(true);
            }
        }
    }
    void addUserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        GUIUtilities.addCentralPane("Add user",
                UserManagerPanel.FRAME_ICON,
                new WindowAddUser(this),
                null,
                true);
    }
    void editUserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        int ind=usersTable.getSelectedRow();
        if(ind>=0)
        {
            GUIUtilities.addCentralPane("Edit user",
                    UserManagerPanel.FRAME_ICON,
                    new WindowAddUser(this,((User)(users.values().toArray()[ind]))),
                    null,
                    true);
        }
    }
    void addRoleButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        GUIUtilities.addCentralPane("Add role",
                UserManagerPanel.FRAME_ICON,
                new WindowAddRole(this),
                null,
                true);
    }
    void refreshUserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        refresh();
    }
    void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        int ind=usersTable.getSelectedRow();
        if(ind>=0)
        {

            if(GUIUtilities.displayConfirmDialog("Are you sure that you want to delete user?")==0) {
            try {
                userManager.delete(((User) (users.values().toArray()[ind])));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            refresh();
        }
        }
        else
        {


        }

    }
    void deleteRoleButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        int ind=rolesTable.getSelectedRow();
        if(ind>=0) {
            String role= (String) ((RoleTableModel) rolesTable.getModel()).getValueAt(ind,0);
            if(GUIUtilities.displayConfirmDialog("Are you sure that you want to delete role "+ role+ "?")==0)
            try {
                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement state = con.createStatement();
                state.execute("DROP ROLE " + role);

                refresh();
            } catch (Exception e) {
                GUIUtilities.displayErrorMessage(e.getMessage());
                System.out.println(e.toString());
            }
        }
    }
    private void grantButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int row=membershipTable.getSelectedRow();
        int col=membershipTable.getSelectedColumn();
        if (col>=0) {
            try {
                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement st = con.createStatement();
                st.execute("REVOKE " + role_names.elementAt(col)+" FROM " + user_names.elementAt(row) + ";");



            } catch (Exception e) {
                GUIUtilities.displayErrorMessage(e.getMessage());
            }
            try {

                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement st = con.createStatement();
                st.execute("GRANT " + role_names.elementAt(col)+" TO " + user_names.elementAt(row) + ";");
                create_membership();

            } catch (Exception e) {
                GUIUtilities.displayErrorMessage(e.getMessage());
            }
        }

    }

    private void adminButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int row=membershipTable.getSelectedRow();
        int col=membershipTable.getSelectedColumn();
        if (col>=0) {
            try {
                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement st = con.createStatement();
                st.execute("REVOKE " + role_names.elementAt(col)+" FROM " + user_names.elementAt(row) + ";");



            } catch (Exception e) {
                GUIUtilities.displayErrorMessage(e.getMessage());
            }
            try {

                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement st = con.createStatement();
                st.execute("GRANT " + role_names.elementAt(col)+" TO " + user_names.elementAt(row) + " WITH ADMIN OPTION;");
                create_membership();

            } catch (Exception e) {
                GUIUtilities.displayErrorMessage(e.getMessage());
            }
        }

    }

    private void no_grantButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int row=membershipTable.getSelectedRow();
        int col=membershipTable.getSelectedColumn();
        if (col>=0) {
            try {
                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement st = con.createStatement();
                st.execute("REVOKE " + role_names.elementAt(col)+" FROM " + user_names.elementAt(row) + ";");



            } catch (Exception e) {
                GUIUtilities.displayErrorMessage(e.getMessage());
            }
            create_membership();
        }

    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addUserButton;
    private javax.swing.JButton addRoleButton;
    private javax.swing.JButton adminButton;
    private javax.swing.JComboBox<String> databaseBox;
    private javax.swing.JLabel databaseLabel;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JButton deleteRoleButton;
    private javax.swing.JButton editUserButton;
    private javax.swing.JButton grantButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel membershipPanel;
    private javax.swing.JTable membershipTable;
    private javax.swing.JButton no_grantButton;
    private javax.swing.JButton refreshUsersButton;
    private javax.swing.JPanel rolesPanel;
    private javax.swing.JComboBox<String> serverBox;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JPanel usersPanel;
    private javax.swing.JTable usersTable;
    private javax.swing.JTable rolesTable;
    // End of variables declaration                   
    public static final String TITLE = "User manager";

    /**
     * This objects icon as an internal frame
     */
    public static final String FRAME_ICON = "user_manager_16.png";
    public UserManager userManager;
    List<DatabaseConnection> listConnections;
    Map<String,User> users;
    Vector<String>user_names;
    Vector<String>role_names;
    public User userAdd;
    void getUsersPanel()
    {
        try {

            users = userManager.getUsers();

            usersTable.setModel(new RoleTableModel(
                    new Object[][]{

                    },
                    new String[]{
                            "User name", "First name", "Middle name", "Last name"
                    }
            ));
            user_names.clear();

            for (User u : users.values()) {

                /*DatabaseConnection db=listConnections.get(databaseBox.getSelectedIndex());

                Class.forName("org.firebirdsql.");
                Connection con= DriverManager.getConnection(db.getURL(),db.getUserName(),db.getPassword());
                Statement st=con.createStatement();
                ResultSet rs = st.executeQuery("sp_who");
                System.out.println(rs.getString(0));*/
                user_names.add(u.getUserName().trim());
                Object[] rowData = new Object[]{u.getUserName(), u.getFirstName(), u.getMiddleName(), u.getLastName()};
                ((RoleTableModel) usersTable.getModel()).addRow(rowData);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            GUIUtilities.displayErrorMessage(e.toString());
        }
    }
    void refresh()
    {
        if(databaseBox.getItemAt(databaseBox.getSelectedIndex())!="") {
            serverBox.removeAllItems();
            serverBox.addItem(listConnections.get(databaseBox.getSelectedIndex()).getHost());
        }
        userManager.setDatabase(listConnections.get(databaseBox.getSelectedIndex()).getSourceName());
        userManager.setHost(listConnections.get(databaseBox.getSelectedIndex()).getHost());
        userManager.setPort(listConnections.get(databaseBox.getSelectedIndex()).getPortInt());
        if(listConnections.get(databaseBox.getSelectedIndex()).isConnected()) {
            rolesPanel.setVisible(true);
            membershipPanel.setVisible(true);
            con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));

            userManager.setUser(listConnections.get(databaseBox.getSelectedIndex()).getUserName());
            userManager.setPassword(listConnections.get(databaseBox.getSelectedIndex()).getUnencryptedPassword());

getUsersPanel();

                try {

                    //con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                    Statement state = con.createStatement();
                    result = state.executeQuery("SELECT RDB$ROLE_NAME,RDB$OWNER_NAME FROM RDB$ROLES ORDER BY" +
                            " RDB$ROLE_NAME");
                    rolesTable.setModel(new RoleTableModel(
                            new Object[][]{

                            },
                            new String[]{
                                    "Role name", "Owner"
                            }
                    ));

                    role_names.clear();
                    //role_names.add("USER");
                    while (result.next()) {
                        String rol=result.getString(1).trim();
                        role_names.add(rol);
                        user_names.add(rol);
                        Object[] roleData = new Object[]{rol, result.getObject(2)};
                        ((RoleTableModel) rolesTable.getModel()).addRow(roleData);


                    }


                } catch (Exception e) {
                    GUIUtilities.displayErrorMessage(e.toString());
                }
            create_membership();

        }
else{
            getUsersPanel();
            membershipPanel.setVisible(false);
            rolesPanel.setVisible(false);
        }
//create_membership();
    }
    void create_membership()
    {
        membershipTable.setModel(new RoleTableModel(
                new Object [][] {},
                role_names.toArray()
        ));

        for(int i=0;i<user_names.size();i++)
        {
            try {
                //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
                Statement st = con.createStatement();
                ResultSet rs1=st.executeQuery("select distinct RDB$PRIVILEGE,RDB$GRANT_OPTION,rdb$Relation_name from RDB$USER_PRIVILEGES\n" +
                        "where (RDB$USER='"+user_names.elementAt(i)+"') and (rdb$object_type=8 or rdb$object_type=13)");
                Vector<Object> roleData= new Vector<Object>();
                for (String u:role_names)
                {
                    roleData.add(no);
                }
                //roleData.set(0,user_names.elementAt(i));
                while (rs1.next())
                {
                    String u=rs1.getString(3);
                    u=u.trim();
                    int ind = role_names.indexOf(u);
                    if (rs1.getObject(2).equals(0))
                        roleData.set(ind,gr);
                    else
                        roleData.set(ind,adm);

                }
                ((RoleTableModel) membershipTable.getModel()).addRow(roleData);

            }
            catch(Exception e)
            {GUIUtilities.displayErrorMessage(e.getMessage());}
        }
        int sizer=0;
        for(int i=0;i<role_names.size();i++)
        {

            int temper=role_names.elementAt(i).length()*8;
            membershipTable.getColumn(role_names.elementAt(i)).setMinWidth(temper);
            sizer+=temper;
            //membershipTable.getColumn(role_names.elementAt(i)).setResizable(false);

        }

        JList rowHeader=new JList(user_names);
        rowHeader.setFixedCellWidth(150);
        rowHeader.setFixedCellHeight(membershipTable.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(membershipTable));
        jScrollPane3.setRowHeaderView(rowHeader);
        int wid=jScrollPane3.getPreferredSize().width;
        if (sizer>wid)
            membershipTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        else
            membershipTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    }
    ResultSet result;
    public void addUser()
    {
        try {
            userManager.add(userAdd);

            refresh();
        }
        catch(Exception e)
        {
            GUIUtilities.displayErrorMessage(e.getMessage());
            System.out.println(e.toString());
        }
    }
    public void addRole(String role)
    {
        try {
            //Connection con = ConnectionManager.getConnection(listConnections.get(databaseBox.getSelectedIndex()));
            Statement state = con.createStatement();
            if(!state.execute("CREATE ROLE "+role))
                GUIUtilities.displayInformationMessage("Succes");
            else
                GUIUtilities.displayErrorMessage("Error");
            refresh();
        }
        catch(Exception e)
        {
            GUIUtilities.displayErrorMessage(e.getMessage());
            System.out.println(e.toString());
        }
    }
    public void editUser()
    {
        try {
            userManager.update(userAdd);

            refresh();
        }
        catch(Exception e)
        {
            GUIUtilities.displayErrorMessage(e.getMessage());
            System.out.println(e.toString());
        }
    }

}
