package windows;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GiftCardWindow extends JFrame implements ActionListener {

    public static String giftCardNum = "";
    private JTextField txt_card;
    private JButton btn_pay, btn_cancel;

    public GiftCardWindow(String s) {
        setTitle(s);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());
        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
        Background.setBackgroundPicture(this, background);

        //Interface display information panel
        JLabel lbl_show = new JLabel("Gift Card");
        lbl_show.setForeground(Color.WHITE);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 35));
        lbl_show.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        //User Information Module
        //1.Gift card number
        JLabel lbl_card = new JLabel("Gift card number:");
        lbl_card.setForeground(Color.WHITE);
        lbl_card.setFont(new Font("Verdana", Font.BOLD, 30));
        lbl_card.setHorizontalAlignment(SwingConstants.CENTER);
        //2.Information input box
        txt_card = new JTextField(15);
        //3.User Information Panel (Typesetting)
        JPanel jp_gc = new JPanel();

        //Design the panel background to be transparent, because you want to display a custom background image
        jp_gc.setOpaque(false);

        jp_gc.setLayout(new GridLayout(7, 2));
        jp_gc.add(new JLabel());
        jp_gc.add(new JLabel());
        jp_gc.add(lbl_card);
        jp_gc.add(txt_card);

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
        this.add(jp_gc, BorderLayout.CENTER);
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
            try {
                if (check_valid()) {
                    giftCardNum = txt_card.getText();
                    new ConfirmWindowG("Confirm the Payment");
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
                                "Reason: Cancelled by failed gift card payment\n" +
                                "Date: " + d + "\n\n");
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this, "Invalid Input" , "Failed", JOptionPane.PLAIN_MESSAGE);
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
        Object read = parser.parse(new FileReader("gift_cards.json"));
        JSONArray jsonRead = (JSONArray) read;
        boolean check_number = false;
        boolean check_status = false;
        for (Object o : jsonRead) {
            JSONObject object = (JSONObject) o;
            String number = (String) object.get("number");
            if (txt_card.getText().equals(number)) {
                check_number = true;
            }
        }
        if (check_number) {
            for (Object o : jsonRead) {
                JSONObject object = (JSONObject) o;
                String number = (String) object.get("number");
                if (txt_card.getText().equals(number)) {
                    if (object.get("status").equals("redeemable")) {
                        check_status = true;
                    }
                }
            }
        }
        return check_number && check_status;
    }
}
