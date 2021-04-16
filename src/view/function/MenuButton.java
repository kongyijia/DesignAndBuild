package view.function;

import javax.swing.*;
import java.awt.*;

/**
 *  This is the button of side navigation bar.
 *  Each button correspond one page, when button clicked, view switch to correspond page.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public class MenuButton extends JButton {
    private String key;
    public MenuButton(String viewName ,String key, int index) {
        super(viewName);
        this.key = key;
        this.setBounds(0, index * FunctionPanel.MENU_BUTTON_HEIGHT, FunctionPanel.MENU_WIDTH, FunctionPanel.MENU_BUTTON_HEIGHT);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.white);
    }

    public String getKey() {
        return key;
    }
}
