package views;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;

    private JPanel loginPanel = new JPanel();
    private MainPanel mainPanel = new MainPanel();

    public MainFrame(){
        initialize();

        loginPanel.setBounds(0,0,WIDTH, HEIGHT);
        mainPanel.setBounds(0,0,WIDTH, HEIGHT);

        this.add(mainPanel);
        this.add(loginPanel);
    }
    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CourseWork");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.changeToLogin();
    }

    public void changeToMain(){
        loginPanel.setVisible(false);
        mainPanel.setVisible(true);
    }

    public void changeToLogin(){
        loginPanel.setVisible(false);
        mainPanel.setVisible(true);
    }
}
