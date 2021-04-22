package view.Userinformation;

import control.MainFrame;
import model.Client;
import model.Coach;
import model.Course;
import model.User;

import javax.swing.*;
import java.awt.*;

public class BuildInformation extends JPanel
{
    public BuildInformation(Client receiveuser)
    {

        this.setBounds(300, 20, 500, 330);
        this.setBackground(Color.PINK);

        if (receiveuser != null)
        {
            JLabel name = new JLabel(receiveuser.getNickName());
            int sex_tem = receiveuser.getSex();
            String sex_string = null;
            if (sex_tem == 1)
                sex_string = "male";
            if (sex_tem == 0)
                sex_string = "female";
            JLabel sex = new JLabel(sex_string);
            JLabel phone = new JLabel(receiveuser.getPhone());
            JLabel mail = new JLabel(receiveuser.getEmail());


            JLabel label_name;
            JLabel label_sex;
            JLabel label_Phone;
            JLabel label_mail;
            JLabel label_level;
            if (receiveuser.getRole() == 2) // user
            {
                User user = (User) receiveuser;
                GridLayout layout = new GridLayout(8, 2);
                label_name = new JLabel("Name: ");
                label_sex = new JLabel("Sex: ");
                label_Phone = new JLabel("Phone: ");
                label_mail = new JLabel("E-mail: ");
                JLabel label_account = new JLabel("Account: ");
                label_level = new JLabel("Level: ");
                JLabel label_Time = new JLabel("Study Time: ");
                JLabel label_vip = new JLabel("VIP Level: ");

                JLabel account = new JLabel("" + user.getAccount());
                JLabel time = new JLabel("" + user.getLearningTime());
                JLabel vip = new JLabel("" + user.getVip());
                JLabel level = new JLabel("" + user.getLevel());


                this.setLayout(layout);// Depends on size

                this.add(label_name);
                this.add(name);
                this.add(label_sex);
                this.add(sex);
                this.add(label_Phone);
                this.add(phone);
                this.add(label_mail);
                this.add(mail);
                this.add(label_account);
                this.add(account);
                this.add(label_level);
                this.add(level);
                this.add(label_Time);
                this.add(time);
                this.add(label_vip);
                this.add(vip);
            }
            if (receiveuser.getRole() == 1) //coach
            {
                Coach coach = (Coach) receiveuser;
                GridLayout layout = new GridLayout(5, 2);
                label_name = new JLabel("Name: ");
                label_sex = new JLabel("Sex: ");
                label_Phone = new JLabel("Phone: ");
                label_mail = new JLabel("E-mail: ");
                label_level = new JLabel("Level: ");
                JLabel level = new JLabel("" + coach.getLevel());
                this.setLayout(layout);// Depends on size

                this.add(label_name);
                this.add(name);
                this.add(label_sex);
                this.add(sex);
                this.add(label_Phone);
                this.add(phone);
                this.add(label_mail);
                this.add(mail);
                this.add(label_level);
                this.add(level);
            }
        }




        this.setVisible(true);


    }
}