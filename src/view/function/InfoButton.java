package view.function;

import javax.swing.*;
import java.awt.*;

/**
 *  This is the button of top navigation bar.
 *  Each button correspond one page, when button clicked, view switch to correspond page.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public class InfoButton extends JButton {
    private String key;

    public InfoButton(String viewName, String key, int index){
        super(viewName);

        int INFO_BUTTON_X = 100;
        int INFO_BUTTON_Y = 0;
        int INFO_BUTTON_WIDTH = 150;
        int INFO_BUTTON_HEIGHT = 50;

        this.setBounds(INFO_BUTTON_X + INFO_BUTTON_WIDTH * index, INFO_BUTTON_Y, INFO_BUTTON_WIDTH, INFO_BUTTON_HEIGHT);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setForeground(Color.white);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
