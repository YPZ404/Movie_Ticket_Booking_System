package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class MGiftCardWindow extends JFrame implements ActionListener{
    private JButton btn_giftCard, btn_movieManagement, btn_staffManagement, btn_showManagement, btn_reports, btn_logout;
    private JTextField txt_giftCard;
    private JSONArray cardNumberList;

    public MGiftCardWindow(String title){
        setTitle(title);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());
        cardNumberList = new JSONArray();

        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
        Background.setBackgroundPicture(this, background);

        //Interface display information panel
        JLabel lbl_show = new JLabel("Gift Card Management");
        lbl_show.setForeground(Color.WHITE);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 40));
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        // Login interface function button module
        btn_giftCard = new JButton("Add gift card");
        btn_giftCard.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_giftCard.addActionListener(this);

        txt_giftCard = new JTextField(15);

        JPanel jp_giftcardFunctionBtn = new JPanel();
        jp_giftcardFunctionBtn.setOpaque(false);
        jp_giftcardFunctionBtn.add(new JLabel());
        jp_giftcardFunctionBtn.add(txt_giftCard);
        jp_giftcardFunctionBtn.add(btn_giftCard);

        btn_movieManagement = new JButton("Manage movies");
        btn_movieManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_movieManagement.addActionListener(this);

        btn_showManagement = new JButton("Manage shows");
        btn_showManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_showManagement.addActionListener(this);

        btn_staffManagement = new JButton("Manage staff");
        btn_staffManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_staffManagement.addActionListener(this);

        btn_reports = new JButton("View reports");
        btn_reports.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_reports.addActionListener(this);

        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_logout.addActionListener(this);

        JPanel jp_functionBtn = new JPanel();
        jp_functionBtn.setOpaque(false);
        jp_functionBtn.add(btn_movieManagement);
        jp_functionBtn.add(btn_showManagement);
        jp_functionBtn.add(btn_reports);
        jp_functionBtn.add(btn_staffManagement);

        //Set the main panel layout and add the above customized panel
        this.setLayout(new BorderLayout());
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_giftcardFunctionBtn, BorderLayout.CENTER);
        this.add(jp_functionBtn, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btn_movieManagement) {
            new MMovieManagementWindow("Movie management");
            this.dispose();
        }

        if (e.getSource() == btn_showManagement) {
            new MShowManagementWindow("Show management");
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

        if (e.getSource() == btn_giftCard) {
            try {
                String giftcardString = txt_giftCard.getText().trim();
                String giftcardNumString = "";
                if (giftcardString.length() >= 2) {
                    if (giftcardString.charAt(giftcardString.length() - 2) != 'G' || giftcardString.charAt(giftcardString.length() - 1) != 'C') {
                        throw new NumberFormatException();
                    }
                    giftcardNumString = giftcardString.substring(0, giftcardString.length() - 2);
                }
                double giftcardNum = Double.parseDouble(giftcardNumString);
                if (giftcardNumString.length() != 16) {
                    throw new NumberFormatException();
                }

                try {
                    JSONParser parser = new JSONParser();
                    Object read = parser.parse(new FileReader("gift_cards.json"));
                    JSONArray jsonRead = (JSONArray) read;
                    Boolean addPrev = false;
                    if (cardNumberList.size() == 0) {
                        addPrev = true;
                    }
                    for (Object o : jsonRead) {
                        JSONObject object = (JSONObject) o;
                        String number = (String) object.get("number");
                        if (number.equals(giftcardString)) {
                            Icon failed = new ImageIcon("picture/fail.jpg");
                            JOptionPane.showMessageDialog(this, "Gift card already exists", "Warning", JOptionPane.WARNING_MESSAGE, failed);
                            return;
                        }
                        if (addPrev == true) {
                            cardNumberList.add(o);
                        }
                    }
                }
                catch (Exception m) {}

                JSONObject cardNumber = new JSONObject();
                cardNumber.put("number", giftcardString);
                cardNumber.put("status", "redeemable");
                cardNumberList.add(cardNumber);

                try {
                    FileWriter file = new FileWriter("gift_cards.json");
                    file.write(cardNumberList.toJSONString());
                    file.flush();
                    file.close();
                } catch (IOException o) {
                    o.printStackTrace();
                }

                Icon success = new ImageIcon("picture/success.png");
                JOptionPane.showMessageDialog(this, "Successfully added giftcard" , "Success", JOptionPane.PLAIN_MESSAGE, success);

            } catch (NumberFormatException n) {
                Icon failed = new ImageIcon("picture/fail.jpg");
                JOptionPane.showMessageDialog(this, "Gift card format is incorrect", "Warning", JOptionPane.WARNING_MESSAGE, failed);
            }
        }
    }
}