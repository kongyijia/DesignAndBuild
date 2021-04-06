package views;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;

    private MainPanel mainPanel;

    public MainFrame(){
        initialize();
    }

    private void initialize(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CourseWork");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);

        mainPanel = new MainPanel();
        this.add(mainPanel);

        this.setVisible(true);
    }
}
