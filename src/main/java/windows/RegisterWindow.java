package windows;

import sql.UserSQL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * @Project: FIlmTicketingSystem
 * @Date: 21/10/2021
 * @author: zhefu wu
 * @Description: TODO Register Window setting
 */

public class RegisterWindow extends JFrame implements ActionListener {
    private JTextField txt_account, txt_name; // ,txt_id
    private JPasswordField txt_pwd1, txt_pwd2;
//    private JComboBox<String> com_gender, com_idType;
//    private JRadioButton rbtn_clause;
//    private JLabel lbl_clause;
    private JButton btn_confirm, btn_cancel;

    private JPanel jp_show, jp_input, jp_bottom;

    public RegisterWindow(String s) {
        //Set interface header and symbol

        setTitle(s);
        ImageIcon icon = new ImageIcon("picture/logo1.jpg");
        setIconImage(icon.getImage());

        //Initialize the top display information module
        initTopShowInfo();

        //Initialize the middle user to fill in the information module
        initMidUserInfo();

        //Initialize the bottom reading agreement and interface function button module
        initBottomModule();

        //Add mod above
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_input, BorderLayout.CENTER);
        this.add(jp_bottom, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setSize(450, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Listener
    public void actionPerformed(ActionEvent e) {
        //Obtain info
        String account = txt_account.getText().trim();
        String pwd1 = new String(txt_pwd1.getPassword());
        String pwd2 = new String(txt_pwd2.getPassword());
        String name = txt_name.getText().trim();

        //Login button event
        if (e.getSource() == btn_confirm) {
            if (!inputInfoValid(account, pwd1, pwd2, name)) {
                //If the input is invalid, return directly
                return;
            }
            UserSQL usersql = new UserSQL();
            if (usersql.userExisted(account)) { //If this account already exists
                JOptionPane.showMessageDialog(this, "\n" +
                        "The account has been registered!", "Warning", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            //If the user does not exist, start registration
            boolean success = usersql.register(account, pwd1, name);
            if (success) { //If the registration is successful
                JOptionPane.showMessageDialog(this, "\n" +
                        "Registered successfully, please login!", "Warning", JOptionPane.PLAIN_MESSAGE);
                new MainWindow("Movie reservation system");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "registration failed!", "Warning", JOptionPane.PLAIN_MESSAGE);
            }
            return;
        }

        //Cancel button event, close the current window
        if (e.getSource() == btn_cancel) {
            new MainWindow("Movie reservation system");
            this.dispose();
        }
    }

    //Initialize the top information display module
    private void initTopShowInfo() {
        JLabel lbl_show = new JLabel("Please complete the information");
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 30));
        jp_show = new JPanel();
        jp_show.add(lbl_show);
    }

    //Initialize the middle user to fill in the information module
    private void initMidUserInfo() {
        //1.Account
        JLabel lbl_account = new JLabel("Account:");
        lbl_account.setFont(new Font("Verdana", Font.BOLD, 20));
        lbl_account.setHorizontalAlignment(JLabel.CENTER);

        //2.Pin
        JLabel lbl_setPassword = new JLabel("Please set your pin:");
        lbl_setPassword.setFont(new Font("Verdana", Font.BOLD, 20));
        lbl_setPassword.setHorizontalAlignment(JLabel.CENTER);

        //3.Confirm Pin
        JLabel lbl_confirmPassword = new JLabel("Please confirm your pin:");
        lbl_confirmPassword.setFont(new Font("Verdana", Font.BOLD, 20));
        lbl_confirmPassword.setHorizontalAlignment(JLabel.CENTER);

        //4.Name Pin
        JLabel lbl_name = new JLabel("Name");
        lbl_name.setFont(new Font("Verdana", Font.BOLD, 20));
        lbl_name.setHorizontalAlignment(JLabel.CENTER);

        //Initialize the input box for the information to be filled in
        txt_account = new JTextField(11);
        txt_pwd1 = new JPasswordField(15);
        txt_pwd2 = new JPasswordField(15);
        txt_name = new JTextField(10);

        //Initialize the middle section to fill in the information panel
        jp_input = new JPanel();
        jp_input.setLayout(new GridLayout(7, 2));
        jp_input.add(lbl_account);
        jp_input.add(txt_account);
        jp_input.add(lbl_setPassword);
        jp_input.add(txt_pwd1);
        jp_input.add(lbl_confirmPassword);
        jp_input.add(txt_pwd2);
        jp_input.add(lbl_name);
        jp_input.add(txt_name);
    }

    //Initialize interface function button
    private void initBottomModule(){
        JPanel jp_clause = new JPanel();

        //function button
        btn_confirm = new JButton("Confirm");
        btn_confirm.addActionListener(this);
        btn_cancel = new JButton("Cancel");
        btn_cancel.addActionListener(this);
        JPanel jp_clause_confirm = new JPanel();
        jp_clause_confirm.add(btn_confirm);
        jp_clause_confirm.add(btn_cancel);

        //bottom module template
        jp_bottom = new JPanel();
        jp_bottom.setLayout(new GridLayout(2, 1));
        jp_bottom.add(jp_clause);
        jp_bottom.add(jp_clause_confirm);
    }

    //Check the validity of the input information
    private boolean inputInfoValid(String Account, String pwd1, String pwd2, String name){
        if (Account.equals("") || pwd1.equals("") || pwd2.equals("") || name.equals("") ) {
            JOptionPane.showMessageDialog(this, "Missing input", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!pwd1.equals(pwd2)) {
            JOptionPane.showMessageDialog(this, "Passwords inconsistent", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

}
