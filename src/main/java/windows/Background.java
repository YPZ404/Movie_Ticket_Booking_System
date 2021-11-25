package windows;


import javax.swing.*;
import java.awt.*;

/**
 * @Project: FIlmTicketingSystem
 * @Date: 21/10/2021
 * @author: zhefu wu
 * @Description: TODO Background setting
 */

public class Background {
    //background setting
    public static void setBackgroundPicture(JFrame jFrame, ImageIcon picture) {
        JLabel lbl_image = new JLabel(picture);
        jFrame.getLayeredPane().add(lbl_image, JLayeredPane.FRAME_CONTENT_LAYER);
        lbl_image.setSize(picture.getIconWidth(), picture.getIconHeight());
        Container cp = jFrame.getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel) cp).setOpaque(false);
        jFrame.setSize(picture.getIconWidth(), picture.getIconHeight());
    }
}