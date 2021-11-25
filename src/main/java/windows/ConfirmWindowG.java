package windows;

import entity.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sql.BookSQL;
import sql.ShowSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ConfirmWindowG extends JFrame implements ActionListener {

    private JButton btn_confirm, btn_cancel;
    public String tran_id = "";

    public ConfirmWindowG(String s) {
        setTitle(s);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());

        //Customize the background of the main panel of the main interface
        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
        Background.setBackgroundPicture(this, background);

        //Interface display information panel
        JLabel lbl_show = new JLabel("Confirm your payment");
        lbl_show.setForeground(Color.WHITE);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 35));
        lbl_show.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        JLabel lbl_info = new JLabel("Automatically time out in 2 minutes.");
        lbl_info.setForeground(Color.YELLOW);
        lbl_info.setFont(new Font("Verdana", Font.PLAIN, 25));
        lbl_info.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_info = new JPanel();
        jp_info.setOpaque(false);
        jp_info.add(lbl_info);

        // Login interface function button module
        //1.Pay Button
        btn_confirm = new JButton("Confirm");
        btn_confirm.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_confirm.addActionListener(this);
        //2.Cancel Button
        btn_cancel = new JButton("Cancel");
        btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_cancel.addActionListener(this);

        //4.Function button panel
        JPanel jp_functionBtn = new JPanel();
        jp_functionBtn.setOpaque(false);
        jp_functionBtn.add(btn_confirm);
        jp_functionBtn.add(btn_cancel);
        //Set the main panel layout and add the above customized panel
        this.setLayout(new BorderLayout());
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_info, BorderLayout.CENTER);
        this.add(jp_functionBtn, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        java.util.Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat s = new SimpleDateFormat();
                s.applyPattern("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String d = s.format(date);
                try {
                    FileWriter writer = new FileWriter("cancel_info.txt", true);
                    writer.write("Order Cancelled\n" +
                            "User: " + LoginWindow.getUser().getUsername() + "\n" +
                            "Reason: Cancelled by timeout\n" +
                            "Date: " + d + "\n\n");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                new UserWindow("User");
                disposer();
            }
        };
        timer.schedule(task, 120000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_confirm) {
            StringBuilder transaction_id = new StringBuilder("");
            Random random = new Random();
            for (int i = 0; i < 14; i++) {
                int type = random.nextInt(3);
                switch (type) {
                    case 0:
                        transaction_id.append(String.valueOf((char) (random.nextInt(25) + 65)));
                        break;
                    case 1:
                        transaction_id.append(String.valueOf(random.nextInt(10)));
                        break;
                    case 2:
                        transaction_id.append(String.valueOf((char) (random.nextInt(25) + 97)));
                        break;
                    default:
                        break;
                }
            }
            transaction_id.append("TI");
            tran_id = transaction_id.toString();
            try {
                FileWriter writer = new FileWriter("transaction_id.txt", true);
                writer.write(transaction_id.toString() + "\n");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JSONParser parser = new JSONParser();
            Object read = null;
            try {
                read = parser.parse(new FileReader("gift_cards.json"));
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
            JSONArray jsonRead = (JSONArray) read;
            assert jsonRead != null;
            for (Object o : jsonRead) {
                JSONObject object = (JSONObject) o;
                if (object.get("number").equals(GiftCardWindow.giftCardNum)) {
                    BufferedWriter w = null;
                    try {
                        w = new BufferedWriter(new FileWriter("gift_cards.json"));
                        object.put("status", "redeemed");
                        w.write(jsonRead.toString());
                        w.flush();
                        w.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            Book b = BookWindow.getBook();
            int[] seats = BookWindow.getSeats();
            new BookSQL().insertIntoBook(LoginWindow.getUser().getUserid(), b.getShowID(), BookWindow.tickets, b.getPrice(), seats[0], seats[1], seats[2]);
            new ShowSQL().seatupdate(BookWindow.getBook().getShowID(), UserWindow.getShow().getFront() - seats[0], UserWindow.getShow().getMiddle() - seats[1], UserWindow.getShow().getRear() - seats[2]);
            JOptionPane.showMessageDialog(this, "Order confirmed! Your transaction id is "+ transaction_id+ ". Please keep it properly.", "Confirmed!", JOptionPane.PLAIN_MESSAGE);
            new UserWindow("User");
            this.dispose();
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
            new UserWindow("User");
            this.dispose();
        }
    }

    public void disposer() {
        this.dispose();
    }
}
