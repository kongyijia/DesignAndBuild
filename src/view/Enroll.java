package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import model.*;
import model.mapping.*;

/**
 * @description Panel of enrolment for users.
 *
 * @author Shengbo Wang
 * @version 1.2
 * @see Enroll
 * @since 19 April 2021
 */

public class Enroll extends JPanel{
    public int flag;
    public int state = 0;
    public JPanel p_enroll;
    //personal information shared by all 3 roles
    public int id = 0;
    public String nickName;
    public String password, password2;
    public int sex;//0 female, 1 male
    public String phone;
    public String email;
    public int role;//0 admin, 1 trainer, 2 user
    public boolean cancel;

    public ButtonGroup s_sex;
    //components shared by all 3 roles
    //components for input
    public TextField i_nickName;
    public JPasswordField i_password, i_password2;
    public JRadioButton i_male, i_female;
    public TextField i_phone;
    public TextField i_email;
    public JButton i_cancel, i_ok;

    //components for reminder
    public JLabel r_nickName;
    public JLabel r_password;
    public JLabel r_password2;
    public JLabel r_sex;
    public JLabel r_phone;
    public JLabel r_email;

    //components for warning
    public JLabel w_nickName;
    public JLabel w_password;
    public JLabel w_password2;
    public JLabel w_sex;
    public JLabel w_phone;
    public JLabel w_email;

    public JFrame f_message;
    public JButton b_login;
    public JPanel p_message;
    public JLabel r_message;

    public Enroll() {
        this.setLayout(null);
        p_enroll = new JPanel();
        p_enroll.setLayout(null);
        this.setBounds(0,0,1200,560);
        this.add(p_enroll);

        newcomponent();

        //radio button (only select one)
        s_sex = new ButtonGroup();
        s_sex.add(i_male);
        s_sex.add(i_female);

        setbound();
        addtopanel();
    }

    public void newcomponent(){
        //reminders
        r_nickName = new JLabel("nickName");
        r_password = new JLabel("Password");
        r_password2 = new JLabel("Input your password again to confirm");
        r_sex = new JLabel("Sex");
        r_phone = new JLabel("Phone number");
        r_email = new JLabel("Email");

        //input fields
        i_nickName = new TextField();
        i_password = new JPasswordField();
        i_password2 = new JPasswordField();
        i_male = new JRadioButton("male");
        i_female = new JRadioButton("female");
        i_phone = new TextField();
        i_email = new TextField();
        i_cancel = new JButton("cancel");
        i_ok = new JButton("ok");

        f_message = new JFrame();
        b_login = new JButton("Login");
        p_message = new JPanel();
        r_message = new JLabel("Enrollment success.");
    }

    public void setbound(){
        r_nickName.setBounds(150, 100, 300, 25);
        r_password.setBounds(150, 150, 300, 25);
        r_password2.setBounds(150, 200, 300, 25);
        r_sex.setBounds(150, 250, 300, 25);
        r_phone.setBounds(150, 300, 300, 25);
        r_email.setBounds(150, 350, 300, 25);

        i_nickName.setBounds(700, 100, 100, 25);
        i_password.setBounds(700, 150, 100, 25);
        i_password2.setBounds(700, 200, 100, 25);
        i_male.setBounds(700, 250, 100, 25);
        i_female.setBounds(800, 250, 100, 25);
        i_phone.setBounds(700, 300, 100, 25);
        i_email.setBounds(700, 350, 150, 25);
        i_cancel.setBounds(350, 400, 100, 25);
        i_ok.setBounds(650, 400, 100, 25);

        p_enroll.setBounds(0,0,1200,560);

        f_message.setTitle("Enroll");
        f_message.setSize(300,300);
        f_message.setContentPane(p_message);
        f_message.setLocationRelativeTo(null);
        p_message.setLayout(null);
        r_message.setBounds(80,50,140,20);
        b_login.setBounds(100,150,100,20);

        p_message.add(r_message);
        p_message.add(b_login);
        f_message.setVisible(false);
    }

    public void addtopanel(){
        p_enroll.add(i_nickName);
        p_enroll.add(i_password);
        p_enroll.add(i_password2);
        p_enroll.add(i_male);
        p_enroll.add(i_female);
        p_enroll.add(i_phone);
        p_enroll.add(i_email);
        p_enroll.add(i_cancel);
        p_enroll.add(i_ok);
        p_enroll.add(r_nickName);
        p_enroll.add(r_password);
        p_enroll.add(r_password2);
        p_enroll.add(r_sex);
        p_enroll.add(r_phone);
        p_enroll.add(r_email);
    }

    public void addListener(ActionListener actionListener){
        i_cancel.addActionListener(actionListener);
        i_ok.addActionListener(actionListener);
        b_login.addActionListener(actionListener);
    }
}
