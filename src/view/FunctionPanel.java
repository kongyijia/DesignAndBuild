package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import model.*;

public class FunctionPanel extends JLayeredPane{
    public static final int MENU_WIDTH = 150;
    public static final int MENU_HEIGHT = 200;
    public static final int INFO_HEIGHT = 50;
    public static final int INFO_WIDTH = MainPanel.WIDTH;
    public static final int MENU_BUTTON_HEIGHT = 30;
    public static int MENU_BUTTON_COUNT = 0;
    public static int INFO_BUTTON_COUNT = 0;

    private CardLayout cardLayout = new CardLayout();
    private JPanel menuPanel;
    private JPanel showPanel;
    private JPanel infoPanel;
    private HashMap<String, JPanel> pages = new HashMap<>();

    private MainPanel mainPanel;
    private Client client;

    public FunctionPanel(MainPanel mainPanel, Client client) {
        this.mainPanel = mainPanel;
        this.client = client;

        initialize(client);
    }

    private void initialize(Client client){
        this.setLayout(null);
        this.setBounds(0,0, MainPanel.WIDTH, MainPanel.HEIGHT);

        showPanel_init();
        infoPanel_init(client.getNickName(), "assets/pictures/test.jpg");
        menuPanel_init();
        pages_init(client.getRole());
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
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(showPanel);
        this.setLayer(showPanel, -1);
    }

    private void infoPanel_init(String nickname, String avatarURL){
        infoPanel = new JPanel();

        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.setBounds(0, 0, INFO_WIDTH, INFO_HEIGHT);

        JButton avatarButton = new JButton();
        avatarButton.setBounds(1100, 0, 50, 50);
        ImageIcon avatarIcon = new ImageIcon(avatarURL);
        avatarIcon.setImage(avatarIcon.getImage().getScaledInstance(avatarButton.getWidth(),avatarButton.getHeight(),Image.SCALE_DEFAULT));
        avatarButton.setIcon(avatarIcon);
        avatarButton.addActionListener(e -> menuPanel.setVisible(true));

        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(50, 0, 50, 50);
        ImageIcon logoIcon = new ImageIcon("assets/pictures/logo.jpg");
        logoIcon.setImage(logoIcon.getImage().getScaledInstance(logoLabel.getWidth(),logoLabel.getHeight(),Image.SCALE_DEFAULT));
        logoLabel.setIcon(logoIcon);
        infoPanel.add(logoLabel);

        JLabel welcomeLabel = new JLabel("Welcome " + nickname + " !");
        welcomeLabel.setBounds(900, 10, 200, 40);

        infoPanel.add(welcomeLabel);
        infoPanel.add(avatarButton);

        this.add(infoPanel);
        this.setLayer(infoPanel, 2);
    }

    private void menuPanel_init() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(1000, INFO_HEIGHT, MENU_WIDTH, MENU_HEIGHT);
        menuPanel.setBackground(Color.white);

        this.add(menuPanel);
        this.setLayer(menuPanel, 3);

        JButton exitButton = new JButton("Sign out");
        exitButton.setBounds(0, MENU_HEIGHT - MENU_BUTTON_HEIGHT, MENU_WIDTH, MENU_BUTTON_HEIGHT);
        exitButton.addActionListener(e -> mainPanel.changeToIndex());
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        menuPanel.add(exitButton);
        menuPanel.setVisible(false);
    }
    private void pages_init(int role){
        if(role == 0){
            addPage(new JPanel(),"Your Profile","userProfile", 1);
            addPage(new JPanel(),"Your Course","userCourse",1);
            addPage(new JPanel(),"Video Square","userVideoSquare", 1);
        }
        else if(role == 1){
            addPage(new JPanel(),"Your Profile","coachProfile",1);
            addPage(new JPanel(),"Your Course","coachCourse",1);
            addPage(new JPanel(),"Video Management","coachVideoManagement",1);
        }
        else if(role == 2){
            addPage(new JPanel(),"Video Square","adminVideoSquare",1);
            addPage(new JPanel(),"Video Management","adminVideoManagement",1);
            addPage(new JPanel(),"Staff Management","adminStaffManagement",1);
        }
    }

    private void addPage(JPanel panel, String viewName, String key, int buttonType){
        showPanel.add(panel, key);
        pages.put(key, panel);
        if(buttonType == 1){
            menuPanel.add(new MenuButton(viewName, key, MENU_BUTTON_COUNT));
            MENU_BUTTON_COUNT++;
        }
        else{
            infoPanel.add(new InfoButton(viewName, key, INFO_BUTTON_COUNT));
            INFO_BUTTON_COUNT++;
        }
    }
    public class MenuButton extends JButton{
        public MenuButton(String viewName ,String key, int index){
            super(viewName);
            this.setBounds(0, index * MENU_BUTTON_HEIGHT, MENU_WIDTH, MENU_BUTTON_HEIGHT);
            this.addActionListener(e -> {cardLayout.show(showPanel, key); menuPanel.setVisible(false);});
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }

    public class InfoButton extends JButton{
        public InfoButton(String viewName, String key, int index){
            super(viewName);

            int INFO_BUTTON_X = 100;
            int INFO_BUTTON_Y = 10;
            int INFO_BUTTON_WIDTH = 100;
            int INFO_BUTTON_HEIGHT = 30;

            this.setBounds(INFO_BUTTON_X + INFO_BUTTON_WIDTH * index, INFO_BUTTON_Y, INFO_BUTTON_WIDTH, INFO_BUTTON_HEIGHT);
            this.addActionListener(e -> cardLayout.show(showPanel, key));
        }
    }
}




