package windows;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import entity.*;

/**
 * @Project:
 * @Date:
 * @author:
 * @Description:
 */

public class BookWindow extends JFrame implements ActionListener {

    public static int tickets = 0;
    private JButton btn_to_pay, btn_cancel, btn_sign_out;
    private JLabel lab_name1, lab_name2, lab_name3, lab_name4;
    private JComboBox<String> com_type1, com_type2, com_type3, com_type4, com_seat1, com_seat2, com_seat3, com_seat4;

    private JLabel lab_filmName,lab_filmTime,lab_location,lab_screen,lab_type,lab_d,lab_c,lab_s,lab_p,lab_f,lab_m,lab_r;
    private Show currentshow = UserWindow.getShow();
    private static Book currentbook = new Book();
    public static int s_a = 0, s_b = 0, s_c = 0, s_d = 0;
    public static int p_a = 0, p_b = 0, p_c = 0, p_d = 0;

    public BookWindow(String s){
        setTitle(s);
        String iconSrc = "picture/projector.jpg";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());


        // Left part
        JPanel jp_left = new JPanel();

        String filname = "Film name: " + currentshow.getFilmName();
        lab_filmName = new JLabel(filname);
        lab_filmName.setForeground(Color.black);
        lab_filmName.setFont(new Font("Verdana", Font.PLAIN, 15));

        String location = "Location: " + currentshow.getLocation();
        lab_location = new JLabel(location);
        lab_location.setForeground(Color.black);
        lab_location.setFont(new Font("Verdana", Font.PLAIN, 15));

        String time = "Showing time: " + currentshow.getTime();
        lab_filmTime = new JLabel(time);
        lab_filmTime.setForeground(Color.black);
        lab_filmTime.setFont(new Font("Verdana", Font.PLAIN, 15));

        String screen = "Screen size: " + currentshow.getScreensize();
        lab_screen = new JLabel(screen);
        lab_screen.setForeground(Color.black);
        lab_screen.setFont(new Font("Verdana", Font.PLAIN, 15));

        String type = "Film classification: " + currentshow.getType();
        lab_type = new JLabel(type);
        lab_type.setForeground(Color.black);
        lab_type.setFont(new Font("Verdana", Font.PLAIN, 15));

        String d = "Director: " + currentshow.getDirector();
        lab_d = new JLabel(d);
        lab_d.setForeground(Color.black);
        lab_d.setFont(new Font("Verdana", Font.PLAIN, 15));

        String c = "Casts: " + currentshow.getCasts();
        lab_c = new JLabel(c);
        lab_c.setForeground(Color.black);
        lab_c.setFont(new Font("Verdana", Font.PLAIN, 15));

        String p = "Price / ticket: " + currentshow.getPrice();
        lab_p = new JLabel(p);
        lab_p.setForeground(Color.black);
        lab_p.setFont(new Font("Verdana", Font.PLAIN, 15));


        JTextArea textArea = new JTextArea(2, 40);
        textArea.setText(currentshow.getFilm_synopsis());
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(UIManager.getColor("Label.background"));
        textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setBorder(UIManager.getBorder("Label.border"));


        JPanel jp_left_top = new JPanel();
        jp_left_top.setLayout(new GridLayout(3, 1));
        jp_left_top.setOpaque(false);
        jp_left_top.add(lab_filmName);
        jp_left_top.add(lab_location);
        jp_left_top.add(lab_filmTime);

        JPanel jp_left_mid = new JPanel();
        jp_left_mid.setLayout(new GridLayout(5, 1));
        jp_left_mid.add(lab_screen);
        jp_left_mid.add(lab_type);
        jp_left_mid.add(lab_d);
        jp_left_mid.add(lab_c);
        jp_left_mid.add(lab_p);

        JPanel jp_left_d = new JPanel();
        jp_left_d.setLayout(new GridLayout(2, 3));

        lab_s = new JLabel("Remaining seats: " );
        lab_s.setForeground(Color.red);
        lab_s.setFont(new Font("Verdana", Font.PLAIN, 15));

        lab_f = new JLabel("Front : " + currentshow.getFront());
        lab_f.setForeground(Color.red);
        lab_f.setFont(new Font("Verdana", Font.PLAIN, 15));

        lab_m = new JLabel("Middle : " + currentshow.getMiddle());
        lab_m.setForeground(Color.red);
        lab_m.setFont(new Font("Verdana", Font.PLAIN, 15));

        lab_r = new JLabel("Rare : " +  currentshow.getRear());
        lab_r.setForeground(Color.red);
        lab_r.setFont(new Font("Verdana", Font.PLAIN, 15));

        jp_left_d.add(lab_s);
        jp_left_d.add(new JLabel(""));
        jp_left_d.add(new JLabel(""));
        jp_left_d.add(lab_f);
        jp_left_d.add(lab_m);
        jp_left_d.add(lab_r);

        jp_left.setOpaque(false);
        jp_left.setLayout(new GridLayout(4, 1));
        jp_left.add(jp_left_top);
        jp_left.add(jp_left_mid);
        jp_left.add(jp_left_d);
        jp_left.add(textArea);

        // right part
        lab_name1 = new JLabel("ticket 1");
        lab_name1.setForeground(Color.blue);
        lab_name1.setBackground(Color.ORANGE);
        lab_name1.setFont(new Font("Verdana", Font.PLAIN, 20));
        com_seat1 = new JComboBox<>(new String[]{"None","front","middle","rear"});
        com_type1 = new JComboBox<>(new String[]{"None","child(under 12)", "student", "adult", "senior/pensioner"});

        lab_name2 = new JLabel("ticket 2");
        lab_name2.setForeground(Color.blue);
        lab_name2.setBackground(Color.ORANGE);
        lab_name2.setFont(new Font("Verdana", Font.PLAIN, 20));
        com_seat2 = new JComboBox<>(new String[]{"None","front","middle","rear"});
        com_type2 = new JComboBox<>(new String[]{"None","child(under 12)", "student", "adult", "senior/pensioner"});

        lab_name3 = new JLabel("ticket 3");
        lab_name3.setForeground(Color.blue);
        lab_name3.setBackground(Color.ORANGE);
        lab_name3.setFont(new Font("Verdana", Font.PLAIN, 20));
        com_seat3 = new JComboBox<>(new String[]{"None","front","middle","rear"});
        com_type3 = new JComboBox<>(new String[]{"None","child(under 12)", "student", "adult", "senior/pensioner"});

        lab_name4 = new JLabel("ticket 4");
        lab_name4.setForeground(Color.blue);
        lab_name4.setBackground(Color.ORANGE);
        lab_name4.setFont(new Font("Verdana", Font.PLAIN, 15));
        com_seat4 = new JComboBox<>(new String[]{"None","front","middle","rear"});
        com_type4 = new JComboBox<>(new String[]{"None","child(under 12)", "student", "adult", "senior/pensioner"});

        btn_cancel = new JButton("Cancel");
        btn_cancel.setOpaque(false);
        btn_cancel.addActionListener(this);
        btn_cancel.setBackground(Color.GRAY);
        btn_cancel.setForeground(Color.black);
        btn_cancel.setHorizontalAlignment(JButton.CENTER);
        btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 15));

        btn_to_pay = new JButton("To pay");
        btn_to_pay.setOpaque(false);
        btn_to_pay.addActionListener(this);
        btn_to_pay.setBackground(Color.GRAY);
        btn_to_pay.setForeground(Color.black);
        btn_to_pay.setHorizontalAlignment(JButton.CENTER);
        btn_to_pay.setFont(new Font("Verdana", Font.PLAIN, 15));


        btn_sign_out = new JButton("Sign out");
        btn_sign_out.setOpaque(false);
        btn_sign_out.addActionListener(this);
        btn_sign_out.setBackground(Color.GRAY);
        btn_sign_out.setForeground(Color.black);
        btn_sign_out.setHorizontalAlignment(JButton.CENTER);
        btn_sign_out.setFont(new Font("Verdana", Font.PLAIN, 15));


        JPanel jp_right_top = new JPanel();
        jp_right_top.setLayout(new GridLayout(4,4));
        jp_right_top.setPreferredSize(new Dimension(400, 400));
        jp_right_top.add(lab_name1);jp_right_top.add(com_seat1);jp_right_top.add(com_type1);jp_right_top.add(new JLabel());
        jp_right_top.add(lab_name2);jp_right_top.add(com_seat2);jp_right_top.add(com_type2);jp_right_top.add(new JLabel());
        jp_right_top.add(lab_name3);jp_right_top.add(com_seat3);jp_right_top.add(com_type3);jp_right_top.add(new JLabel());
        jp_right_top.add(lab_name4);jp_right_top.add(com_seat4);jp_right_top.add(com_type4);jp_right_top.add(new JLabel());

        JPanel jp_right_bot = new JPanel();
        jp_right_bot.add(btn_cancel);jp_right_bot.add(btn_sign_out);jp_right_bot.add(btn_to_pay);

        JPanel jp_right = new JPanel();
        jp_right.setOpaque(false);
        jp_right.setBorder(new TitledBorder("Tickets"));
        jp_right.setLayout(new BorderLayout());
        jp_right.add(jp_right_top, BorderLayout.CENTER);
        jp_right.add(jp_right_bot, BorderLayout.SOUTH);


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_to_pay) {
            try {
                if (validseats()&&validuser()){
                    setCurrentbook();
                    float price = currentbook.getPrice();
                    JOptionPane.showMessageDialog(this, "In total "+price , "Warning", JOptionPane.PLAIN_MESSAGE);
                    new PaymentWindow("Payment Page");
                    this.dispose();
                    return;
                }
                return;
            }catch (Exception exception){
                JOptionPane.showMessageDialog(this, "Can not be empty!" , "Warning", JOptionPane.PLAIN_MESSAGE);
                return;
            }

        }
        if (e.getSource()== btn_sign_out){
            new LoginWindow("Login Please");
            this.dispose();
        }
        if (e.getSource()==btn_cancel){
            new UserWindow("User Page");
            this.dispose();
        }

    }

    public Boolean validuser(){
        ArrayList<Integer> userBooks = new ArrayList<Integer>();
        userBooks.add(com_type1.getSelectedIndex());
        userBooks.add(com_type2.getSelectedIndex());
        userBooks.add(com_type3.getSelectedIndex());
        userBooks.add(com_type4.getSelectedIndex());

        switch (currentshow.getType()) {
            case "PG-13":
                // Child without parents can't
                if (userBooks.contains(1) && !userBooks.contains(3) && !userBooks.contains(4)) {
                    JOptionPane.showMessageDialog(this, "Children cannot watch PG-13 movies unaccompanied.", "Warning", JOptionPane.PLAIN_MESSAGE);
                    return false;
                }

                break;
            case "M":
            case "MA-15":
                if (userBooks.contains(1)) {
                    JOptionPane.showMessageDialog(this, "Children cannot watch M and M-15 movies.", "Warning", JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                break;
            case "R":
                if (userBooks.contains(1)) {
                    JOptionPane.showMessageDialog(this, "Children cannot watch R movies.", "Warning", JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                if (userBooks.contains(2) && !userBooks.contains(3) && !userBooks.contains(4)) {
                    JOptionPane.showMessageDialog(this, "Student cannot watch PG-13 movies unaccompanied.", "Warning", JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                break;
        }
        return true;


    }

    public Boolean validseats(){

        ArrayList<Integer> userseats = new ArrayList<Integer>();
        userseats.add(com_type1.getSelectedIndex());
        userseats.add(com_type2.getSelectedIndex());
        userseats.add(com_type3.getSelectedIndex());
        userseats.add(com_type4.getSelectedIndex());

        int f = currentshow.getFront();
        int m = currentshow.getMiddle();
        int r = currentshow.getRear();

        for (int a: userseats){
            if (a == 1){
                f--;
            }else if (a == 2){
                m--;
            }else if(a ==3){
                r--;
            }
        }

        if (f<0 || m<0 ||r<0){
            JOptionPane.showMessageDialog(this, "Not enough seats!" , "Warning", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        return true;
    }

    public void setCurrentbook(){
        ArrayList<Integer> userBooks = new ArrayList<Integer>();
        userBooks.add(com_type1.getSelectedIndex());
        userBooks.add(com_type2.getSelectedIndex());
        userBooks.add(com_type3.getSelectedIndex());
        userBooks.add(com_type4.getSelectedIndex());

        ArrayList<Integer> userseats = new ArrayList<Integer>();
        userseats.add(com_type1.getSelectedIndex());
        userseats.add(com_type2.getSelectedIndex());
        userseats.add(com_type3.getSelectedIndex());
        userseats.add(com_type4.getSelectedIndex());

        s_a = com_seat1.getSelectedIndex();
        s_b = com_seat2.getSelectedIndex();
        s_c = com_seat3.getSelectedIndex();
        s_d = com_seat4.getSelectedIndex();

        p_a = com_type1.getSelectedIndex();
        p_b = com_type2.getSelectedIndex();
        p_c = com_type3.getSelectedIndex();
        p_d = com_type4.getSelectedIndex();

        int multi = 0;

        for (int a: userseats){
            if (a != 0){multi += 1;}
        }

        tickets = multi;
        currentbook.setPrice(currentshow.getPrice() * multi);
        currentbook.setFilmname(currentshow.getFilmName());
        currentbook.setLocation(currentshow.getLocation());
        currentbook.setShowID(currentshow.getShowID());
        currentbook.setSeats(userseats);
        currentbook.setUser(userBooks);
    }

    public static Book getBook() {
        return currentbook;
    }

    public static int[] getSeats() {
        int front = 0;
        int mid = 0;
        int rare = 0;
        if (BookWindow.p_a != 0) {
            if (BookWindow.s_a == 1) {
                front += 1;
            }
            if (BookWindow.s_a == 2) {
                mid += 1;
            }
            if (BookWindow.s_a == 3) {
                rare += 1;
            }
        }
        if (BookWindow.p_b != 0) {
            if (BookWindow.s_b == 1) {
                front += 1;
            }
            if (BookWindow.s_b == 2) {
                mid += 1;
            }
            if (BookWindow.s_b == 3) {
                rare += 1;
            }
        }
        if (BookWindow.p_c != 0) {
            if (BookWindow.s_c == 1) {
                front += 1;
            }
            if (BookWindow.s_c == 2) {
                mid += 1;
            }
            if (BookWindow.s_c == 3) {
                rare += 1;
            }
        }
        if (BookWindow.p_d != 0) {
            if (BookWindow.s_d == 1) {
                front += 1;
            }
            if (BookWindow.s_d == 2) {
                mid += 1;
            }
            if (BookWindow.s_d == 3) {
                rare += 1;
            }
        }
        return new int[]{front, mid, rare};
    }
}
