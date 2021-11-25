package windows;


import entity.*;
import sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @Project: FIlmTicketingSystem
 * @Date: 21/10/2021
 * @author: zhefu wu
 * @Description: TODO User Window setting
 */

public class UserWindow extends JFrame implements ActionListener{

    private JButton btn_ticketBusiness, btn_personal_info, btn_booking_history;
    private JPanel jp_right, jp_contact, jp_ticketBusiness, jp_history;

    private JTextField txt_filmName, txt_time;
    private JLabel btn_filmName, btn_type, btn_screen, btn_location, btn_time;
    private JButton btn_confirmBook, btn_sign_out, btn_filmSearch;
    private JComboBox<String> com_screen, com_type, com_Location;

    private JButton btn_self, btn_modify;
    private JLabel lbl_account, lbl_new_pin, lbl_new_pin2, lbl_id;
    private JTextField txt_account, txt_id;
    private JPasswordField txt_new_pin, txt_new_pin2;

    private JTextField txt_filmName2, txt_time2;
    private JLabel btn_filmName2, btn_time2;
    private JButton btn_sign_out2,btn_search,btn_cancel;

    private DefaultTableModel t_Filmtable,t_Booktable;
    private JTable Jt_Film, Jt_Book;
    private JScrollPane Js_Film, Js_Book;

    private static User user = LoginWindow.getUser();
    private static Show show = new Show();
    private static Object[][] filmtable;
    private static Object[][] histable;

    public static ArrayList<String> cancel_info = new ArrayList<>();

    public UserWindow(String s) {
        setTitle(s);
        String iconSrc = "picture/projector.jpg";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());

        initTicketAndContactButton();

        personalInfoModule();

        ticketBusinessModule();

        personalHistoryModule();

        // Left part
        JPanel jp_left = new JPanel();
        jp_left.setOpaque(false);
        jp_left.setLayout(new GridLayout(4, 2));
        jp_left.add(btn_ticketBusiness);
        jp_left.add(btn_personal_info);
        jp_left.add(btn_booking_history);

        // right part
        jp_right = new JPanel();
        jp_right.setOpaque(false);
        jp_right.setLayout(new BorderLayout());
        jp_right.add(jp_ticketBusiness, BorderLayout.CENTER);

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

    private void initTicketAndContactButton() {
        btn_ticketBusiness = new JButton("Movies in theaters");
        btn_ticketBusiness.setOpaque(false);
        btn_ticketBusiness.addActionListener(this);
        btn_ticketBusiness.setForeground(Color.red);
        btn_ticketBusiness.setBackground(Color.DARK_GRAY);
        btn_ticketBusiness.setHorizontalAlignment(JButton.CENTER);
        btn_ticketBusiness.setFont(new Font("Verdana", Font.PLAIN, 22));
        btn_ticketBusiness.setBounds(20,2,20,20);

        btn_personal_info = new JButton("Modify Personal Information");
        btn_personal_info.setOpaque(false);
        btn_personal_info.addActionListener(this);
        btn_personal_info.setForeground(Color.red);
        btn_personal_info.setBackground(Color.DARK_GRAY);
        btn_personal_info.setHorizontalAlignment(JLabel.CENTER);
        btn_personal_info.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_personal_info.setBounds(2,2,20,20);

        btn_booking_history = new JButton("<html>Booking History or<br>Cancel reservation</html>");
        btn_booking_history.setOpaque(false);
        btn_booking_history.addActionListener(this);
        btn_booking_history.setForeground(Color.red);
        btn_booking_history.setBackground(Color.DARK_GRAY);
        btn_booking_history.setHorizontalAlignment(JLabel.CENTER);
        btn_booking_history.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_booking_history.setBounds(2,2,20,20);
    }

    private void ticketBusinessModule() {

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
        com_type = new JComboBox<>(new String[]{"all","G", "PG-13","M","MA-15","R"});
        com_screen = new JComboBox<>(new String[]{"all","gold" , "silver", "bronze"});
        com_Location = new JComboBox<>(new String[]{"all","Ultimo" , "TownHalL", "Redfern"});

        btn_filmSearch = new JButton("Search");
        btn_filmSearch.setOpaque(false);
        btn_filmSearch.addActionListener(this);
        btn_filmSearch.setBackground(Color.GRAY);
        btn_filmSearch.setForeground(Color.red);
        btn_filmSearch.setHorizontalAlignment(JButton.CENTER);
        btn_filmSearch.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_ticketTop = new JPanel();
        jp_ticketTop.setOpaque(false);
        jp_ticketTop.add(btn_filmName);
        jp_ticketTop.add(txt_filmName);
        jp_ticketTop.add(btn_type);
        jp_ticketTop.add(com_type);
        jp_ticketTop.add(btn_screen);
        jp_ticketTop.add(com_screen);
        jp_ticketTop.add(btn_location);
        jp_ticketTop.add(com_Location);
        jp_ticketTop.add(btn_time);
        jp_ticketTop.add(txt_time);
        jp_ticketTop.add(btn_filmSearch);

        //2
        //Object[][] table == searchfilm()
        t_Filmtable = new DefaultTableModel(
                getFilmtable(),
                new String[] {"Show_id", "Film_name","Screen","Location","type","Show Time","Price","Front Remaining","Middle Remaining","Rear Remaining",
                        "director","casts","film synopsis"} );

        Jt_Film = new JTable(t_Filmtable);

        JPanel panel_middle = new JPanel(null);

        Js_Film = new JScrollPane(Jt_Film);
        Js_Film.setBounds( 200,20 , 1100, 500 ); // x, y, width, height
        panel_middle.add(Js_Film,BorderLayout.CENTER);
        //3

        btn_confirmBook = new JButton("Confirm book");
        btn_confirmBook.setOpaque(false);
        btn_confirmBook.addActionListener(this);
        btn_confirmBook.setBackground(Color.GRAY);
        btn_confirmBook.setForeground(Color.black);
        btn_confirmBook.setHorizontalAlignment(JButton.CENTER);
        btn_confirmBook.setFont(new Font("Verdana", Font.PLAIN, 20));

        btn_sign_out = new JButton("Sign out");
        btn_sign_out.setOpaque(false);
        btn_sign_out.addActionListener(this);
        btn_sign_out.setBackground(Color.GRAY);
        btn_sign_out.setForeground(Color.black);
        btn_sign_out.setHorizontalAlignment(JButton.CENTER);
        btn_sign_out.setFont(new Font("Verdana", Font.PLAIN, 20));


        JPanel jp_tickedBl = new JPanel();
        jp_tickedBl.setOpaque(false);
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(btn_sign_out);
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(btn_confirmBook);

        jp_ticketBusiness = new JPanel();
        jp_ticketBusiness.setOpaque(false);
        jp_ticketBusiness.setBorder(new TitledBorder("Show Search"));
        jp_ticketBusiness.setLayout(new BorderLayout());
        jp_ticketBusiness.add(jp_ticketTop, BorderLayout.NORTH);
        jp_ticketBusiness.add(panel_middle, BorderLayout.CENTER);
        jp_ticketBusiness.add(jp_tickedBl, BorderLayout.SOUTH);
    }

    private void personalInfoModule() {

        jp_contact = new JPanel();
        jp_contact.setOpaque(false);

        btn_self = new JButton("Personal info");
        btn_self.setOpaque(false);
        btn_self.addActionListener(this);
        btn_self.setBackground(Color.GRAY);
        btn_self.setForeground(Color.white);
        btn_self.setHorizontalAlignment(JButton.CENTER);
        btn_self.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_contactTop = new JPanel();
        jp_contactTop.setOpaque(false);
        jp_contactTop.add(btn_self);

        lbl_account = new JLabel("Account:");
        lbl_account.setHorizontalAlignment(JLabel.CENTER);
        lbl_account.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_new_pin = new JLabel("New Pin:");
        lbl_new_pin.setHorizontalAlignment(JLabel.CENTER);
        lbl_new_pin.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_new_pin2 = new JLabel("Confirm Pin:");
        lbl_new_pin2.setHorizontalAlignment(JLabel.CENTER);
        lbl_new_pin2.setFont(new Font("Verdana", Font.PLAIN, 30));

        lbl_id = new JLabel("Account ID:");
        lbl_id.setFont(new Font("Verdana", Font.PLAIN, 30));
        lbl_id.setHorizontalAlignment(JLabel.CENTER);

        txt_account = new JTextField();
        txt_account.setOpaque(false);
        txt_account.setEditable(false);
        txt_account.setForeground(Color.white);
        txt_account.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_new_pin = new  JPasswordField ();
        txt_new_pin.setOpaque(false);
        txt_new_pin.setForeground(Color.white);
        txt_new_pin.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_new_pin2 = new  JPasswordField ();
        txt_new_pin2.setOpaque(false);
        txt_new_pin2.setForeground(Color.white);
        txt_new_pin2.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_id = new JTextField();
        txt_id.setOpaque(false);
        txt_id.setEditable(false);
        txt_id.setForeground(Color.WHITE);
        txt_id.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_contactMid = new JPanel();
        jp_contactMid.setOpaque(false);
        jp_contactMid.setLayout(new GridLayout(4, 2));
        jp_contactMid.add(lbl_account);
        jp_contactMid.add(txt_account);
        jp_contactMid.add(lbl_new_pin);
        jp_contactMid.add(txt_new_pin);
        jp_contactMid.add(lbl_new_pin2);
        jp_contactMid.add(txt_new_pin2);
        jp_contactMid.add(lbl_id);
        jp_contactMid.add(txt_id);


        btn_modify = new JButton("Yes");
        btn_modify.addActionListener(this);
        btn_modify.setBackground(Color.orange);
        btn_modify.setHorizontalAlignment(JButton.CENTER);
        btn_modify.setFont(new Font("Verdana", Font.PLAIN, 20));


        jp_contact = new JPanel();
        jp_contact.setOpaque(false);
        jp_contact.setLayout(new BorderLayout());
        jp_contact.add(jp_contactTop, BorderLayout.NORTH);
        jp_contact.add(jp_contactMid, BorderLayout.CENTER);
        jp_contact.add(btn_modify, BorderLayout.SOUTH);
    }

    private void personalHistoryModule(){

        // Top part
        btn_filmName2 = new JLabel("Film name");
        btn_filmName2.setForeground(Color.blue);
        btn_filmName2.setBackground(Color.ORANGE);
        btn_filmName2.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_time2 = new JLabel("Show date");
        btn_time2.setForeground(Color.blue);
        btn_time2.setBackground(Color.ORANGE);
        btn_time2.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_search = new JButton("Search");
        btn_search.setOpaque(false);
        btn_search.addActionListener(this);
        btn_search.setHorizontalAlignment(JButton.CENTER);
        btn_search.setFont(new Font("Verdana", Font.PLAIN, 20));

        txt_filmName2 = new JTextField(10);
        txt_time2 = new JTextField(10);

        JPanel jp_ticketTop2 = new JPanel();
        jp_ticketTop2.setOpaque(false);
        jp_ticketTop2.add(btn_filmName2);
        jp_ticketTop2.add(txt_filmName2);
        jp_ticketTop2.add(btn_time2);
        jp_ticketTop2.add(txt_time2);
        jp_ticketTop2.add(btn_search);

        //Middle part
        t_Booktable = new DefaultTableModel(
                getHistable(),
                new String[] {"BookId","ShowId","Film name","Screen size","Location name", "Film classification","Show release","Purchase price","Tickets","front","middle","rear"} );

        Jt_Book = new JTable(t_Booktable);

        JPanel panel_middle2 = new JPanel(null);

        Js_Book = new JScrollPane(Jt_Book);
        Js_Book.setBounds( 200,20 , 1100, 500 ); // x, y, width, height
        panel_middle2.add(Js_Book,BorderLayout.CENTER);

        btn_sign_out2 = new JButton("Sign out");
        btn_sign_out2.setOpaque(false);
        btn_sign_out2.addActionListener(this);
        btn_sign_out2.setBackground(Color.GRAY);
        btn_sign_out2.setForeground(Color.black);
        btn_sign_out2.setHorizontalAlignment(JButton.CENTER);
        btn_sign_out2.setFont(new Font("Verdana", Font.PLAIN, 20));

        btn_cancel = new JButton("Cancel this book");
        btn_cancel.setOpaque(false);
        btn_cancel.addActionListener(this);
        btn_cancel.setBackground(Color.GRAY);
        btn_cancel.setForeground(Color.black);
        btn_cancel.setHorizontalAlignment(JButton.CENTER);
        btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 20));

        JPanel jp_tickedBl = new JPanel();
        jp_tickedBl.setOpaque(false);
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(new JLabel());
        jp_tickedBl.add(btn_sign_out2);
        jp_tickedBl.add(btn_cancel);

        jp_history = new JPanel();
        jp_history.setOpaque(false);
        jp_history.setBorder(new TitledBorder("history Search"));
        jp_history.setLayout(new BorderLayout());
        jp_history.add(jp_ticketTop2, BorderLayout.NORTH);
        jp_history.add(panel_middle2, BorderLayout.CENTER);
        jp_history.add(jp_tickedBl, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {

        String account = user.getAccount();
        String pwd1 = new String(txt_new_pin.getPassword());
        String pwd2 = new String(txt_new_pin2.getPassword());

        if (e.getSource() == btn_ticketBusiness) {
            jp_right.removeAll();
            jp_right.add(jp_ticketBusiness);
            //jp_right.add(jp_history);
            jp_right.updateUI();
            return;
        }

        if (e.getSource() == btn_personal_info || e.getSource() == btn_self) {
            jp_contact.setBorder(new TitledBorder("Change info"));
            jp_right.removeAll();
            jp_right.add(jp_contact);
            jp_right.updateUI();
            btn_self.setForeground(Color.blue);
            //getSelfInfo();
            validate();
            return;
        }

        if (e.getSource() == btn_booking_history){
            jp_right.removeAll();
            jp_right.add(jp_history);
            jp_right.updateUI();
            return;
        }

        if(e.getSource() == btn_filmSearch){
            String name = txt_filmName.getText().trim();
            String type = Objects.requireNonNull(com_type.getSelectedItem()).toString();
            String screen = Objects.requireNonNull(com_screen.getSelectedItem()).toString();
            String location = Objects.requireNonNull(com_Location.getSelectedItem()).toString();
            String time = txt_time.getText().trim();

            searchfilm(name,type,screen,location,time);

            // update the table panel
            ticketBusinessModule();
            // update the right UI
            jp_right.removeAll();
            jp_right.add(jp_ticketBusiness);
            jp_right.updateUI();
            return;
        }

        if (e.getSource()==btn_search){
            String userid = user.getUserid();
            String name = txt_filmName2.getText().trim();
            String time = txt_time2.getText().trim();

            searchhis(userid,name,time);
            // update the table panel
            personalHistoryModule();
            // update the right UI
            jp_right.removeAll();
            jp_right.add(jp_history);
            jp_right.updateUI();
            return;
        }

        if (e.getSource()==btn_cancel){
            int row = Jt_Book.getSelectedRow();
            String bookid = t_Booktable.getValueAt(row,0).toString();
            if (!bookid.equals("")){
                cancelbooking();
                JOptionPane.showMessageDialog(this, "Successfully cancel this book! ", "Successfully", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Can not be empty, please select a record! ", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
        }

        if (e.getSource() == btn_confirmBook) {
            int r = Jt_Film.getSelectedRow();

            String showid = t_Filmtable.getValueAt(r,0).toString();
            String filmname = t_Filmtable.getValueAt(r,1).toString();
            String screensize = t_Filmtable.getValueAt(r,2).toString();
            String location = t_Filmtable.getValueAt(r,3).toString();
            String type = t_Filmtable.getValueAt(r,4).toString();
            String Time = t_Filmtable.getValueAt(r,5).toString();
            float price = Float.parseFloat(t_Filmtable.getValueAt(r,6).toString());
            int front = Integer.parseInt(t_Filmtable.getValueAt(r,7).toString());
            int middle = Integer.parseInt(t_Filmtable.getValueAt(r,8).toString());
            int rear = Integer.parseInt(t_Filmtable.getValueAt(r,9).toString());
            String d = t_Filmtable.getValueAt(r,10).toString();
            String c = t_Filmtable.getValueAt(r,11).toString();
            String p = t_Filmtable.getValueAt(r,12).toString();

            System.out.println(showid);

            if (!showid.equals("")){
                show.setShowID(showid);
                show.setFilmName(filmname);
                show.setScreensize(screensize);
                show.setLocation(location);
                show.setType(type);
                show.setTime(Time);
                show.setPrice(price);
                show.setFront(front);
                show.setMiddle(middle);
                show.setRear(rear);
                show.setDirector(d);
                show.setCasts(c);
                show.setFilm_synopsis(p);

                JOptionPane.showMessageDialog(this, "Ready to book show(" + showid + ") for film " + filmname, "Select in successfully", JOptionPane.PLAIN_MESSAGE);
                new BookWindow("Booking Page");
                this.dispose();
            }else{
                return;
            }

        }
        if(e.getSource() == btn_sign_out || e.getSource() == btn_sign_out2){
            new LoginWindow("Login Please");
            this.dispose();
        }
        if(e.getSource() == btn_modify){
            if (!inputInfoValid(account, pwd1, pwd2)) {
                //If the input is invalid, return directly
                return;
            }

            UserSQL usersql = new UserSQL();
            boolean success = usersql.updateUser(account,pwd1);

            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Modify successfully!", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Modify failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }


    private boolean inputInfoValid(String account, String pwd1, String pwd2){
        if (pwd1.equals("") || pwd2.equals("") ) {
            JOptionPane.showMessageDialog(this, "Missing input", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!pwd1.equals(pwd2)) {
            JOptionPane.showMessageDialog(this, "Passwords inconsistent", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void searchfilm(String name,String filmtype, String screensize, String location, String time){
        ShowSQL showsql = new  ShowSQL();
        Object[][] result = showsql.usershowselection(name,filmtype,screensize,location,time);
        setFilmtable(result);
    }

    private void searchhis(String useid,String name, String time){
        BookSQL booksql = new BookSQL();
        Object[][] result = booksql.userhisselection(useid,name,time);
        setHistable(result);
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

    public static Object[][] getHistable() {
        return histable;
    }

    public static void setHistable(Object[][] histable) {
        UserWindow.histable = histable;
    }

    public void cancelbooking(){
        int row = Jt_Book.getSelectedRow();
        String bookid = t_Booktable.getValueAt(row,0).toString();
        String showid = t_Booktable.getValueAt(row,1).toString();
        int front = Integer.parseInt(t_Booktable.getValueAt(row,9).toString());
        int mid = Integer.parseInt(t_Booktable.getValueAt(row,10).toString());
        int rear = Integer.parseInt(t_Booktable.getValueAt(row,11).toString());
        BookSQL booksql = new BookSQL();
        ShowSQL showsql = new ShowSQL();
        booksql.drophis(bookid);
        showsql.seatupdate(showid,front,mid,rear);
        SimpleDateFormat s = new SimpleDateFormat();
        s.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String d = s.format(date);
        try {
            FileWriter writer = new FileWriter("cancel_info.txt", true);
            writer.write("Order Canceled\n" +
                    "User: " + LoginWindow.getUser().getUsername() + "\n" +
                    "Reason: Canceled by themselves\n" +
                    "Date: " + d + "\n\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
