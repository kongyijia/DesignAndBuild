package view.function;

import javax.swing.*;

public class MenuButton extends JButton {
    private String key;
    public MenuButton(String viewName ,String key, int index) {
        super(viewName);
        this.key = key;
        this.setBounds(0, index * FunctionPanel.MENU_BUTTON_HEIGHT, FunctionPanel.MENU_WIDTH, FunctionPanel.MENU_BUTTON_HEIGHT);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public String getKey() {
        return key;
    }
}
