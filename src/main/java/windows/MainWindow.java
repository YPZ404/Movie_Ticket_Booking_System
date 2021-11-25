package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainWindow extends JFrame implements ActionListener {
    private JButton btn_login, btn_register, btn_cancel, btn_guest;
    // private static entity.User user;

    public MainWindow(String title) {
        //Set the login interface header and symbol
        setTitle(title);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());

        //Customize the background of the main panel of the main interface
        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
            Background.setBackgroundPicture(this, background);

        //Interface display information panel
        JLabel lbl_show = new JLabel("Movie reservation system");
            lbl_show.setForeground(Color.WHITE);
            lbl_show.setFont(new Font("Verdana", Font.PLAIN, 40));
            lbl_show.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_show = new JPanel();
            jp_show.setOpaque(false);
            jp_show.add(lbl_show);

        // User Information Panel (Typesetting)
        JPanel jp_userInfo = new JPanel();

        // Design the panel background to be transparent, because you want to display a custom background image
            jp_userInfo.setOpaque(false);
            jp_userInfo.setLayout(new GridLayout(7, 2));

        // Login interface function button module
        //1.Login button
        btn_login = new JButton("Login");
            btn_login.setFont(new Font("Verdana", Font.PLAIN, 20));
            btn_login.addActionListener(this);
        //2.Register Button
        btn_register = new JButton("Register");
            btn_register.setFont(new Font("Verdana", Font.PLAIN, 20));
            btn_register.addActionListener(this);
        //3.Cancel Button
        btn_cancel = new JButton("Cancel");
            btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 20));
            btn_cancel.addActionListener(this);
        //4.Continue as guest button
        btn_guest = new JButton("Continue as guest");
            btn_guest.setFont(new Font("Verdana", Font.PLAIN, 20));
            btn_guest.addActionListener(this);
        //5.Function button panel
        JPanel jp_functionBtn = new JPanel();
            jp_functionBtn.setOpaque(false);
            jp_functionBtn.add(btn_guest);
            jp_functionBtn.add(btn_login);
            jp_functionBtn.add(btn_register);
            jp_functionBtn.add(btn_cancel);
            //


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

    public void actionPerformed(ActionEvent e) {
        // Login
        if (e.getSource() == btn_login) {
            new LoginWindow("Login as user");
            this.dispose();
        }
        // Register
        if (e.getSource() == btn_register) {
            new RegisterWindow("Register account");
            this.dispose();
        }
        // Continue as guest
        if (e.getSource() == btn_guest) {
            new InfoWindow("Featured movies");
            this.dispose();
        }
        // Cancel
        if (e.getSource() == btn_cancel) {
            System.exit(0);
        }
    }
}