package windows;

import sql.UserSQL;
import entity.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @Project: FIlmTicketingSystem
 * @Date: 21/10/2021
 * @author: zhefu wu
 * @Description: TODO Login widows page
 */

public class LoginWindow extends JFrame implements ActionListener {

    private JTextField txt_account;
    private JPasswordField txt_password;
    private JComboBox<String> com_role;
    private JButton btn_login, btn_cancel;
    private static entity.User user;

    public LoginWindow(String title) {

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
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 35));
        lbl_show.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        //User Information Module
        //1.Account
        JLabel lbl_account;
        lbl_account = new JLabel("Account:");
        lbl_account.setForeground(Color.WHITE);
        lbl_account.setFont(new Font("Verdana", Font.BOLD, 30));
        lbl_account.setHorizontalAlignment(SwingConstants.CENTER);
        //2.Pin
        JLabel lbl_password = new JLabel("Pin:");
        lbl_password.setForeground(Color.WHITE);
        lbl_password.setFont(new Font("Verdana", Font.BOLD, 30));
        lbl_password.setHorizontalAlignment(SwingConstants.CENTER);
        //3.User Id
        JLabel lbl_role = new JLabel("Type:");
        lbl_role.setForeground(Color.WHITE);
        lbl_role.setFont(new Font("Verdana", Font.BOLD, 30));
        lbl_role.setHorizontalAlignment(SwingConstants.CENTER);
        //4.Information input box
        txt_account = new JTextField(15);
        txt_password = new JPasswordField(20);
        com_role = new JComboBox<>(new String[]{"User", "Staff", "Manager"});
        //5.User Information Panel (Typesetting)
        JPanel jp_userInfo = new JPanel();

        //Design the panel background to be transparent, because you want to display a custom background image
        jp_userInfo.setOpaque(false);

        jp_userInfo.setLayout(new GridLayout(7, 2));
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(lbl_account);
        jp_userInfo.add(txt_account);
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(lbl_password);
        jp_userInfo.add(txt_password);
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(lbl_role);
        jp_userInfo.add(com_role);
        jp_userInfo.add(new JLabel());
        jp_userInfo.add(new JLabel());

        // Login interface function button module
        //1.Login button
        btn_login = new JButton("Login");
        btn_login.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_login.addActionListener(this);
        //2.Cancel Button
        btn_cancel = new JButton("Cancel");
        btn_cancel.setFont(new Font("Verdana", Font.PLAIN, 20));
        btn_cancel.addActionListener(this);
        //3.Function button panel
        JPanel jp_functionBtn = new JPanel();
        jp_functionBtn.setOpaque(false);
        jp_functionBtn.add(btn_login);
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

    public void actionPerformed(ActionEvent e) {
        //Login
        if (e.getSource() == btn_login) {
            String userTel = txt_account.getText().trim();
            String password = new String(txt_password.getPassword());
            int type = com_role.getSelectedIndex();
            System.out.println(userTel);
            login(userTel, password, type);
            return;
        }

        //Cancel login
        if (e.getSource() == btn_cancel) {
            new MainWindow("Movie reservation system");
            this.dispose();
        }
    }

    public static User getUser() {
        return user;
    }

    //This method is called when the user clicks the login button
    private void login(String Account, String password, int type) {
        Icon success = new ImageIcon("picture/success.png");
        Icon failed = new ImageIcon("picture/fail.jpg");

        //Null test
        if (Account.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Input can not be empty!", "Warning", JOptionPane.WARNING_MESSAGE, failed);
            return;
        }

        //If not Null
        UserSQL usersql = new UserSQL();
        boolean existed = usersql.userValidate(Account, password, type);
        if (!existed) {
            JOptionPane.showMessageDialog(this, "The input information is incorrect", "Warning", JOptionPane.WARNING_MESSAGE, failed);
            System.out.println(Account+ " " + password + " " + type);
            return;
        }

        //Determine types to different interface

        if (type == 0) {
            user = usersql.userQueryByAccount(Account);
            JOptionPane.showMessageDialog(this, "Welcome! " + Account , "User logged in successfully", JOptionPane.PLAIN_MESSAGE, success);
            new UserWindow("User");
            this.dispose();

        } else if (type == 1) {
            new SMovieManagementWindow("Staff");
            this.dispose();
        } else if (type == 2) {
            new MMovieManagementWindow("Manager");
            this.dispose();
        }
    }


}
