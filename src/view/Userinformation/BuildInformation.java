package view.Userinformation;

import model.Client;
import model.Coach;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * <p>
 *     Class {@code BuildInformation} is a class extends JPanel.
 * </p>
 * <p>
 *     It is a parent component used to display the user's personal information.
 *
 * </p>
 */
public class BuildInformation extends JPanel
{
    /**
     *
     * Create {@code Client} information panel.
     * @param receiveUser Client
     * @see Client
     * @return void
     * @author Zhanao Zhang
     * @date 2021/5/31 21:18
     * @version V1.0
     */
    public BuildInformation(Client receiveUser)
    {

        this.setBounds(300, 20, 500, 330);
        this.setBackground(new Color(231, 236, 159)); // monkey ass

        if (receiveUser != null)
        {
            JLabel name = new JLabel(receiveUser.getNickName());
            int sex_tem = receiveUser.getSex();
            String sex_string = null;
            if (sex_tem == 1)
                sex_string = "male";
            if (sex_tem == 0)
                sex_string = "female";
            JLabel sex = new JLabel(sex_string);
            JLabel phone = new JLabel(receiveUser.getPhone());
            JLabel mail = new JLabel(receiveUser.getEmail());


            JLabel label_name;
            JLabel label_sex;
            JLabel label_Phone;
            JLabel label_mail;
            JLabel label_level;
            if (receiveUser.getRole() == 2) // user
            {
                User user = (User) receiveUser;
                GridLayout layout = new GridLayout(8, 2);
                label_name = new JLabel("Name: ");
                label_sex = new JLabel("Sex: ");
                label_Phone = new JLabel("Phone: ");
                label_mail = new JLabel("E-mail: ");
                JLabel label_account = new JLabel("Account: ");
                label_level = new JLabel("Level: ");
                JLabel label_Time = new JLabel("Study Time: ");
                JLabel label_vip = new JLabel("VIP Level: ");


                DecimalFormat accountTem = new DecimalFormat("######0.00");


                JLabel account = new JLabel("" + accountTem.format(user.getAccount()));
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
            if (receiveUser.getRole() == 1) //coach
            {
                Coach coach = (Coach) receiveUser;
                GridLayout layout = new GridLayout(5, 2);
                label_name = new JLabel("Name: ");
                label_sex = new JLabel("Sex: ");
                label_Phone = new JLabel("Phone: ");
                label_mail = new JLabel("E-mail: ");
                label_level = new JLabel("Level: ");
                String[] levelName = new String[]{"normal", "advanced", "outstanding"};
                JLabel level = new JLabel("" + levelName[coach.getLevel()]);
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