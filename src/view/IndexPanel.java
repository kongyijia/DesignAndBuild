package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import util.config;

public class IndexPanel extends JPanel implements config {
    private JPanel loginPanel;
    private JButton loginButton = new JButton("login");
    private JButton registerButton = new JButton("enroll");
    private JLabel userLabel = new JLabel("User:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JLabel noAccount = new JLabel("");
    private JLabel passwordError = new JLabel("");
    private JTextField userText = new JTextField(20);
    private JPasswordField passwordText = new JPasswordField(20);

    public IndexPanel() {
        initialize();
    }

    private void initialize() {
        this.setLayout(null);
        this.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        loginPanel_init();
    }

    // draw background
    protected void paintComponent(Graphics g) {
        Image icon = new ImageIcon("assets\\pictures\\indexBackGround.jpg").getImage();
        g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void loginPanel_init() {
        loginPanel = new JPanel();
        loginPanel.setBounds(670, 80, 400, 300);
        loginPanel.setBackground(new Color(255, 255, 255));
        loginPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        loginPanel.setLayout(null);
        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(noAccount);
        userLabel.setFont(new Font("宋体", 0, 25));
        userLabel.setBounds(20, 85, 150, 30);
        userText.setBounds(170, 85, 200, 25);
        noAccount.setBounds(30, 130, 500, 30);

        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(passwordError);
        passwordLabel.setFont(new Font("宋体", 0, 25));
        passwordLabel.setBounds(20, 170, 200, 30);
        passwordText.setBounds(170, 170, 200, 25);
        passwordError.setBounds(30, 200, 500, 30);

        loginButton.setPreferredSize(new Dimension(120, 25));
        loginPanel.add(loginButton);
        loginButton.setBounds(70, 240, 100, 25);

        registerButton.setPreferredSize(new Dimension(120, 25));
        loginPanel.add(registerButton);
        registerButton.setBounds(220, 240, 100, 25);

        this.add(loginPanel);
    }

    public void addListener(ActionListener listener) {
        loginButton.addActionListener(listener);
        registerButton.addActionListener(listener);
    }

    public JTextField getUserText() {
        return userText;
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JLabel getNoAccount() {
        return noAccount;
    }

    public JLabel getPasswordError() {
        return passwordError;
    }

}

