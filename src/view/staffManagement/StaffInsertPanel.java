package view.staffManagement;

import util.config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * This class is used in {@link StaffManagePanel} generate new coaches and new administrators panel
 *
 * @author Shengbo Wang
 * @version 1.0
 * @since 16 May 2021
 */

public class StaffInsertPanel extends JPanel {
    private HashMap<String, JComponent> insertMap = new HashMap<>();

    private JButton backButton;
    private JButton confirmButton;

    public String nickName, password, password2, phone, email;
    public JTextField nickNameInput, phoneInput, emailInput;
    public JPasswordField passwordInput, password2Input;
    public JLabel w_nickName, w_password, w_password2, w_sex, w_phone, w_email;
    public JComboBox<String> sexInput;
    public int flag = 1, state = 0, id = 0, sex = 0;

    public JFrame f_message;
    public JButton b_login;
    public JPanel p_message;
    public JLabel r_message;

    public StaffInsertPanel(){
        initialize();
    }

    /**
     * This method is used to initialize {@link StaffInsertPanel}
     */
    private void initialize(){
        this.setLayout(null);
        this.setSize(new Dimension(config.PAGE_WIDTH, config.PAGE_HEIGHT - 50));

        JLabel nickNameLabel = new JLabel("NickName: ", JLabel.CENTER);
        nickNameInput = new JTextField();
        this.insertMap.put("nickName", nickNameInput);
        nickNameLabel.setBounds(100,50,100,25);
        nickNameInput.setBounds(350,50,100,25);
        this.add(nickNameLabel);
        this.add(nickNameInput);

        JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        passwordInput = new JPasswordField();
        this.insertMap.put("password", passwordInput);
        passwordLabel.setBounds(100,100,100,25);
        passwordInput.setBounds(350,100,100,25);
        this.add(passwordLabel);
        this.add(passwordInput);

        JLabel password2Label = new JLabel("Input your password again to confirm: ", JLabel.CENTER);
        password2Input = new JPasswordField();
        this.insertMap.put("password2", passwordInput);
        password2Label.setBounds(50,150,250,25);
        password2Input.setBounds(350,150,100,25);
        this.add(password2Label);
        this.add(password2Input);

        JLabel sexLabel = new JLabel("Sex: ", JLabel.CENTER);
        sexInput = new JComboBox<>(new String[] {"male", "female"});
        this.insertMap.put("sex", sexInput);
        sexLabel.setBounds(100,200,100,25);
        sexInput.setBounds(350,200,100,25);
        this.add(sexLabel);
        this.add(sexInput);

        JLabel phoneLabel = new JLabel("Phone Number: ", JLabel.CENTER);
        phoneInput = new JTextField();
        this.insertMap.put("phoneNumber", sexInput);
        phoneLabel.setBounds(100,250,100,25);
        phoneInput.setBounds(350,250,100,25);
        this.add(phoneLabel);
        this.add(phoneInput);

        JLabel emailLabel = new JLabel("E-mail: ", JLabel.CENTER);
        emailInput = new JTextField();
        this.insertMap.put("email", emailInput);
        emailLabel.setBounds(100,300,100,25);
        emailInput.setBounds(350,300,100,25);
        this.add(emailLabel);
        this.add(emailInput);

        backButton = new JButton("Back");
        confirmButton = new JButton("Confirm");
        backButton.setBounds(100,350,100,25);
        confirmButton.setBounds(350,350,100,25);
        this.add(backButton);
        this.add(confirmButton);

        f_message = new JFrame();
        b_login = new JButton("Back to Panel");
        p_message = new JPanel();
        r_message = new JLabel("Enrollment success.");

        f_message.setTitle("Enroll");
        f_message.setSize(300,300);
        f_message.setContentPane(p_message);
        f_message.setLocationRelativeTo(null);
        p_message.setLayout(null);
        r_message.setBounds(80,50,140,20);
        b_login.setBounds(50,150,200,20);

        p_message.add(r_message);
        p_message.add(b_login);
        f_message.setVisible(false);
    }

    /**
     *
     * @param actionListener add listener to components
     */
    public void addListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
        this.confirmButton.addActionListener(actionListener);
        b_login.addActionListener(actionListener);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}
