package windows;

import sql.SQLconn;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.swing.*;
import java.sql.*;

public class SReportsWindow extends JFrame implements ActionListener{
    private JButton btn_movies_report, btn_bookings_report, btn_movieManagement, btn_showManagement, btn_giftCard, btn_logout;

    public SReportsWindow(String title){
        setTitle(title);
        String iconSrc = "picture/login.png";
        ImageIcon icon = new ImageIcon(iconSrc);
        setIconImage(icon.getImage());

        String bgdSrc = "picture/login.png";
        ImageIcon background = new ImageIcon(bgdSrc);
        Background.setBackgroundPicture(this, background);

        JLabel lbl_show = new JLabel("Reports");
        lbl_show.setForeground(Color.WHITE);
        lbl_show.setFont(new Font("Verdana", Font.PLAIN, 40));
        JPanel jp_show = new JPanel();
        jp_show.setOpaque(false);
        jp_show.add(lbl_show);

        // Login interface function button module
        btn_movies_report = new JButton("View movies report");
        btn_movies_report.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_movies_report.addActionListener(this);

        btn_bookings_report = new JButton("View bookings report");
        btn_bookings_report.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_bookings_report.addActionListener(this);

        btn_movieManagement = new JButton("Manage movies");
        btn_movieManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_movieManagement.addActionListener(this);

        btn_showManagement = new JButton("Manage shows");
        btn_showManagement.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_showManagement.addActionListener(this);

        btn_giftCard = new JButton("Manage gift cards");
        btn_giftCard.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_giftCard.addActionListener(this);

        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Calibri", Font.PLAIN, 20));
        btn_logout.addActionListener(this);

        JPanel jp_reportFunctionBtn = new JPanel();
        jp_reportFunctionBtn.setOpaque(false);
        jp_reportFunctionBtn.add(btn_movies_report);
        jp_reportFunctionBtn.add(btn_bookings_report);

        JPanel jp_FunctionBtn = new JPanel();
        jp_FunctionBtn.setOpaque(false);
        jp_FunctionBtn.add(btn_movieManagement);
        jp_FunctionBtn.add(btn_showManagement);
        jp_FunctionBtn.add(btn_giftCard);
        jp_FunctionBtn.add(btn_logout);

        //Set the main panel layout and add the above customized panel
        this.setLayout(new BorderLayout());
        this.add(jp_show, BorderLayout.NORTH);
        this.add(jp_reportFunctionBtn, BorderLayout.CENTER);
        this.add(jp_FunctionBtn, BorderLayout.SOUTH);
        this.validate();
        this.setVisible(true);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_movies_report) {
            try {
                PrintWriter writer = new PrintWriter(("movies.txt"));
                writer.write("Shows:\n\n");
                java.sql.ResultSet resultSet = SQLconn.executeQuery("select * from show");
                ResultSetMetaData rsdata = resultSet.getMetaData();
                String text = "";
                while (resultSet.next()) {
                    for (int i = 1; i <= rsdata.getColumnCount(); i++) {
                        text += rsdata.getColumnName(i) + ": " + resultSet.getString(i);
                        text += "\n";
                    }
                    text += "\n";
                }
                writer.write(text);

                writer.write("\nFilms:\n\n");
                resultSet = SQLconn.executeQuery("select * from film");
                rsdata = resultSet.getMetaData();
                text = "";
                while (resultSet.next()) {
                    for (int i = 1; i <= rsdata.getColumnCount(); i++) {
                        text += rsdata.getColumnName(i) + ": " + resultSet.getString(i);
                        text += "\n";
                    }
                    text += "\n";
                }
                writer.write(text);
                writer.close();

                Icon success = new ImageIcon("picture/success.png");
                JOptionPane.showMessageDialog(this, "Successfully generated movies and shows report" , "Success", JOptionPane.PLAIN_MESSAGE, success);

            } catch (FileNotFoundException | SQLException c) {
                Icon failed = new ImageIcon("picture/fail.jpg");
                JOptionPane.showMessageDialog(this, "Error generating report", "Warning", JOptionPane.WARNING_MESSAGE, failed);
            }
        }

        if (e.getSource() == btn_bookings_report) {
            try {
                PrintWriter writer = new PrintWriter(("bookings.txt"));
                writer.write("Bookings:\n\n");
                java.sql.ResultSet resultSet = SQLconn.executeQuery("select * from booking");
                ResultSetMetaData rsdata = resultSet.getMetaData();
                String text = "";
                while (resultSet.next()) {
                    for (int i = 1; i <= rsdata.getColumnCount(); i++) {
                        text += rsdata.getColumnName(i) + ": " + resultSet.getString(i);
                        text += "\n";
                    }
                    text += "\n";
                }
                writer.write(text);
                writer.close();

                Icon success = new ImageIcon("picture/success.png");
                JOptionPane.showMessageDialog(this, "Successfully generated bookings report" , "Success", JOptionPane.PLAIN_MESSAGE, success);

            } catch(FileNotFoundException | SQLException c){
                Icon failed = new ImageIcon("picture/fail.jpg");
                JOptionPane.showMessageDialog(this, "Error generating report", "Warning", JOptionPane.WARNING_MESSAGE, failed);
            }
        }

        if (e.getSource() == btn_movieManagement) {
            new SMovieManagementWindow("Movie management");
            this.dispose();
        }

        if (e.getSource() == btn_showManagement) {
            new SShowManagementWindow("Show management");
            this.dispose();
        }

        if (e.getSource() == btn_giftCard) {
            new SGiftCardWindow("Giftcard management");
            this.dispose();
        }

        if (e.getSource() == btn_logout) {
            new MainWindow("Movie reservation system");
            this.dispose();
        }
    }
}