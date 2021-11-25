package windows;

import entity.*;
import sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class MStaffManagementWindow extends JFrame implements ActionListener {
    private JLabel lbl_add_userAc, lbl_add_pin, lbl_add_userName;
    private JTextField txt_add_userAc, txt_add_pin, txt_add_userName;
    private JLabel lbl_delete_id;
    private JTextField txt_delete_id;

    private JButton btn_giftCardManagement, btn_showManagement, btn_reports, btn_movieManagement, btn_logout;
    private JButton btn_staffDataSearch, btn_addStaff, btn_add, btn_deleteStaff, btn_delete;
    private JLabel btn_userName;
    private JPanel jp_right, jp_left, jp_staffDataSearch, jp_contact, jp_contact1, jp_contact2;

    private JTextField txt_userName;
    private JButton btn_staffSearch;

    private DefaultTableModel t_staffTable;
    private JTable Jt_staff;
    private JScrollPane Js_staff;

    private static User user = new User();
    private static Object[][] staffTable;

    public MStaffManagementWindow(String title) {
        JLabel lbl_show = new JLabel("Staff Data Management");
        lbl_show.setForeground(Color.BLACK);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 40));
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        btn_giftCardManagement = new JButton("Manage gift cards");
        btn_giftCardManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_giftCardManagement.addActionListener(this);

        btn_showManagement = new JButton("Manage shows");
        btn_showManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_showManagement.addActionListener(this);

        btn_reports = new JButton("View reports");
        btn_reports.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_reports.addActionListener(this);

        btn_movieManagement = new JButton("Manage movies");
        btn_movieManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_movieManagement.addActionListener(this);

        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_logout.addActionListener(this);

        JPanel jp_FunctionBtn = new JPanel();
        jp_FunctionBtn.setOpaque(false);
        jp_FunctionBtn.add(btn_giftCardManagement);
        jp_FunctionBtn.add(btn_showManagement);
        jp_FunctionBtn.add(btn_reports);
        jp_FunctionBtn.add(btn_movieManagement);
        jp_FunctionBtn.add(btn_logout);

        this.setLayout(new BorderLayout());
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_FunctionBtn, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        staffDataSearch();
        initStaffOperationButton();
        addStaffModule();
        deleteStaffModule();

        jp_left = new JPanel();
        jp_left.setOpaque(false);
        jp_left.setLayout(new GridLayout(4, 2));
        jp_left.add(btn_staffDataSearch);
        jp_left.add(btn_addStaff);
        jp_left.add(btn_deleteStaff);

        // right part
        jp_right = new JPanel();
        jp_right.setOpaque(false);
        jp_right.setLayout(new BorderLayout());
        jp_right.add(jp_staffDataSearch, BorderLayout.CENTER);

        this.add(jp_left, BorderLayout.WEST);
        this.add(jp_right, BorderLayout.CENTER);
        this.validate();
        this.setVisible(true);
        this.setSize(1800, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initStaffOperationButton() {
        btn_staffDataSearch = new JButton("Staff Search");
        btn_staffDataSearch.setOpaque(false);
        btn_staffDataSearch.addActionListener(this);
        btn_staffDataSearch.setForeground(Color.red);
        btn_staffDataSearch.setBackground(Color.DARK_GRAY);
        btn_staffDataSearch.setHorizontalAlignment(JLabel.CENTER);
        btn_staffDataSearch.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_staffDataSearch.setBounds(2,2,20,20);

        btn_addStaff = new JButton("Add Staff");
        btn_addStaff.setOpaque(false);
        btn_addStaff.addActionListener(this);
        btn_addStaff.setForeground(Color.red);
        btn_addStaff.setBackground(Color.DARK_GRAY);
        btn_addStaff.setHorizontalAlignment(JLabel.CENTER);
        btn_addStaff.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_addStaff.setBounds(2,2,20,20);

        btn_deleteStaff = new JButton("Delete Staff");
        btn_deleteStaff.setOpaque(false);
        btn_deleteStaff.addActionListener(this);
        btn_deleteStaff.setForeground(Color.red);
        btn_deleteStaff.setBackground(Color.DARK_GRAY);
        btn_deleteStaff.setHorizontalAlignment(JLabel.CENTER);
        btn_deleteStaff.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_deleteStaff.setBounds(2,2,20,20);

    }

    private void addStaffModule() {

        jp_contact = new JPanel();
        jp_contact.setOpaque(false);

        lbl_add_userAc = new JLabel("Staff's user account:");
        lbl_add_userAc.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_userAc.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_pin = new JLabel("Pin:");
        lbl_add_pin.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_pin.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_userName = new JLabel("Staff's user name:");
        lbl_add_userName.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_userName.setFont(new Font("Verdana", Font.PLAIN, 30));



        txt_add_userAc = new JTextField ();
        txt_add_userAc.setOpaque(false);
        txt_add_userAc.setForeground(Color.BLACK);
        txt_add_userAc.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_pin = new JTextField ();
        txt_add_pin.setOpaque(false);
        txt_add_pin.setForeground(Color.BLACK);
        txt_add_pin.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_userName = new JTextField ();
        txt_add_userName.setOpaque(false);
        txt_add_userName.setForeground(Color.BLACK);
        txt_add_userName.setFont(new Font("Verdana", Font.PLAIN, 20));


        JPanel jp_contactMid = new JPanel();
        jp_contactMid.setOpaque(false);
        jp_contactMid.setLayout(new GridLayout(7, 2));
        jp_contactMid.add(lbl_add_userAc);
        jp_contactMid.add(txt_add_userAc);
        jp_contactMid.add(lbl_add_pin);
        jp_contactMid.add(txt_add_pin);
        jp_contactMid.add(lbl_add_userName);
        jp_contactMid.add(txt_add_userName);


        btn_add = new JButton("Add");
        btn_add.addActionListener(this);
        btn_add.setBackground(Color.orange);
        btn_add.setHorizontalAlignment(JButton.CENTER);
        btn_add.setFont(new Font("Verdana", Font.PLAIN, 20));

        jp_contact = new JPanel();
        jp_contact.setOpaque(false);
        jp_contact.setLayout(new BorderLayout());
        jp_contact.add(jp_contactMid, BorderLayout.CENTER);
        jp_contact.add(btn_add, BorderLayout.SOUTH);
    }

    private void deleteStaffModule() {

        jp_contact1 = new JPanel();
        jp_contact1.setOpaque(false);

        lbl_delete_id = new JLabel("Staff id:");
        lbl_delete_id.setHorizontalAlignment(JLabel.CENTER);
        lbl_delete_id.setFont(new Font("Verdana", Font.PLAIN, 30));


        txt_delete_id = new JTextField ();
        txt_delete_id.setOpaque(false);
        txt_delete_id.setForeground(Color.BLACK);
        txt_delete_id.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_contactMid = new JPanel();
        jp_contactMid.setOpaque(false);
        jp_contactMid.setLayout(new GridLayout(4, 2));
        jp_contactMid.add(lbl_delete_id);
        jp_contactMid.add(txt_delete_id);

        btn_delete = new JButton("Delete");
        btn_delete.addActionListener(this);
        btn_delete.setBackground(Color.orange);
        btn_delete.setHorizontalAlignment(JButton.CENTER);
        btn_delete.setFont(new Font("Verdana", Font.PLAIN, 20));

        jp_contact1 = new JPanel();
        jp_contact1.setOpaque(false);
        jp_contact1.setLayout(new BorderLayout());
        jp_contact1.add(jp_contactMid, BorderLayout.CENTER);
        jp_contact1.add(btn_delete, BorderLayout.SOUTH);
    }

    public void staffDataSearch() {
        btn_userName = new JLabel("Staff's user name");
        btn_userName.setForeground(Color.blue);
        btn_userName.setBackground(Color.ORANGE);
        btn_userName.setFont(new Font("Verdana", Font.PLAIN, 15));

        txt_userName = new JTextField(10);

        btn_staffSearch = new JButton("Search");
        btn_staffSearch.setOpaque(false);
        btn_staffSearch.addActionListener(this);
        btn_staffSearch.setBackground(Color.GRAY);
        btn_staffSearch.setForeground(Color.red);
        btn_staffSearch.setHorizontalAlignment(JButton.CENTER);
        btn_staffSearch.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_ticketTop = new JPanel();
        jp_ticketTop.setOpaque(false);
        jp_ticketTop.add(btn_userName);
        jp_ticketTop.add(txt_userName);
        jp_ticketTop.add(btn_staffSearch);

        t_staffTable = new DefaultTableModel(
                getStaffTable(),
                new String[] {"User_id", "User_Account","User_Name", "Authority"});

        Jt_staff = new JTable(t_staffTable);

        JPanel panel_middle = new JPanel(null);

        Js_staff = new JScrollPane(Jt_staff);
        Js_staff.setBounds( 200,20 , 1100, 500 ); // x, y, width, height
        panel_middle.add(Js_staff,BorderLayout.CENTER);

        JPanel jp_tickedBl = new JPanel();
        jp_tickedBl.setOpaque(false);
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());

        jp_staffDataSearch = new JPanel();
        jp_staffDataSearch.setOpaque(false);
        jp_staffDataSearch.setBorder(new TitledBorder("Staff Search"));
        jp_staffDataSearch.setLayout(new BorderLayout());
        jp_staffDataSearch.add(jp_ticketTop, BorderLayout.NORTH);
        jp_staffDataSearch.add(panel_middle, BorderLayout.CENTER);
        jp_staffDataSearch.add(jp_tickedBl, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e){
        String add_ac = txt_add_userAc.getText();
        String add_pin = txt_add_pin.getText();
        String add_name = txt_add_userName.getText();

        String id = txt_delete_id.getText();

        if (e.getSource() == btn_addStaff) {
            jp_contact.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact);
            jp_right.updateUI();
            //getSelfInfo();
            validate();
            return;
        }

        if(e.getSource() == btn_add){
            StaffSQL staffsql = new StaffSQL();
            boolean success = staffsql.insertStaff(add_ac, add_pin, add_name);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Add successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Add failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;

        }


        if (e.getSource() == btn_deleteStaff) {
            jp_contact1.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact1);
            jp_right.updateUI();
            //getSelfInfo();
            validate();
            return;
        }

        if(e.getSource() == btn_delete){
            StaffSQL staffsql = new StaffSQL();
            boolean success = staffsql.deleteStaff(id);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Delete successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;

        }

        if (e.getSource() == btn_staffDataSearch) {
            jp_contact.setBorder(new TitledBorder("Staff list"));
            jp_right.removeAll();
            jp_right.add(jp_staffDataSearch);
            jp_right.updateUI();

        }

        if (e.getSource() == btn_showManagement) {
            new MShowManagementWindow("Show management");
            this.dispose();
        }

        if (e.getSource() == btn_giftCardManagement) {
            new MGiftCardWindow("Gift card management");
            this.dispose();
        }

        if (e.getSource() == btn_reports) {
            new MReportsWindow("Reports");
            this.dispose();
        }

        if (e.getSource() == btn_movieManagement) {
            new MMovieManagementWindow("Movie management");
            this.dispose();
        }

        if (e.getSource() == btn_logout) {
            new LoginWindow("Login as user");
            this.dispose();
        }

        if(e.getSource() == btn_staffSearch){
            String userName = txt_userName.getText().trim();

            searchStaff(userName);

            // update the table panel
            staffDataSearch();
            // update the right UI
            jp_right.removeAll();
            jp_right.add(jp_staffDataSearch);
            jp_right.updateUI();
            return;
        }
        return;
    }

    private void searchStaff(String name){
        StaffSQL staffsql = new StaffSQL();
        Object[][] result = staffsql.managerStaffSelection(name);
        setStaffTable(result);
    }

    public static Object[][] getStaffTable() {
        return staffTable;
    }

    public static void setStaffTable(Object[][] table) {
        staffTable = table;
    }

    public static User getUser() {
        return user;
    }
}