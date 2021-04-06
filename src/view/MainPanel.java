package view;

import javax.swing.*;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 560;

    private JPanel indexPanel;
    private FunctionPanel functionPanel;
    private int role = 0;

    public MainPanel(){
        initialize();
    }

    public void setRole(int role) {
        this.role = role;
    }

    private void initialize(){
        this.setLayout(null);
        this.setBounds(0,0, WIDTH, HEIGHT);
        changeToFunction();
    }

    public void changeToFunction(){
        if(functionPanel == null) {
            this.functionPanel = new FunctionPanel(this, this.role);
            this.add(functionPanel);
            functionPanel.setVisible(true);
        }
        if(indexPanel != null) {
            indexPanel.setVisible(false);
        }
    }

    public void changeToIndex(){
        if (functionPanel != null) {
            functionPanel.setVisible(false);
        }
        if (indexPanel == null){
            indexPanel = new IndexPanel(this);
            this.add(indexPanel);
            indexPanel.setVisible(true);
        }
    }
}
