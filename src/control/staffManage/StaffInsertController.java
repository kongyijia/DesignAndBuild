package control.staffManage;

import control.Controller;
import control.MainFrame;
import model.Administrator;
import model.Coach;
import model.mapping.ClientMapping;
import util.Util;
import util.config;
import view.staffManagement.StaffInsertPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StaffInsertController extends Controller {
    private StaffInsertPanel staffInsertPanel;
    private int role;

    public StaffInsertController() {
        super(config.STAFF_INSERT_NAME, new StaffInsertPanel());
        this.staffInsertPanel = (StaffInsertPanel) this.panel;

        this.setH_gap(300);

        staffInsertPanel.addListener(e -> {
            if (e.getSource() == staffInsertPanel.getBackButton())
                MainFrame.getInstance().goTo(config.STAFF_MANAGE_NAME);
            else if (e.getSource() == staffInsertPanel.getConfirmButton()) {
                staffInsertPanel.flag = 1;
                staffInsertPanel.state = 0;
                staffInsertPanel.nickName = staffInsertPanel.nickNameInput.getText();
                staffInsertPanel.password = String.valueOf(staffInsertPanel.passwordInput.getPassword());
                staffInsertPanel.password2 = String.valueOf(staffInsertPanel.password2Input.getPassword());
                staffInsertPanel.phone = staffInsertPanel.phoneInput.getText();
                staffInsertPanel.email = staffInsertPanel.emailInput.getText();
                handle_nickName(staffInsertPanel.nickName, staffInsertPanel.state);
                handle_password(staffInsertPanel.password);
                handle_password2(staffInsertPanel.password, staffInsertPanel.password2);
                if (staffInsertPanel.sexInput.getSelectedItem() == "male") staffInsertPanel.sex = 1;
                else staffInsertPanel.sex = 0;
                handle_phone(staffInsertPanel.phone);
                handle_email(staffInsertPanel.email);
                if (staffInsertPanel.flag == 1) {
                    write(getRole());
                    if (staffInsertPanel.flag == 1) { write_suc(); }
                }
            } else if (e.getSource() == staffInsertPanel.b_back) {
                staffInsertPanel.f_message.setVisible(false);
                MainFrame.getInstance().goTo(config.STAFF_INSERT_NAME);
                update();
            }
            else {
                MainFrame.getInstance().goTo(config.STAFF_MANAGE_NAME);
            }
        });
    }

    private void handle_nickName(String nickName, int state) {
        if (nickName.length() != 0) {
            if (staffInsertPanel.w_nickName != null) { staffInsertPanel.remove(staffInsertPanel.w_nickName); }
            if(!Util.isNickNameLegal(nickName)){
                staffInsertPanel.flag = 0;
                staffInsertPanel.w_nickName =new JLabel("Nickname not in right mode. Please input another one.");
                staffInsertPanel.w_nickName.setForeground(Color.RED);
                staffInsertPanel.w_nickName.setBounds(100,75,300,20);
                staffInsertPanel.add(staffInsertPanel.w_nickName);
                staffInsertPanel.repaint();
            }
            if (state == 6) {
                staffInsertPanel.flag = 0;
                staffInsertPanel.w_nickName = new JLabel("Duplicate nickname. Please input another one.");
                staffInsertPanel.w_nickName.setForeground(Color.RED);
                staffInsertPanel.w_nickName.setBounds(100, 75, 300, 20);
                staffInsertPanel.add(staffInsertPanel.w_nickName);
                staffInsertPanel.repaint();
            }
        } else {
            staffInsertPanel.flag = 0;
            if (staffInsertPanel.w_nickName != null) { staffInsertPanel.remove(staffInsertPanel.w_nickName); }
            staffInsertPanel.w_nickName = new JLabel("Please input your nickName!");
            staffInsertPanel.w_nickName.setForeground(Color.RED);
            staffInsertPanel.w_nickName.setBounds(100, 75, 300, 20);
            staffInsertPanel.add(staffInsertPanel.w_nickName);
            staffInsertPanel.repaint();
        }
    }

    private void handle_password(String password) {
        if (password.length() != 0) {
            if (staffInsertPanel.w_password != null) { staffInsertPanel.remove(staffInsertPanel.w_password); }
        } else {
            staffInsertPanel.flag = 0;
            if (staffInsertPanel.w_password != null) { staffInsertPanel.remove(staffInsertPanel.w_password); }
            staffInsertPanel.w_password = new JLabel("Please input password!");
            staffInsertPanel.w_password.setForeground(Color.RED);
            staffInsertPanel.w_password.setBounds(100, 125, 300, 20);
            staffInsertPanel.add(staffInsertPanel.w_password);
            staffInsertPanel.repaint();
        }
    }

    private void handle_password2(String password, String password2) {
        if (password2.length() == 0) {
            staffInsertPanel.flag = 0;
            if (staffInsertPanel.w_password2 != null) { staffInsertPanel.remove(staffInsertPanel.w_password2); }
            staffInsertPanel.w_password2 = new JLabel("Please input password again!");
            staffInsertPanel.w_password2.setForeground(Color.RED);
            staffInsertPanel.w_password2.setBounds(100, 175, 300, 20);
            staffInsertPanel.add(staffInsertPanel.w_password2);
            staffInsertPanel.repaint();
        } else {
            if (!password.equals(password2)) {
                staffInsertPanel.flag = 0;
                if (staffInsertPanel.w_password2 != null) { staffInsertPanel.remove(staffInsertPanel.w_password2); }
                staffInsertPanel.w_password2 = new JLabel("Please input the same password!");
                staffInsertPanel.w_password2.setForeground(Color.RED);
                staffInsertPanel.w_password2.setBounds(100, 175, 300, 20);
                staffInsertPanel.add(staffInsertPanel.w_password2);
                staffInsertPanel.repaint();
            } else {
                if (staffInsertPanel.w_password2 != null) { staffInsertPanel.remove(staffInsertPanel.w_password2); }
            }
        }
    }

    private void handle_phone(String phone) {
        if (phone.length() != 0) {
            if (staffInsertPanel.w_phone != null) { staffInsertPanel.remove(staffInsertPanel.w_phone); }
            if(Util.isPhoneLegal(phone) == 0) {
                staffInsertPanel.flag = 0;
                if (staffInsertPanel.w_phone != null) { staffInsertPanel.remove(staffInsertPanel.w_phone); }
                staffInsertPanel.w_phone = new JLabel("Phone number must be 11 digits.");
                staffInsertPanel.w_phone.setForeground(Color.RED);
                staffInsertPanel.w_phone.setBounds(100, 275, 300, 20);
                staffInsertPanel.add(staffInsertPanel.w_phone);
                staffInsertPanel.repaint();
            }
            else if(Util.isPhoneLegal(phone) == -1){
                staffInsertPanel.flag = 0;
                if(staffInsertPanel.w_phone != null){staffInsertPanel.remove(staffInsertPanel.w_phone);}
                staffInsertPanel.w_phone = new JLabel("Phone number should only contain numbers.");
                staffInsertPanel.w_phone.setForeground(Color.RED);
                staffInsertPanel.w_phone.setBounds(100,275,300,20);
                staffInsertPanel.add(staffInsertPanel.w_phone);
                staffInsertPanel.repaint();
            }
        } else {
            staffInsertPanel.flag = 0;
            if (staffInsertPanel.w_phone != null) { staffInsertPanel.remove(staffInsertPanel.w_phone); }
            staffInsertPanel.w_phone = new JLabel("Please input your phone number!");
            staffInsertPanel.w_phone.setForeground(Color.RED);
            staffInsertPanel.w_phone.setBounds(100, 275, 300, 20);
            staffInsertPanel.add(staffInsertPanel.w_phone);
            staffInsertPanel.repaint();
        }
    }

    private void handle_email(String email) {
        if (email.length() == 0) {
            staffInsertPanel.flag = 0;
            if (staffInsertPanel.w_email != null) { staffInsertPanel.remove(staffInsertPanel.w_email); }
            staffInsertPanel.w_email = new JLabel("Please input your email!");
            staffInsertPanel.w_email.setForeground(Color.RED);
            staffInsertPanel.w_email.setBounds(100, 325, 300, 20);
            staffInsertPanel.add(staffInsertPanel.w_email);
        } else {
            if (!Util.isEmailLegal(email)) {
                staffInsertPanel.flag = 0;
                if (staffInsertPanel.w_email != null) { staffInsertPanel.remove(staffInsertPanel.w_email); }
                staffInsertPanel.w_email = new JLabel("Please input your email in the correct form!");
                staffInsertPanel.w_email.setForeground(Color.RED);
                staffInsertPanel.w_email.setBounds(100, 325, 300, 20);
                staffInsertPanel.add(staffInsertPanel.w_email);
            } else {
                if (staffInsertPanel.w_email != null) { staffInsertPanel.remove(staffInsertPanel.w_email); }
            }
        }
        staffInsertPanel.repaint();
    }

    private void write(int role) {
        staffInsertPanel.id = (int) (Math.random() * 10000);
        Administrator clt;
        Coach clt2;
        if (role == 0) {
            clt = new Administrator(staffInsertPanel.id, staffInsertPanel.nickName, staffInsertPanel.password,
                    staffInsertPanel.sex, staffInsertPanel.phone, staffInsertPanel.email, role);
            try {
                staffInsertPanel.state = ClientMapping.add(clt);
                if (staffInsertPanel.state == 6) {
                    handle_nickName(staffInsertPanel.nickName, staffInsertPanel.state);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (staffInsertPanel.state == 2) {
                staffInsertPanel.id = (int) (Math.random() * 10000);
                try {
                    staffInsertPanel.state = ClientMapping.add(clt);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else {
            clt2 = new Coach(staffInsertPanel.id, staffInsertPanel.nickName, staffInsertPanel.password,
                    staffInsertPanel.sex, staffInsertPanel.phone, staffInsertPanel.email, role);
            try {
                staffInsertPanel.state = ClientMapping.add(clt2);
                if (staffInsertPanel.state == 6) {
                    handle_nickName(staffInsertPanel.nickName, staffInsertPanel.state);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            while (staffInsertPanel.state == 2) {
                staffInsertPanel.id = (int) (Math.random() * 10000);
                try {
                    staffInsertPanel.state = ClientMapping.add(clt2);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (staffInsertPanel.state == 6) {
            handle_nickName(staffInsertPanel.nickName, staffInsertPanel.state);
        }
    }

    @Override
    public void update() {
        staffInsertPanel.nickNameInput.setText("");
        staffInsertPanel.passwordInput.setText("");
        staffInsertPanel.password2Input.setText("");
        staffInsertPanel.phoneInput.setText("");
        staffInsertPanel.emailInput.setText("");
    }

    public void write_suc() {
        staffInsertPanel.f_message.setVisible(true);
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
