package view.staffManagement;

import util.config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class StaffInsertPanel extends JPanel {
    private HashMap<String, JComponent> insertMap = new HashMap<>();

    private JButton backButton;
    private JButton confirmButton;

    public StaffInsertPanel(){
        initialize();
    }

    private void initialize(){
        this.setLayout(new GridLayout(0,2,50,30));
        this.setSize(new Dimension(config.PAGE_WIDTH, config.PAGE_HEIGHT - 50));

        // TODO
        JLabel whiteJLabel1 = new JLabel();
        JLabel whiteJLabel2 = new JLabel();
        this.add(whiteJLabel1);
        this.add(whiteJLabel2);

        JLabel nickNameLabel = new JLabel("NickName: ", JLabel.CENTER);
        JTextField nickNameInput = new JTextField();
        this.insertMap.put("nickName", nickNameInput);
        this.add(nickNameLabel);
        this.add(nickNameInput);

        JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        JTextField passwordInput = new JTextField();
        this.insertMap.put("password", passwordInput);
        this.add(passwordLabel);
        this.add(passwordInput);

        JLabel password2Label = new JLabel("Input your password again to confirm: ", JLabel.CENTER);
        JTextField password2Input = new JTextField();
        this.insertMap.put("password2", passwordInput);
        this.add(password2Label);
        this.add(password2Input);

        JLabel sexLabel = new JLabel("Sex: ", JLabel.CENTER);
        JComboBox<String> sexInput = new JComboBox<>(new String[] {"male", "female"});
        this.insertMap.put("sex", sexInput);
        this.add(sexLabel);
        this.add(sexInput);

        JLabel phoneLabel = new JLabel("Phone Number: ", JLabel.CENTER);
        JTextField phoneInput = new JTextField();
        this.insertMap.put("phoneNumber", sexInput);
        this.add(phoneLabel);
        this.add(phoneInput);

        JLabel emailLabel = new JLabel("E-mail: ", JLabel.CENTER);
        JTextField emailInput = new JTextField();
        this.insertMap.put("email", emailInput);
        this.add(emailLabel);
        this.add(emailInput);

        backButton = new JButton("Back");
        confirmButton = new JButton("Confirm");
        this.add(backButton);
        this.add(confirmButton);

        JLabel whiteJLabel3 = new JLabel();
        JLabel whiteJLabel4 = new JLabel();
        this.add(whiteJLabel3);
        this.add(whiteJLabel4);
    }

    public void addListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
        this.confirmButton.addActionListener(actionListener);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public HashMap<String, JComponent> getInsertMap() {
        return insertMap;
    }
}
