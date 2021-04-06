package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FunctionPanel extends JLayeredPane{
    public static final int MENU_WIDTH = 150;
    public static final int MENU_HEIGHT = 200;
    public static final int INFO_HEIGHT = 50;
    public static final int INFO_WIDTH = MainPanel.WIDTH;
    public static final int MENU_BUTTON_HEIGHT = 30;

    private JPanel menuPanel;
    private JPanel showPanel;
    private JPanel infoPanel;
    private CardLayout cardLayout = new CardLayout();
    private MainPanel mainPanel;

    public FunctionPanel(MainPanel mainPanel, int role) {
        this.mainPanel = mainPanel;
        initialize(role);
    }

    private void initialize(int role){
        this.setLayout(null);
        this.setBounds(0,0, MainPanel.WIDTH, MainPanel.HEIGHT);

        showPanel_init();
        infoPanel_init(role);
        menuPanel_init(role);
    }

    private void showPanel_init(){
        showPanel = new JPanel();
        showPanel.setLayout(cardLayout);
        showPanel.setBounds(0, INFO_HEIGHT, INFO_WIDTH, MainPanel.HEIGHT - INFO_HEIGHT);
        showPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuPanel.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                return;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                return;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                return;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                return;
            }
        });
        this.add(showPanel);
        this.setLayer(showPanel, -1);
    }

    private void infoPanel_init(int role){
        infoPanel = new JPanel();

        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.setBounds(0, 0, INFO_WIDTH, INFO_HEIGHT);

        JButton photoButton = new JButton();
        photoButton.setBounds(1100, 0, 50, 50);
        ImageIcon icon = new ImageIcon("static/pictures/test.jpg");
        icon.setImage(icon.getImage().getScaledInstance(photoButton.getWidth(),photoButton.getHeight(),Image.SCALE_DEFAULT));
        photoButton.setIcon(icon);
        photoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(true);
            }
        });

        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(50, 0, 50, 50);
        ImageIcon logoIcon = new ImageIcon("static/pictures/logo.jpg");
        logoIcon.setImage(logoIcon.getImage().getScaledInstance(logoLabel.getWidth(),logoLabel.getHeight(),Image.SCALE_DEFAULT));
        logoLabel.setIcon(logoIcon);
        infoPanel.add(logoLabel);

        String nickname = "JokerFeng";
        JLabel welcomeLabel = new JLabel("Welcome " + nickname + " !");
        welcomeLabel.setBounds(900, 10, 200, 40);

        infoPanel.add(welcomeLabel);
        infoPanel.add(photoButton);

        this.add(infoPanel);
        this.setLayer(infoPanel, 2);
    }

    private void menuPanel_init(int role) {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(1000, INFO_HEIGHT, MENU_WIDTH, MENU_HEIGHT);
        menuPanel.setBackground(Color.white);

        this.add(menuPanel);
        this.setLayer(menuPanel, 3);

        if(role == 0){
            menuPanel.add(new MenuButton(new JPanel(), "Your Profile","userProfile",0));
            menuPanel.add(new MenuButton(new JPanel(),"Your Course","userCourse", 1));
            menuPanel.add(new MenuButton(new JPanel(),"Video Square","userVideoSquare", 2));
        }
        else if(role == 1){
            menuPanel.add(new MenuButton(new JPanel(),"Your Profile","coachProfile", 0));
            menuPanel.add(new MenuButton(new JPanel(),"Your Course","coachCourse", 1));
            menuPanel.add(new MenuButton(new JPanel(),"Video Management","coachVideoManagement", 2));
        }
        else if(role == 2){
            menuPanel.add(new MenuButton(new JPanel(),"Video Square","adminVideoSquare", 0));
            menuPanel.add(new MenuButton(new JPanel(),"Video Management","adminVideoManagement", 1));
            menuPanel.add(new MenuButton(new JPanel(),"Staff Management","adminStaffManagement", 2));
        }
        JButton exitButton = new JButton("Sign out");
        exitButton.setBounds(0, MENU_HEIGHT - MENU_BUTTON_HEIGHT, MENU_WIDTH, MENU_BUTTON_HEIGHT);
        //exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.changeToIndex();
            }
        });
        menuPanel.add(exitButton);
        menuPanel.setVisible(false);
    }

    public class MenuButton extends JButton{
        public MenuButton(JPanel panel, String viewName ,String options, int index){
            super(viewName);
            this.setBounds(0, index * MENU_BUTTON_HEIGHT, MENU_WIDTH, MENU_BUTTON_HEIGHT);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(showPanel, options);
                }
            });
            showPanel.add(panel, options);
        }
    }

    public class InfoButton extends JButton{
        public InfoButton(JPanel panel, String viewName ,String options, int index){
            super(viewName);

            int INFO_BUTTON_X = 100;
            int INFO_BUTTON_Y = 10;
            int INFO_BUTTON_WIDTH = 100;
            int INFO_BUTTON_HEIGHT = 30;

            this.setBounds(INFO_BUTTON_X + INFO_BUTTON_WIDTH * index, INFO_BUTTON_Y, INFO_BUTTON_WIDTH, INFO_BUTTON_HEIGHT);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(showPanel, options);
                }
            });
            showPanel.add(panel, options);
        }
    }
}




