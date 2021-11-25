package windows;

import entity.*;
import sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class SMovieManagementWindow extends JFrame implements ActionListener{
    private JButton btn_deleteFilm, btn_modify, btn_addFilm, btn_add, btn_updateFilm, btn_update;
    private JButton btn_giftCardManagement, btn_showManagement, btn_reports, btn_logout;
    private JButton btn_movieDataSearch;
    private JPanel jp_right, jp_left, jp_movieDataSearch, jp_contact, jp_contact1, jp_contact2;

    private JTextField txt_filmName, txt_delete_id;
    private JTextField txt_add_name, txt_add_type, txt_add_uptime, txt_add_release, txt_add_director, txt_add_cast, txt_add_synposis;
    private JTextField txt_update_id, txt_update_name, txt_update_type, txt_update_uptime, txt_update_release, txt_update_director, txt_update_cast, txt_update_synposis;
    private JLabel btn_filmname, btn_type, lbl_delete_id;
    private JLabel lbl_add_name, lbl_add_type, lbl_add_uptime, lbl_add_release, lbl_add_director, lbl_add_cast, lbl_add_synposis;
    private JLabel lbl_update_id, lbl_update_name, lbl_update_type, lbl_update_uptime, lbl_update_release, lbl_update_director, lbl_update_cast, lbl_update_synposis;
    private JButton btn_filmSearch;
    private JComboBox<String> com_type;

    private DefaultTableModel t_Filmtable;
    private JTable Jt_Film;
    private JScrollPane Js_Film;

    private static Film film = new Film();
    private static Object[][] filmtable;

    public SMovieManagementWindow(String title){
        JLabel lbl_show = new JLabel("Movie Data Management");
        lbl_show.setForeground(Color.BLACK);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 40));
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);


        // Login interface function button module

        btn_giftCardManagement = new JButton("Manage gift cards");
        btn_giftCardManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_giftCardManagement.addActionListener(this);

        btn_showManagement = new JButton("Manage shows");
        btn_showManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_showManagement.addActionListener(this);

        btn_reports = new JButton("View reports");
        btn_reports.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_reports.addActionListener(this);

        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_logout.addActionListener(this);

        JPanel jp_FunctionBtn = new JPanel();
        jp_FunctionBtn.setOpaque(false);
        jp_FunctionBtn.add(btn_giftCardManagement);
        jp_FunctionBtn.add(btn_showManagement);
        jp_FunctionBtn.add(btn_reports);
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

        movieDataSearch();
        initFilmOperationButton();
        addFilmModule();
        updateFilmModule();
        deleteFilmModule();

        // Left part
        jp_left = new JPanel();
        jp_left.setOpaque(false);
        jp_left.setLayout(new GridLayout(4, 2));
        jp_left.add(btn_movieDataSearch);
        jp_left.add(btn_addFilm);
        jp_left.add(btn_updateFilm);
        jp_left.add(btn_deleteFilm);

        // right part
        jp_right = new JPanel();
        jp_right.setOpaque(false);
        jp_right.setLayout(new BorderLayout());
        jp_right.add(jp_movieDataSearch, BorderLayout.CENTER);

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

    private void initFilmOperationButton() {
        btn_movieDataSearch = new JButton("Film Search");
        btn_movieDataSearch.setOpaque(false);
        btn_movieDataSearch.addActionListener(this);
        btn_movieDataSearch.setForeground(Color.red);
        btn_movieDataSearch.setBackground(Color.DARK_GRAY);
        btn_movieDataSearch.setHorizontalAlignment(JLabel.CENTER);
        btn_movieDataSearch.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_movieDataSearch.setBounds(2,2,20,20);

        btn_addFilm = new JButton("Add Film");
        btn_addFilm.setOpaque(false);
        btn_addFilm.addActionListener(this);
        btn_addFilm.setForeground(Color.red);
        btn_addFilm.setBackground(Color.DARK_GRAY);
        btn_addFilm.setHorizontalAlignment(JLabel.CENTER);
        btn_addFilm.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_addFilm.setBounds(2,2,20,20);

        btn_updateFilm = new JButton("Update Film");
        btn_updateFilm.setOpaque(false);
        btn_updateFilm.addActionListener(this);
        btn_updateFilm.setForeground(Color.red);
        btn_updateFilm.setBackground(Color.DARK_GRAY);
        btn_updateFilm.setHorizontalAlignment(JLabel.CENTER);
        btn_updateFilm.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_updateFilm.setBounds(2,2,20,20);

        btn_deleteFilm = new JButton("Delete Film");
        btn_deleteFilm.setOpaque(false);
        btn_deleteFilm.addActionListener(this);
        btn_deleteFilm.setForeground(Color.red);
        btn_deleteFilm.setBackground(Color.DARK_GRAY);
        btn_deleteFilm.setHorizontalAlignment(JLabel.CENTER);
        btn_deleteFilm.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_deleteFilm.setBounds(2,2,20,20);

    }

    private void addFilmModule() {

        jp_contact1 = new JPanel();
        jp_contact1.setOpaque(false);

        lbl_add_name = new JLabel("Film name:");
        lbl_add_name.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_name.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_type = new JLabel("Film classification:");
        lbl_add_type.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_type.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_uptime = new JLabel("Film upcoming time:");
        lbl_add_uptime.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_uptime.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_release = new JLabel("Film release time:");
        lbl_add_release.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_release.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_director = new JLabel("Film director:");
        lbl_add_director.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_director.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_cast = new JLabel("Film cast:");
        lbl_add_cast.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_cast.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_add_synposis = new JLabel("Film synposis:");
        lbl_add_synposis.setHorizontalAlignment(JLabel.CENTER);
        lbl_add_synposis.setFont(new Font("Verdana", Font.PLAIN, 30));

        txt_add_name = new JTextField ();
        txt_add_name.setOpaque(false);
        txt_add_name.setForeground(Color.BLACK);
        txt_add_name.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_type = new JTextField ();
        txt_add_type.setOpaque(false);
        txt_add_type.setForeground(Color.BLACK);
        txt_add_type.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_uptime = new JTextField ();
        txt_add_uptime.setOpaque(false);
        txt_add_uptime.setForeground(Color.BLACK);
        txt_add_uptime.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_release = new JTextField ();
        txt_add_release.setOpaque(false);
        txt_add_release.setForeground(Color.BLACK);
        txt_add_release.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_director = new JTextField ();
        txt_add_director.setOpaque(false);
        txt_add_director.setForeground(Color.BLACK);
        txt_add_director.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_cast = new JTextField ();
        txt_add_cast.setOpaque(false);
        txt_add_cast.setForeground(Color.BLACK);
        txt_add_cast.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_add_synposis = new JTextField ();
        txt_add_synposis.setOpaque(false);
        txt_add_synposis.setForeground(Color.BLACK);
        txt_add_synposis.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_contactMid = new JPanel();
        jp_contactMid.setOpaque(false);
        jp_contactMid.setLayout(new GridLayout(7, 2));
        jp_contactMid.add(lbl_add_name);
        jp_contactMid.add(txt_add_name);
        jp_contactMid.add(lbl_add_type);
        jp_contactMid.add(txt_add_type);
        jp_contactMid.add(lbl_add_uptime);
        jp_contactMid.add(txt_add_uptime);
        jp_contactMid.add(lbl_add_release);
        jp_contactMid.add(txt_add_release);
        jp_contactMid.add(lbl_add_director);
        jp_contactMid.add(txt_add_director);
        jp_contactMid.add(lbl_add_cast);
        jp_contactMid.add(txt_add_cast);
        jp_contactMid.add(lbl_add_synposis);
        jp_contactMid.add(txt_add_synposis);

        btn_add = new JButton("Add");
        btn_add.addActionListener(this);
        btn_add.setBackground(Color.orange);
        btn_add.setHorizontalAlignment(JButton.CENTER);
        btn_add.setFont(new Font("Verdana", Font.PLAIN, 20));

        jp_contact1 = new JPanel();
        jp_contact1.setOpaque(false);
        jp_contact1.setLayout(new BorderLayout());
        jp_contact1.add(jp_contactMid, BorderLayout.CENTER);
        jp_contact1.add(btn_add, BorderLayout.SOUTH);
    }

    private void updateFilmModule() {

        jp_contact2 = new JPanel();
        jp_contact2.setOpaque(false);

        lbl_update_id = new JLabel("Film id:");
        lbl_update_id.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_id.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_name = new JLabel("Film name:");
        lbl_update_name.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_name.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_type = new JLabel("Film classification:");
        lbl_update_type.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_type.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_uptime = new JLabel("Film upcoming time:");
        lbl_update_uptime.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_uptime.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_release = new JLabel("Film release time:");
        lbl_update_release.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_release.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_director = new JLabel("Film director:");
        lbl_update_director.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_director.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_cast = new JLabel("Film cast:");
        lbl_update_cast.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_cast.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_update_synposis = new JLabel("Film synposis:");
        lbl_update_synposis.setHorizontalAlignment(JLabel.CENTER);
        lbl_update_synposis.setFont(new Font("Verdana", Font.PLAIN, 30));

        txt_update_id = new JTextField ();
        txt_update_id.setOpaque(false);
        txt_update_id.setForeground(Color.BLACK);
        txt_update_id.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_name = new JTextField ();
        txt_update_name.setOpaque(false);
        txt_update_name.setForeground(Color.BLACK);
        txt_update_name.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_type = new JTextField ();
        txt_update_type.setOpaque(false);
        txt_update_type.setForeground(Color.BLACK);
        txt_update_type.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_uptime = new JTextField ();
        txt_update_uptime.setOpaque(false);
        txt_update_uptime.setForeground(Color.BLACK);
        txt_update_uptime.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_release = new JTextField ();
        txt_update_release.setOpaque(false);
        txt_update_release.setForeground(Color.BLACK);
        txt_update_release.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_director = new JTextField ();
        txt_update_director.setOpaque(false);
        txt_update_director.setForeground(Color.BLACK);
        txt_update_director.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_cast = new JTextField ();
        txt_update_cast.setOpaque(false);
        txt_update_cast.setForeground(Color.BLACK);
        txt_update_cast.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_update_synposis = new JTextField ();
        txt_update_synposis.setOpaque(false);
        txt_update_synposis.setForeground(Color.BLACK);
        txt_update_synposis.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_contactMid = new JPanel();
        jp_contactMid.setOpaque(false);
        jp_contactMid.setLayout(new GridLayout(8, 2));
        jp_contactMid.add(lbl_update_id);
        jp_contactMid.add(txt_update_id);
        jp_contactMid.add(lbl_update_name);
        jp_contactMid.add(txt_update_name);
        jp_contactMid.add(lbl_update_type);
        jp_contactMid.add(txt_update_type);
        jp_contactMid.add(lbl_update_uptime);
        jp_contactMid.add(txt_update_uptime);
        jp_contactMid.add(lbl_update_release);
        jp_contactMid.add(txt_update_release);
        jp_contactMid.add(lbl_update_director);
        jp_contactMid.add(txt_update_director);
        jp_contactMid.add(lbl_update_cast);
        jp_contactMid.add(txt_update_cast);
        jp_contactMid.add(lbl_update_synposis);
        jp_contactMid.add(txt_update_synposis);


        btn_update = new JButton("Update");
        btn_update.addActionListener(this);
        btn_update.setBackground(Color.orange);
        btn_update.setHorizontalAlignment(JButton.CENTER);
        btn_update.setFont(new Font("Verdana", Font.PLAIN, 20));


        jp_contact2 = new JPanel();
        jp_contact2.setOpaque(false);
        jp_contact2.setLayout(new BorderLayout());
        jp_contact2.add(jp_contactMid, BorderLayout.CENTER);
        jp_contact2.add(btn_update, BorderLayout.SOUTH);
    }

    private void deleteFilmModule() {

        jp_contact = new JPanel();
        jp_contact.setOpaque(false);

        lbl_delete_id = new JLabel("Film id:");
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

        btn_modify = new JButton("Delete");
        btn_modify.addActionListener(this);
        btn_modify.setBackground(Color.orange);
        btn_modify.setHorizontalAlignment(JButton.CENTER);
        btn_modify.setFont(new Font("Verdana", Font.PLAIN, 20));

        jp_contact = new JPanel();
        jp_contact.setOpaque(false);
        jp_contact.setLayout(new BorderLayout());
        jp_contact.add(jp_contactMid, BorderLayout.CENTER);
        jp_contact.add(btn_modify, BorderLayout.SOUTH);
    }

    private void movieDataSearch() {

        btn_filmname = new JLabel("Film name");
        btn_filmname.setForeground(Color.blue);
        btn_filmname.setBackground(Color.ORANGE);
        btn_filmname.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_type = new JLabel("Film type");
        btn_type.setForeground(Color.blue);
        btn_type.setBackground(Color.ORANGE);
        btn_type.setFont(new Font("Verdana", Font.PLAIN, 15));

        txt_filmName = new JTextField(10);
        com_type = new JComboBox<>(new String[]{"all","G", "PG-13","M","MA-15","R"});

        btn_filmSearch = new JButton("Search");
        btn_filmSearch.setOpaque(false);
        btn_filmSearch.addActionListener(this);
        btn_filmSearch.setBackground(Color.GRAY);
        btn_filmSearch.setForeground(Color.red);
        btn_filmSearch.setHorizontalAlignment(JButton.CENTER);
        btn_filmSearch.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_ticketTop = new JPanel();
        jp_ticketTop.setOpaque(false);
        jp_ticketTop.add(btn_filmname);
        jp_ticketTop.add(txt_filmName);
        jp_ticketTop.add(btn_type);
        jp_ticketTop.add(com_type);
        jp_ticketTop.add(btn_filmSearch);

        //2
        //Object[][] table == searchfilm()
        t_Filmtable = new DefaultTableModel(
                getFilmtable(),
                new String[] {"Film_id", "Film_name","Type", "Upcoming Times", "Release", "Director", "Casts", "Film synopsis"} );

        Jt_Film = new JTable(t_Filmtable);

        JPanel panel_middle = new JPanel(null);

        Js_Film = new JScrollPane(Jt_Film);
        Js_Film.setBounds( 200,20 , 1100, 500 ); // x, y, width, height
        panel_middle.add(Js_Film,BorderLayout.CENTER);
        //3

        JPanel jp_tickedBl = new JPanel();
        jp_tickedBl.setOpaque(false);
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());

        jp_movieDataSearch = new JPanel();
        jp_movieDataSearch.setOpaque(false);
        jp_movieDataSearch.setBorder(new TitledBorder("Movie Search"));
        jp_movieDataSearch.setLayout(new BorderLayout());
        jp_movieDataSearch.add(jp_ticketTop, BorderLayout.NORTH);
        jp_movieDataSearch.add(panel_middle, BorderLayout.CENTER);
        jp_movieDataSearch.add(jp_tickedBl, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e){
        String add_name = txt_add_name.getText();
        String add_type = txt_add_type.getText();
        String add_up = txt_add_uptime.getText();
        String add_release = txt_add_release.getText();
        String add_director = txt_add_director.getText();
        String add_cast = txt_add_cast.getText();
        String add_synposis = txt_add_synposis.getText();

        String update_id = txt_update_id.getText();
        String update_name = txt_update_name.getText();
        String update_type = txt_update_type.getText();
        String update_up = txt_update_uptime.getText();
        String update_release = txt_update_release.getText();
        String update_director = txt_update_director.getText();
        String update_cast = txt_update_cast.getText();
        String update_synposis = txt_update_synposis.getText();

        String film_d = txt_delete_id.getText();

        if (e.getSource() == btn_addFilm) {
            jp_contact1.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact1);
            jp_right.updateUI();
            //getSelfInfo();
            validate();
            return;
        }

        if(e.getSource() == btn_add){
            FilmSQL filmsql = new FilmSQL();
            boolean success = filmsql.insertFilm(add_name, add_type, add_up, add_release, add_director, add_cast, add_synposis);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Add successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Add failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;

        }

        if (e.getSource() == btn_updateFilm) {
            jp_contact2.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact2);
            jp_right.updateUI();
            //getSelfInfo();
            validate();
            return;
        }

        if(e.getSource() == btn_update){
            FilmSQL filmsql = new FilmSQL();
            boolean success = filmsql.updateFilm(update_id, update_name, update_type, update_up, update_release, update_director, update_cast, update_synposis);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Update successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;

        }

        if (e.getSource() == btn_deleteFilm) {
            jp_contact.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact);
            jp_right.updateUI();
            //getSelfInfo();
            validate();
            return;
        }

        if(e.getSource() == btn_modify){
            FilmSQL filmsql = new FilmSQL();
            boolean success = filmsql.deleteFilm(film_d);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Delete successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;

        }

        if (e.getSource() == btn_movieDataSearch) {
            jp_contact.setBorder(new TitledBorder("Film list"));
            jp_right.removeAll();
            jp_right.add(jp_movieDataSearch);
            jp_right.updateUI();

        }

        if (e.getSource() == btn_showManagement) {
            new SShowManagementWindow("Show management");
            this.dispose();
        }

        if (e.getSource() == btn_giftCardManagement) {
            new SGiftCardWindow("Gift card management");
            this.dispose();
        }

        if (e.getSource() == btn_reports) {
            new SReportsWindow("Reports");
            this.dispose();
        }

        if (e.getSource() == btn_logout) {
            new LoginWindow("Login as user");
            this.dispose();
        }

        if(e.getSource() == btn_filmSearch){
            String filmname = txt_filmName.getText().trim();
            String filmtype = Objects.requireNonNull(com_type.getSelectedItem()).toString();

            searchfilm(filmname,filmtype);

            // update the table panel
            movieDataSearch();
            // update the right UI
            jp_right.removeAll();
            jp_right.add(jp_movieDataSearch);
            jp_right.updateUI();
            return;
        }
        return;
    }

    private void searchfilm(String name, String filmtype){
        FilmSQL filmsql = new FilmSQL();
        Object[][] result = filmsql.staffMovieSelection(name,filmtype);
        setFilmtable(result);
    }

    public static Object[][] getFilmtable() {
        return filmtable;
    }

    public static void setFilmtable(Object[][] table) {
        filmtable = table;
    }

    public static Film getFilm() {
        return film;
    }

}