package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import sql.PaymentSQL;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class TransactionWindow extends JFrame implements ActionListener {

    private JPasswordField txt_card;
    private JTextField txt_name;
    private JButton btn_pay, btn_cancel;
    private static User user = LoginWindow.getUser();

    public TransactionWindow(String s) throws SQLException {
        setTitle(s);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());
        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
        Background.setBackgroundPicture(this, background);

        //Interface display information panel
        JLabel lbl_show = new JLabel("Pay");
        lbl_show.setForeground(Color.WHITE);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 35));
        lbl_show.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        //User Information Module
        //1.Credit/debit card
        JLabel lbl_card = new JLabel("Credit card:");
        lbl_card.setForeground(Color.WHITE);
        lbl_card.setFont(new Font("Verdana", Font.BOLD, 30));
        lbl_card.setHorizontalAlignment(SwingConstants.CENTER);
        //2.Name
        JLabel lbl_name = new JLabel("Name on card:");
        lbl_name.setForeground(Color.WHITE);
        lbl_name.setFont(new Font("Verdana", Font.BOLD, 30));
        lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
        //4.Information input box
        String card = new PaymentSQL().getCard(user.getAccount());
        String name = new PaymentSQL().getName(user.getAccount());
        txt_card = new JPasswordField(card);
        txt_name = new JTextField(name);
        //5.User Information Panel (Typesetting)
        JPanel jp_userInfo = new JPanel();

        //Design the panel background to be transparent, because you want to display a custom background image
        jp_userInfo.setOpaque(false);

        jp_userInfo.setLayout(new GridLayout(7, 2));
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(lbl_name);
        jp_userInfo.add(txt_name);
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(lbl_card);
        jp_userInfo.add(txt_card);

        // Login interface function button module
        //1.Login button
        btn_pay = new JButton("Confirm payment");
        btn_pay.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_pay.addActionListener(this);
        //2.Cancel Button
        btn_cancel = new JButton("Cancel");
        btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_cancel.addActionListener(this);
        //3.Function button panel
        JPanel jp_functionBtn = new JPanel();
        jp_functionBtn.setOpaque(false);
        jp_functionBtn.add(btn_pay);
        jp_functionBtn.add(btn_cancel);

        //Set the main panel layout and add the above customized panel
        this.setLayout(new BorderLayout());
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_userInfo, BorderLayout.CENTER);
        this.add(jp_functionBtn, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_pay) {
            //TODO
            try {
                if (check_valid()) {
                    new PaymentSQL().setCard(user.getAccount(), txt_name.getText(), String.valueOf(txt_card.getPassword()));
                    new ConfirmWindowC("Confirm the Payment");
                    this.dispose();
                } else {
                    SimpleDateFormat s = new SimpleDateFormat();
                    s.applyPattern("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String d = s.format(date);
                    try {
                        FileWriter writer = new FileWriter("cancel_info.txt", true);
                        writer.write("Order Cancelled\n" +
                                "User: " + LoginWindow.getUser().getUsername() + "\n" +
                                "Reason: Cancelled by failed credit card payment\n" +
                                "Date: " + d + "\n\n");
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this, "Incorrect Input" , "Failed", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == btn_cancel) {
            SimpleDateFormat s = new SimpleDateFormat();
            s.applyPattern("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String d = s.format(date);
            try {
                FileWriter writer = new FileWriter("cancel_info.txt", true);
                writer.write("Order Cancelled\n" +
                        "User: " + LoginWindow.getUser().getUsername() + "\n" +
                        "Reason: Cancelled by themselves\n" +
                        "Date: " + d + "\n\n");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new PaymentWindow("Payment System");
            this.dispose();
        }
    }

    private boolean check_valid() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object read = parser.parse(new FileReader("credit_cards.json"));
        JSONArray jsonRead = (JSONArray) read;
        boolean check_name = false;
        boolean check_number = false;
        for (Object o : jsonRead) {
            JSONObject object = (JSONObject) o;
            String name = (String) object.get("name");
            String number = (String) object.get("number");
            if (txt_name.getText().equals(name)) {
                check_name = true;
            }
            if (new String(txt_card.getPassword()).equals(number)) {
                check_number = true;
            }
        }
        return check_name && check_number;
    }

}
