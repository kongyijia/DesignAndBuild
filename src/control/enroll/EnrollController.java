package control.enroll;

import control.Controller;

import javax.swing.*;

import control.MainFrame;
import model.Administrator;
import model.Coach;
import model.User;
import model.mapping.ClientMapping;
import util.config;
import view.Enroll;

import java.awt.*;
import java.io.IOException;


public class EnrollController extends Controller {
    private Enroll enroll;

    public EnrollController() {
        super(config.ENROLL_PANEL_NAME, new Enroll());
        this.enroll = (Enroll) this.panel;
        enroll.addListener(e -> {
            if(e.getSource() == enroll.i_cancel){
                MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
            }
            else if(e.getSource() == enroll.i_ok){
                enroll.nickName = enroll.i_nickName.getText();
                enroll.password = String.valueOf(enroll.i_password.getPassword());
                enroll.password2 = String.valueOf(enroll.i_password2.getPassword());
                enroll.phone = enroll.i_phone.getText();
                enroll.email = enroll.i_email.getText();

                enroll.flag = 1;
                handle_select();
                handle_nickName(enroll.nickName, enroll.state);
                handle_password(enroll.password);
                handle_password2(enroll.password, enroll.password2);
                handle_sex();
                handle_phone(enroll.phone);
                handle_email(enroll.email);
                if(enroll.i_male.isSelected()) enroll.sex = 1; else enroll.sex = 0;
                if(enroll.flag == 1){
                    write();
                    if(enroll.flag == 1) {
                        message();
                    }
                }
            }
            else if(e.getSource() == enroll.b_back){
                enroll.f_message.setVisible(false);
            }
            else if(e.getSource() == enroll.b_login){
                enroll.f_message.setVisible(false);
                MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
            }
        });
    }

    private void handle_select(){
        if(enroll.i_admin.isSelected() || enroll.i_trainer.isSelected() || enroll.i_user.isSelected()) {
            if(enroll.w_select != null){enroll.p_enroll.remove(enroll.w_select);}
        }
        else{
            enroll.flag = 0;
            if(enroll.w_select != null){enroll.p_enroll.remove(enroll.w_select);}
            enroll.w_select = new JLabel("Please select a role!");
            enroll.w_select.setForeground(Color.RED);
            enroll.w_select.setBounds(150, 75, 300, 20);
            enroll.p_enroll.add(enroll.w_select);
            enroll.p_enroll.repaint();
        }
    }

    private void handle_nickName(String nickName, int state){
        if(nickName.length() != 0){
            if(enroll.w_nickName != null){enroll.p_enroll.remove(enroll.w_nickName);}
            if(state == 6){
                enroll.flag = 0;
                enroll.w_nickName =new JLabel("Duplicate nickname. Please input another one.");
                enroll.w_nickName.setForeground(Color.RED);
                enroll.w_nickName.setBounds(150,125,300,20);
                enroll.p_enroll.add(enroll.w_nickName);
                enroll.p_enroll.repaint();
            }
        }
        else{
            enroll.flag = 0;
            if(enroll.w_nickName != null){enroll.p_enroll.remove(enroll.w_nickName);}
            enroll.w_nickName =new JLabel("Please input your nickName!");
            enroll.w_nickName.setForeground(Color.RED);
            enroll.w_nickName.setBounds(150,125,300,20);
            enroll.p_enroll.add(enroll.w_nickName);
            enroll.p_enroll.repaint();
        }
    }

    private void handle_password(String password){
        if(password.length() != 0){
            if(enroll.w_password != null){enroll.p_enroll.remove(enroll.w_password);}
        }
        else{
            enroll.flag = 0;
            if(enroll.w_password != null){enroll.p_enroll.remove(enroll.w_password);}
            enroll.w_password = new JLabel("Please input password!");
            enroll.w_password.setForeground(Color.RED);
            enroll.w_password.setBounds(150,175,300,20);
            enroll.p_enroll.add(enroll.w_password);
            enroll.p_enroll.repaint();
        }
    }

    private void handle_password2(String password, String password2){
        if(password2.length() == 0){
            enroll.flag = 0;
            if(enroll.w_password2 != null){enroll.p_enroll.remove(enroll.w_password2);}
            enroll.w_password2 = new JLabel("Please input password again!");
            enroll.w_password2.setForeground(Color.RED);
            enroll.w_password2.setBounds(150,225,300,20);
            enroll.p_enroll.add(enroll.w_password2);
            enroll.p_enroll.repaint();
        }
        else{
            if(!password.equals(password2)){
                enroll.flag = 0;
                if(enroll.w_password2 != null){enroll.p_enroll.remove(enroll.w_password2);}
                enroll.w_password2 = new JLabel("Please input the same password!");
                enroll.w_password2.setForeground(Color.RED);
                enroll.w_password2.setBounds(150,225,300,20);
                enroll.p_enroll.add(enroll.w_password2);
                enroll.p_enroll.repaint();
            }
            else{
                if(enroll.w_password2 != null){enroll.p_enroll.remove(enroll.w_password2);}
            }
        }
    }

    private void handle_sex(){
        if(enroll.i_male.isSelected() || enroll.i_female.isSelected()){
            if(enroll.w_sex != null){enroll.p_enroll.remove(enroll.w_sex);}
        }
        else{
            enroll.flag = 0;
            if(enroll.w_sex != null){enroll.p_enroll.remove(enroll.w_sex);}
            enroll.w_sex = new JLabel("Please select your sex!");
            enroll.w_sex.setForeground(Color.RED);
            enroll.w_sex.setBounds(150,275,300,20);
            enroll.p_enroll.add(enroll.w_sex);
            enroll.p_enroll.repaint();
        }
    }

    private void handle_phone(String phone){
        if(phone.length() != 0){
            if(enroll.w_phone != null){enroll.p_enroll.remove(enroll.w_phone);}
        }
        else{
            enroll.flag = 0;
            if(enroll.w_phone != null){enroll.p_enroll.remove(enroll.w_phone);}
            enroll.w_phone = new JLabel("Please input your phone number!");
            enroll.w_phone.setForeground(Color.RED);
            enroll.w_phone.setBounds(150,325,300,20);
            enroll.p_enroll.add(enroll.w_phone);
            enroll.p_enroll.repaint();
        }
    }

    private void handle_email(String email){
        if(email.length() == 0){
            enroll.flag = 0;
            if(enroll.w_email != null){enroll.p_enroll.remove(enroll.w_email);}
            enroll.w_email = new JLabel("Please input your email!");
            enroll.w_email.setForeground(Color.RED);
            enroll.w_email.setBounds(150,375,300,20);
            enroll.p_enroll.add(enroll.w_email);
        }
        else{
            String regex = "^\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
            if(!email.matches(regex)){
                enroll.flag = 0;
                if(enroll.w_email != null){enroll.p_enroll.remove(enroll.w_email);}
                enroll.w_email = new JLabel("Please input your email in the correct form!");
                enroll.w_email.setForeground(Color.RED);
                enroll.w_email.setBounds(150,375,300,20);
                enroll.p_enroll.add(enroll.w_email);
            }
            else{
                if(enroll.w_email != null){enroll.p_enroll.remove(enroll.w_email);}
            }
        }
        enroll.p_enroll.repaint();
    }

    private void write(){
        enroll.id = (int)(Math.random()*10000);
        if(enroll.i_admin.isSelected()){
            Administrator admin = new Administrator(enroll.id, enroll.nickName, enroll.password, enroll.sex, enroll.phone, enroll.email, 0);
            try {
                enroll.state = ClientMapping.add(admin);
                if (enroll.state == 6) {
                    handle_nickName(enroll.nickName, enroll.state);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (enroll.state == 2) {
                enroll.id = (int)(Math.random()*10000);
                try {
                    enroll.state = ClientMapping.add(admin);
                    if (enroll.state == 6) {
                        handle_nickName(enroll.nickName, enroll.state);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        else if(enroll.i_trainer.isSelected()){
            Coach trainer = new Coach(enroll.id, enroll.nickName, enroll.password, enroll.sex, enroll.phone, enroll.email, 1);
            try {
                enroll.state = ClientMapping.add(trainer);
                if (enroll.state == 6) {
                    handle_nickName(enroll.nickName, enroll.state);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (enroll.state == 2) {
                enroll.id = (int)(Math.random()*10000);
                try {
                    enroll.state = ClientMapping.add(trainer);
                    if (enroll.state == 6) {
                        handle_nickName(enroll.nickName, enroll.state);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        else{
            User user = new User(enroll.id, enroll.nickName, enroll.password, enroll.sex, enroll.phone, enroll.email, 2);
            try {
                enroll.state = ClientMapping.add(user);
                if (enroll.state == 6) {
                    handle_nickName(enroll.nickName, enroll.state);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (enroll.state == 2) {
                enroll.id = (int)(Math.random()*10000);
                try {
                    enroll.state = ClientMapping.add(user);
                    if (enroll.state == 6) {
                        handle_nickName(enroll.nickName, enroll.state);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }


    private void message(){
        enroll.f_message.setVisible(true);
    }

    @Override
    public void update() {

    }
}
