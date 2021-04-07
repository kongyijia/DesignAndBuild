package view;

import javax.swing.*;
import java.awt.*;

public class IndexPanel extends JPanel {
    private JPanel loginPanel;
    private MainPanel mainPanel;

    public IndexPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        initialize();
    }

    private void initialize(){
        this.setLayout(null);
        this.setBounds(0,0, MainFrame.WIDTH, MainFrame.HEIGHT);

        loginPanel_init();
    }
    protected void paintComponent(Graphics g) {
        Image icon = new ImageIcon("assets\\pictures\\indexBackGround.jpg").getImage();
        g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void loginPanel_init(){
        loginPanel = new LoginPanel(this.mainPanel);
        loginPanel.setBounds(670,80,400,300);
        loginPanel.setBackground(new Color(255, 255, 255,170));
        loginPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        this.add(loginPanel);
    }
}

