package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import model.*;
import model.mapping.*;


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

    public JRadioButton i_admin, i_trainer, i_user;
    //components shared by all 3 roles
    //components for input
    public TextField i_nickName;
    public JPasswordField i_password, i_password2;
    public JRadioButton i_male, i_female;
    public TextField i_phone;
    public TextField i_email;
    public JButton i_cancel, i_ok;

    //components for reminder
    public JLabel r_select;
    public JLabel r_nickName;
    public JLabel r_password;
    public JLabel r_password2;
    public JLabel r_sex;
    public JLabel r_phone;
    public JLabel r_email;

    //components for warning
    public JLabel w_select;
    public JLabel w_nickName;
    public JLabel w_password;
    public JLabel w_password2;
    public JLabel w_sex;
    public JLabel w_phone;
    public JLabel w_email;

    public JFrame f_message = new JFrame();
    public JButton b_back = new JButton("Back");
    public JButton b_login = new JButton("Login");
    public JPanel p_message = new JPanel();
    public JLabel r_message = new JLabel("Enrollment success.");

    public Enroll() {
        this.setLayout(null);
        p_enroll = new JPanel();
        p_enroll.setLayout(null);
        this.setBounds(0,0,1200,560);
        this.add(p_enroll);

        newcomponent();

        //radio button (only select one)
        ButtonGroup select = new ButtonGroup();
        select.add(i_admin);
        select.add(i_trainer);
        select.add(i_user);
        ButtonGroup sex = new ButtonGroup();
        sex.add(i_male);
        sex.add(i_female);

        setbound();
        addtopanel();
    }

    public void newcomponent(){
        //reminders
        r_select = new JLabel("Select one role");
        r_nickName = new JLabel("nickName");
        r_password = new JLabel("Password");
        r_password2 = new JLabel("Input your password again to confirm");
        r_sex = new JLabel("Sex");
        r_phone = new JLabel("Phone number");
        r_email = new JLabel("Email");

        //input fields
        i_admin = new JRadioButton("Administrator");
        i_trainer = new JRadioButton("Trainer");
        i_user = new JRadioButton("User");
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
        b_back = new JButton("Back");
        b_login = new JButton("Login");
        p_message = new JPanel();
        r_message = new JLabel("Enrollment success.");
    }

    public void setbound(){
        r_select.setBounds(150,50,300,25);
        r_nickName.setBounds(150, 100, 300, 25);
        r_password.setBounds(150, 150, 300, 25);
        r_password2.setBounds(150, 200, 300, 25);
        r_sex.setBounds(150, 250, 300, 25);
        r_phone.setBounds(150, 300, 300, 25);
        r_email.setBounds(150, 350, 300, 25);

        i_admin.setBounds(700,50,100,25);
        i_trainer.setBounds(800,50,100,25);
        i_user.setBounds(900,50,100,25);
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
        p_message.setLayout(null);
        r_message.setBounds(80,50,140,20);
        b_back.setBounds(50,150,100,20);
        b_login.setBounds(150,150,100,20);

        p_message.add(r_message);
        p_message.add(b_back);
        p_message.add(b_login);
        f_message.setVisible(false);
    }

    public void addtopanel(){
        p_enroll.add(i_admin);
        p_enroll.add(i_trainer);
        p_enroll.add(i_user);
        p_enroll.add(i_nickName);
        p_enroll.add(i_password);
        p_enroll.add(i_password2);
        p_enroll.add(i_male);
        p_enroll.add(i_female);
        p_enroll.add(i_phone);
        p_enroll.add(i_email);
        p_enroll.add(i_cancel);
        p_enroll.add(i_ok);
        p_enroll.add(r_select);
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
        b_back.addActionListener(actionListener);
        b_login.addActionListener(actionListener);
    }


//    public void handle_select(){
//        if(i_admin.isSelected() || i_trainer.isSelected() || i_user.isSelected()) {
//            flag = 1;
//            if(w_select != null){p_enroll.remove(w_select);}
//        }
//        else{
//            flag = 0;
//            if(w_select != null){p_enroll.remove(w_select);}
//            w_select = new JLabel("Please select a role!");
//            w_select.setForeground(Color.RED);
//            w_select.setBounds(150, 75, 300, 20);
//            p_enroll.add(w_select);
//            p_enroll.repaint();
//        }
//    }
//
//    public void handle_nickName(String nickName, int state){
//        if(nickName.length() != 0){
//            flag = 1;
//            if(w_nickName != null){p_enroll.remove(w_nickName);}
//            if(state == 6){
//                flag = 0;
//                w_nickName =new JLabel("Duplicate nickname. Please input another one.");
//                w_nickName.setForeground(Color.RED);
//                w_nickName.setBounds(150,125,300,20);
//                p_enroll.add(w_nickName);
//                p_enroll.repaint();
//            }
//        }
//        else{
//            flag = 0;
//            if(w_nickName != null){p_enroll.remove(w_nickName);}
//            w_nickName =new JLabel("Please input your nickName!");
//            w_nickName.setForeground(Color.RED);
//            w_nickName.setBounds(150,125,300,20);
//            p_enroll.add(w_nickName);
//            p_enroll.repaint();
//        }
//    }
//
//    public void handle_password(String password){
//        if(password.length() != 0){
//            flag = 1;
//            if(w_password != null){p_enroll.remove(w_password);}
//        }
//        else{
//            flag = 0;
//            if(w_password != null){p_enroll.remove(w_password);}
//            w_password = new JLabel("Please input password!");
//            w_password.setForeground(Color.RED);
//            w_password.setBounds(150,175,300,20);
//            p_enroll.add(w_password);
//            p_enroll.repaint();
//        }
//    }
//
//    public void handle_password2(String password, String password2){
//        if(password2.length() == 0){
//            flag = 0;
//            if(w_password2 != null){p_enroll.remove(w_password2);}
//            w_password2 = new JLabel("Please input password again!");
//            w_password2.setForeground(Color.RED);
//            w_password2.setBounds(150,225,300,20);
//            p_enroll.add(w_password2);
//            p_enroll.repaint();
//        }
//        else{
//            if(!password.equals(password2)){
//                flag = 0;
//                if(w_password2 != null){p_enroll.remove(w_password2);}
//                w_password2 = new JLabel("Please input the same password!");
//                w_password2.setForeground(Color.RED);
//                w_password2.setBounds(150,225,300,20);
//                p_enroll.add(w_password2);
//                p_enroll.repaint();
//            }
//            else{
//                flag = 1;
//                if(w_password2 != null){p_enroll.remove(w_password2);}
//            }
//        }
//    }
//
//    public void handle_sex(){
//        if(i_male.isSelected() || i_female.isSelected()){
//            flag =1;
//            if(w_sex != null){p_enroll.remove(w_sex);}
//        }
//        else{
//            flag = 0;
//            if(w_sex != null){p_enroll.remove(w_sex);}
//            w_sex = new JLabel("Please select your sex!");
//            w_sex.setForeground(Color.RED);
//            w_sex.setBounds(150,275,300,20);
//            p_enroll.add(w_sex);
//            p_enroll.repaint();
//        }
//    }
//
//    public void handle_phone(String phone){
//        if(phone.length() != 0){
//            flag = 1;
//            if(w_phone != null){p_enroll.remove(w_phone);}
//        }
//        else{
//            flag = 0;
//            if(w_phone != null){p_enroll.remove(w_phone);}
//            w_phone = new JLabel("Please input your phone number!");
//            w_phone.setForeground(Color.RED);
//            w_phone.setBounds(150,325,300,20);
//            p_enroll.add(w_phone);
//            p_enroll.repaint();
//        }
//    }
//
//    public void handle_email(String email){
//        if(email.length() == 0){
//            flag = 0;
//            if(w_email != null){p_enroll.remove(w_email);}
//            w_email = new JLabel("Please input your email!");
//            w_email.setForeground(Color.RED);
//            w_email.setBounds(150,375,300,20);
//            p_enroll.add(w_email);
//        }
//        else{
//            String regex = "^\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
//            if(!email.matches(regex)){
//                flag = 0;
//                if(w_email != null){p_enroll.remove(w_email);}
//                w_email = new JLabel("Please input your email in the correct form!");
//                w_email.setForeground(Color.RED);
//                w_email.setBounds(150,375,300,20);
//                p_enroll.add(w_email);
//            }
//            else{
//                flag = 1;
//                if(w_email != null){p_enroll.remove(w_email);}
//            }
//        }
//        p_enroll.repaint();
//    }
//
//    public void write(){
//        if(i_admin.isSelected()){
//            Administrator admin = new Administrator(id, nickName, password, sex, phone, email, 0);
//            try {
//                state = ClientMapping.add(admin);
//                if(state == 6){
//                    handle_nickName(nickName, state);
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//        else if(i_trainer.isSelected()){
//            Coach trainer = new Coach(id, nickName, password, sex, phone, email, 1);
//            try {
//                state = ClientMapping.add(trainer);
//                if(state == 6){
//                    handle_nickName(nickName, state);
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//        else{
//            User user = new User(id, nickName, password, sex, phone, email, 2);
//            try {
//                state = ClientMapping.add(user);
//                if(state == 6){
//                    handle_nickName(nickName, state);
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//        id++;
//    }
}
