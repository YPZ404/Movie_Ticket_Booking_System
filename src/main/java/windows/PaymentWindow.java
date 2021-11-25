package windows;

import entity.Book;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class PaymentWindow extends JFrame implements ActionListener {

    private JButton btn_pay, btn_cancel, btn_gift_card;
    private Book book = BookWindow.getBook();

    public PaymentWindow(String s) {
        //Set the interface header and symbol
        setTitle(s);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());

        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
        Background.setBackgroundPicture(this, background);

        // Interface display information panel
        JLabel lbl_show = new JLabel("Payment System");
        lbl_show.setForeground(Color.cyan);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 35));
        lbl_show.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        JLabel lbl_info = new JLabel("Total price: " + book.getPrice());
        lbl_info.setForeground(Color.LIGHT_GRAY);
        lbl_info.setFont(new Font("Verdana", Font.PLAIN, 35));
        lbl_info.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_info = new JPanel();
        jp_info.setOpaque(false);
        jp_info.add(lbl_info);

        // Function button module
        //1.Pay Button
        btn_pay = new JButton("Use Credit Card to Pay");
        btn_pay.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_pay.addActionListener(this);
        //2.Cancel Button
        btn_cancel = new JButton("Cancel");
        btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_cancel.addActionListener(this);
        //3.Gift card Button
        btn_gift_card = new JButton("Use Gift Card to Pay");
        btn_gift_card.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_gift_card.addActionListener(this);
        //4.Function button panel
        JPanel jp_functionBtn = new JPanel();
        jp_functionBtn.setOpaque(false);
        jp_functionBtn.add(btn_pay);
        jp_functionBtn.add(btn_gift_card);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Go to transaction page
        if (e.getSource() == btn_pay) {
            try {
                new TransactionWindow("Please input your payment method information");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }

        // Gift card page
        if (e.getSource() == btn_gift_card) {
            new GiftCardWindow("Gift card payment");
            this.dispose();
        }

        // Cancel
        if (e.getSource() == btn_cancel) {
            new BookWindow("Movie reservation system");
            this.dispose();
        }
    }
}
