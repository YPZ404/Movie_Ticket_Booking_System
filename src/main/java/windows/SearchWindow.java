package windows;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import sql.*;
import entity.*;

public class SearchWindow extends JFrame implements ActionListener{

    private JButton btn_endorse, btn_refund;
    private JTable orderSheetTable;
    private Object[] cols_orderSheet = {};
    private Object[][] rows_orderSheet;
    //    private ArrayList<OrderSheets> orderSheets;
    private String userId, buyTime;
    private int trainTypeNo;
    private String trainNo, startPlace, endPlace;

    public SearchWindow(String s,String startplace,String endplace,String departure,String tel,int traintypeno1) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
