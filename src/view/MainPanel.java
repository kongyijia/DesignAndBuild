package view;

import model.Client;
import javax.swing.*;

public class MainPanel extends JPanel {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 560;

    private JPanel indexPanel;
    private JPanel enrollPanel;
    private FunctionPanel functionPanel;

    public MainPanel(){
        initialize();
    }

    private void initialize(){
        this.setLayout(null);
        this.setBounds(0,0, WIDTH, HEIGHT);
        changeToIndex();
    }

    public void changeToFunction(Client client){
        if(indexPanel != null) { indexPanel.setVisible(false); }
        if(enrollPanel != null){ enrollPanel.setVisible(false); }

        if(functionPanel != null) {
            this.remove(functionPanel);
        }
        this.functionPanel = new FunctionPanel(this, client);
        this.add(functionPanel);
    }

    public void changeToIndex(){
        if(enrollPanel != null){ enrollPanel.setVisible(false); }
        if (functionPanel != null) { functionPanel.setVisible(false); }
        if (indexPanel != null){
            this.remove(indexPanel);
        }
        indexPanel = new IndexPanel(this);
        this.add(indexPanel);
    }

    public void changeToEnroll(){
        if(indexPanel != null){ indexPanel.setVisible(false); }
        if(functionPanel != null){ functionPanel.setVisible(false); }
        if (enrollPanel != null) { this.remove(enrollPanel); }
        enrollPanel = new Enroll(this);
        this.add(enrollPanel);
    }
}
