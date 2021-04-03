package views;

import javax.swing.*;
import java.awt.*;

public class IndexPanel extends JPanel {
    private JPanel loginPanel;
    private JPanel enrollPanel;
    private MainPanel mainPanel;

    public IndexPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        initialize();
    }

    private void initialize(){
        this.setLayout(null);
        this.setBounds(0,0, MainFrame.WIDTH, MainFrame.HEIGHT);

        loginPanel_init();
        changeToIndex();
    }
    protected void paintComponent(Graphics g) {
        Image icon = new ImageIcon("static\\pictures\\carousel-3.jpg").getImage();
        g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void loginPanel_init(){
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBounds(650,70,400,400);

        this.add(loginPanel);
    }

    public void changeToIndex(){
        this.setVisible(true);
        if(enrollPanel != null) {
            enrollPanel.setVisible(false);
        }
    }

    public void changeToEnroll() {
        this.setVisible(false);
        if (enrollPanel == null) {
            enrollPanel = new JPanel();
            enrollPanel.setVisible(true);
        }
    }
}

