package windows;

import entity.*;
import sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class MShowManagementWindow extends JFrame implements ActionListener {
    private JButton btn_giftCardManagement, btn_movieManagement, btn_reports, btn_staffManagement, btn_logout;
    private JButton btn_showBusiness, btn_addShow, btn_add;
    private JLabel lbl_add_fId, lbl_add_lId, lbl_add_screenSize, lbl_add_release, lbl_add_front, lbl_add_middle, lbl_add_rear, lbl_add_price;
    private JPanel jp_left, jp_right, jp_showBusiness, jp_contact;

    private JTextField txt_filmName, txt_time;
    private JTextField txt_add_fId, txt_add_lId, txt_add_screenSize, txt_add_release, txt_add_front, txt_add_middle, txt_add_rear, txt_add_price;
    private JLabel btn_filmName, btn_type, btn_screen, btn_location, btn_time;
    private JButton btn_filmSearch;
    private JComboBox<String> com_screen, com_type, com_Location;

    private DefaultTableModel t_Filmtable;
    private JTable Jt_Film;
    private JScrollPane Js_Film;

    private static Show show = new Show();
    private static Object[][] filmtable;

    public MShowManagementWindow(String s) {

        //Interface display information panel
        JLabel lbl_show = new JLabel("Show Management");
        lbl_show.setForeground(Color.BLACK);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 40));
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        // Login interface function button module
        btn_giftCardManagement = new JButton("Manage gift cards");
        btn_giftCardManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_giftCardManagement.addActionListener(this);

        btn_movieManagement = new JButton("Manage movie data");
        btn_movieManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_movieManagement.addActionListener(this);

        btn_reports = new JButton("View reports");
        btn_reports.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_reports.addActionListener(this);

        btn_staffManagement = new JButton("Manage staff");
        btn_staffManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_staffManagement.addActionListener(this);

        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_logout.addActionListener(this);


        JPanel jp_FunctionBtn = new JPanel();
        jp_FunctionBtn.setOpaque(false);
        jp_FunctionBtn.add(btn_giftCardManagement);
        jp_FunctionBtn.add(btn_movieManagement);
        jp_FunctionBtn.add(btn_reports);
        jp_FunctionBtn.add(btn_staffManagement);
        jp_FunctionBtn.add(btn_logout);

        //Set the main panel layout and add the above customized panel
        this.setLayout(new BorderLayout());
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_FunctionBtn, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initShowOperationButton();
        showBusinessModule();
        addShowModule();

        // left part
        jp_left = new JPanel();
        jp_left.setOpaque(false);
        jp_left.setLayout(new GridLayout(4, 2));
        jp_left.add(btn_showBusiness);
        jp_left.add(btn_addShow);

        // right part
        jp_right = new JPanel();
        jp_right.setOpaque(false);
        jp_right.setLayout(new BorderLayout());
        jp_right.add(jp_showBusiness, BorderLayout.CENTER);

        // Add right and lift together
        this.add(jp_left, BorderLayout.WEST);
        this.add(jp_right, BorderLayout.CENTER);
        this.validate();
        this.setVisible(true);
        this.setSize(1800, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initShowOperationButton() {
        btn_showBusiness = new JButton("Show Search");
        btn_showBusiness.setOpaque(false);
        btn_showBusiness.addActionListener(this);
        btn_showBusiness.setForeground(Color.red);
        btn_showBusiness.setBackground(Color.DARK_GRAY);
        btn_showBusiness.setHorizontalAlignment(JLabel.CENTER);
        btn_showBusiness.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_showBusiness.setBounds(2, 2, 20, 20);

        btn_addShow = new JButton("Add Show");
        btn_addShow.setOpaque(false);
        btn_addShow.addActionListener(this);
        btn_addShow.setForeground(Color.red);
        btn_addShow.setBackground(Color.DARK_GRAY);
        btn_addShow.setHorizontalAlignment(JLabel.CENTER);
        btn_addShow.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_addShow.setBounds(2, 2, 20, 20);
    }

    private void addShowModule() {

        jp_contact = new JPanel();
        jp_contact.setOpaque(false);

        lbl_add_fId = new JLabel("Film id:");
        lbl_add_fId.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_fId.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_lId = new JLabel("Location id:");
        lbl_add_lId.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_lId.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_screenSize = new JLabel("Show screen size:");
        lbl_add_screenSize.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_screenSize.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_release = new JLabel("Show release time:");
        lbl_add_release.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_release.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_front = new JLabel("Front seats number:");
        lbl_add_front.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_front.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_middle = new JLabel("Middle seats number:");
        lbl_add_middle.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_middle.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_rear = new JLabel("Rear seats number:");
        lbl_add_rear.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_rear.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_price = new JLabel("Show price:");
        lbl_add_price.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_price.setFont(new Font("Verdana", Font.PLAIN, 30));

        txt_add_fId = new JTextField();
        txt_add_fId.setOpaque(false);
        txt_add_fId.setForeground(Color.BLACK);
        txt_add_fId.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_lId = new JTextField();
        txt_add_lId.setOpaque(false);
        txt_add_lId.setForeground(Color.BLACK);
        txt_add_lId.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_screenSize = new JTextField();
        txt_add_screenSize.setOpaque(false);
        txt_add_screenSize.setForeground(Color.BLACK);
        txt_add_screenSize.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_release = new JTextField();
        txt_add_release.setOpaque(false);
        txt_add_release.setForeground(Color.BLACK);
        txt_add_release.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_front = new JTextField();
        txt_add_front.setOpaque(false);
        txt_add_front.setForeground(Color.BLACK);
        txt_add_front.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_middle = new JTextField();
        txt_add_middle.setOpaque(false);
        txt_add_middle.setForeground(Color.BLACK);
        txt_add_middle.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_rear = new JTextField();
        txt_add_rear.setOpaque(false);
        txt_add_rear.setForeground(Color.BLACK);
        txt_add_rear.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_price = new JTextField();
        txt_add_price.setOpaque(false);
        txt_add_price.setForeground(Color.BLACK);
        txt_add_price.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_contactMid = new JPanel();
        jp_contactMid.setOpaque(false);
        jp_contactMid.setLayout(new GridLayout(8, 2));
        jp_contactMid.add(lbl_add_fId);
        jp_contactMid.add(txt_add_fId);
        jp_contactMid.add(lbl_add_lId);
        jp_contactMid.add(txt_add_lId);
        jp_contactMid.add(lbl_add_screenSize);
        jp_contactMid.add(txt_add_screenSize);
        jp_contactMid.add(lbl_add_release);
        jp_contactMid.add(txt_add_release);
        jp_contactMid.add(lbl_add_front);
        jp_contactMid.add(txt_add_front);
        jp_contactMid.add(lbl_add_middle);
        jp_contactMid.add(txt_add_middle);
        jp_contactMid.add(lbl_add_rear);
        jp_contactMid.add(txt_add_rear);
        jp_contactMid.add(lbl_add_price);
        jp_contactMid.add(txt_add_price);

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

    private void showBusinessModule() {

        btn_filmName = new JLabel("Film name");
        btn_filmName.setForeground(Color.blue);
        btn_filmName.setBackground(Color.ORANGE);
        btn_filmName.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_type = new JLabel("Film type");
        btn_type.setForeground(Color.blue);
        btn_type.setBackground(Color.ORANGE);
        btn_type.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_screen = new JLabel("Screen size");
        btn_screen.setForeground(Color.blue);
        btn_screen.setBackground(Color.ORANGE);
        btn_screen.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_location = new JLabel("Location");
        btn_location.setForeground(Color.blue);
        btn_location.setBackground(Color.ORANGE);
        btn_location.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_time = new JLabel("Show date");
        btn_time.setForeground(Color.blue);
        btn_time.setBackground(Color.ORANGE);
        btn_time.setFont(new Font("Verdana", Font.PLAIN, 15));

        txt_filmName = new JTextField(10);
        txt_time = new JTextField(10);
        com_type = new JComboBox<>(new String[]{"all", "G", "PG-13", "M", "MA-15", "R"});
        com_screen = new JComboBox<>(new String[]{"all", "gold", "silver", "bronze"});
        com_Location = new JComboBox<>(new String[]{"all", "Ultimo", "TownHalL", "Redfern"});

        btn_filmSearch = new JButton("Search");
        btn_filmSearch.setOpaque(false);
        btn_filmSearch.addActionListener(this);
        btn_filmSearch.setBackground(Color.GRAY);
        btn_filmSearch.setForeground(Color.red);
        btn_filmSearch.setHorizontalAlignment(JButton.CENTER);
        btn_filmSearch.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_showTop = new JPanel();
        jp_showTop.setOpaque(false);
        jp_showTop.add(btn_filmName);
        jp_showTop.add(txt_filmName);
        jp_showTop.add(btn_type);
        jp_showTop.add(com_type);
        jp_showTop.add(btn_screen);
        jp_showTop.add(com_screen);
        jp_showTop.add(btn_location);
        jp_showTop.add(com_Location);
        jp_showTop.add(btn_time);
        jp_showTop.add(txt_time);
        jp_showTop.add(btn_filmSearch);

        //2
        //Object[][] table == searchfilm()
        t_Filmtable = new DefaultTableModel(
                getFilmtable(),
                new String[]{"Show_id", "Film_name", "Screen", "Location", "type", "Show Time", "Price", "Front Remaining", "Middle Remaining", "Rear Remaining",
                        "director", "casts", "film synopsis"});

        Jt_Film = new JTable(t_Filmtable);

        JPanel panel_middle = new JPanel(null);

        Js_Film = new JScrollPane(Jt_Film);
        Js_Film.setBounds(200, 20, 1100, 500); // x, y, width, height
        panel_middle.add(Js_Film, BorderLayout.CENTER);
        //3


        JPanel jp_tickedBl = new JPanel();
        jp_tickedBl.setOpaque(false);
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());

        jp_showBusiness = new JPanel();
        jp_showBusiness.setOpaque(false);
        jp_showBusiness.setBorder(new TitledBorder("Show Search"));
        jp_showBusiness.setLayout(new BorderLayout());
        jp_showBusiness.add(jp_showTop, BorderLayout.NORTH);
        jp_showBusiness.add(panel_middle, BorderLayout.CENTER);
        jp_showBusiness.add(jp_tickedBl, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String add_fId = txt_add_fId.getText();
        String add_lId = txt_add_lId.getText();
        String add_screenSize = txt_add_screenSize.getText();
        String add_release = txt_add_release.getText();
        String add_front = txt_add_front.getText();
        String add_middle = txt_add_middle.getText();
        String add_rear = txt_add_rear.getText();
        String add_price = txt_add_price.getText();

        if (e.getSource() == btn_addShow) {
            jp_contact.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact);
            jp_right.updateUI();
            //getSelfInfo();
            validate();
            return;
        }

        if (e.getSource() == btn_add) {
            ShowSQL showsql = new ShowSQL();
            boolean success = showsql.insertShow(add_fId, add_lId, add_screenSize, add_release, add_front, add_middle, add_rear, add_price);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Add successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Add failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;

        }

        if (e.getSource() == btn_movieManagement) {
            new MMovieManagementWindow("Movie management");
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

        if (e.getSource() == btn_staffManagement) {
            new MStaffManagementWindow("Staff management");
            this.dispose();
        }

        if (e.getSource() == btn_logout) {
            new MainWindow("Movie reservation system");
            this.dispose();
        }

        if (e.getSource() == btn_showBusiness) {
            jp_contact.setBorder(new TitledBorder("Show list"));
            jp_right.removeAll();
            jp_right.add(jp_showBusiness);
            jp_right.updateUI();
        }

        if (e.getSource() == btn_filmSearch) {
            String name = txt_filmName.getText().trim();
            String type = Objects.requireNonNull(com_type.getSelectedItem()).toString();
            String screen = Objects.requireNonNull(com_screen.getSelectedItem()).toString();
            String location = Objects.requireNonNull(com_Location.getSelectedItem()).toString();
            String time = txt_time.getText().trim();

            searchfilm(name, type, screen, location, time);

            // update the table panel
            showBusinessModule();
            // update the right UI
            jp_right.removeAll();
            jp_right.add(jp_showBusiness);
            jp_right.updateUI();
            return;
        }

    }

    private void searchfilm(String name, String filmtype, String screensize, String location, String time) {
        ShowSQL showsql = new ShowSQL();
        Object[][] result = showsql.usershowselection(name, filmtype, screensize, location, time);
        setFilmtable(result);
    }

    public static Object[][] getFilmtable() {
        return filmtable;
    }

    public static void setFilmtable(Object[][] table) {
        filmtable = table;
    }

    public static Show getShow() {
        return show;
    }
}

