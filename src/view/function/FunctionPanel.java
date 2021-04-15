package view.function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import control.MainFrame;
import model.*;
import util.config;

public class FunctionPanel extends JLayeredPane implements config {
    public static final int MENU_WIDTH = 150;
    public static final int MENU_HEIGHT = 200;
    public static final int INFO_HEIGHT = PAGE_Y;
    public static final int MENU_BUTTON_HEIGHT = 30;

    private CardLayout cardLayout = new CardLayout();
    private JPanel menuPanel;
    private JPanel showPanel;
    private JPanel infoPanel;
    private HashMap<String, MenuButton> menuButtons = new HashMap<>();
    private HashMap<String, InfoButton> infoButtons = new HashMap<>();

    private JButton avatarButton;
    private JButton exitButton;
    private JLabel welcomeLabel;

    private Client client;

    public FunctionPanel() {
        this.client = MainFrame.getInstance().getClient();
        initialize(client);
    }

    private void initialize(Client client){
        this.setLayout(null);
        this.setBounds(0,0, PANEL_WIDTH, PANEL_HEIGHT);

        showPanel_init();
        infoPanel_init(client.getNickName(), client.getAvatarSrc());
        menuPanel_init();
        button_init(client.getRole());
    }

    private void showPanel_init(){
        showPanel = new JPanel();
        showPanel.setLayout(cardLayout);
        showPanel.setBounds(0, INFO_HEIGHT, PAGE_WIDTH, PAGE_HEIGHT);
        showPanel.setBackground(Color.white);

        this.add(showPanel);
        this.setLayer(showPanel, -1);
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public JPanel getShowPanel() {
        return showPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JButton getAvatarButton() {
        return avatarButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public HashMap<String, MenuButton> getMenuButtons() {
        return menuButtons;
    }

    public HashMap<String, InfoButton> getInfoButtons() {
        return infoButtons;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public void addMouseListener(MouseListener mouseListener){
        showPanel.addMouseListener(mouseListener);
    }

    public void addListener(ActionListener actionListener){
        avatarButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
        menuButtons.forEach((k, v) -> v.addActionListener(actionListener));
        infoButtons.forEach((k, v) -> v.addActionListener(actionListener));
    }

    private void infoPanel_init(String nickname, String avatarURL){
        infoPanel = new JPanel();

        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.setBounds(0, 0, PANEL_WIDTH, INFO_HEIGHT);

        avatarButton = new JButton();
        avatarButton.setBounds(config.PAGE_WIDTH - 100, 0, 50, 50);
        ImageIcon avatarIcon = new ImageIcon(avatarURL);
        avatarIcon.setImage(avatarIcon.getImage().getScaledInstance(avatarButton.getWidth(),avatarButton.getHeight(),Image.SCALE_DEFAULT));
        avatarButton.setIcon(avatarIcon);

        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(50, 0, 50, 50);
        ImageIcon logoIcon = new ImageIcon("assets/pictures/logo.jpg");
        logoIcon.setImage(logoIcon.getImage().getScaledInstance(logoLabel.getWidth(),logoLabel.getHeight(),Image.SCALE_DEFAULT));
        logoLabel.setIcon(logoIcon);
        infoPanel.add(logoLabel);

        welcomeLabel = new JLabel("Welcome " + nickname + " !");
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setBounds(900, 0, 200, 50);

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

        exitButton = new JButton("Sign out");
        exitButton.setBounds(0, MENU_HEIGHT - MENU_BUTTON_HEIGHT, MENU_WIDTH, MENU_BUTTON_HEIGHT);
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        menuPanel.add(exitButton);
        menuPanel.setVisible(false);
    }
    public void button_init(int role){
        if(role == 2){
            addButton("Your Profile",USERDESCRIPTION_PANEL_NAME, 1);
            addButton("Your Course","userCourse",1);
            addButton("Video Square","userVideoSquare", 2);
        }
        else if(role == 1){
            addButton("Your Profile",USERDESCRIPTION_PANEL_NAME,1);
            addButton("Your Course","coachCourse",1);
            addButton("Video Management","coachVideoManagement",1);
        }
        else if(role == 0){
            addButton("Video Square","adminVideoSquare",2);
            addButton("Video Management","adminVideoManagement",1);
            addButton("Staff Management",STAFF_MANAGE_NAME,2);
        }
    }

    private void addButton(String buttonName, String pageName, int buttonType){
        if(buttonType == 1){
            MenuButton menuButton = new MenuButton(buttonName, pageName, menuButtons.size());
            menuButtons.put(pageName, menuButton);
            menuPanel.add(menuButton);
        }
        else{
            InfoButton infoButton = new InfoButton(buttonName, pageName, infoButtons.size());
            infoButtons.put(pageName, infoButton);
            infoPanel.add(infoButton);
        }
    }
}




