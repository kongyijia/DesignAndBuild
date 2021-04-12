package view.Userinformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buildProfilepanel extends JPanel
{
    private static JButton profilebutton = new JButton("Profile Picture");
    private static JButton changepassword = new JButton("Change Password");
    private static JButton changeinformation = new JButton("modify personal information");
    private static JButton topup = new JButton("Top Up");

    public static JButton getProfilebutton()
    {
        return profilebutton;
    }

    public static JButton getChangeinformation()
    {
        return changeinformation;
    }

    public static JButton getChangepassword()
    {
        return changepassword;
    }

    public static JButton getTopup()
    {
        return topup;
    }

    public buildProfilepanel()
    {
        this.setSize(200,510);
        this.setLocation(200,0);
//        this.setBackground(Color.red);
        this.setLayout(null);

        changeinformation.setFont(new Font("Times", Font.ITALIC, 10));
        profilebutton.setFont(new Font("Times", Font.ITALIC, 15));
        changepassword.setFont(new Font("Times", Font.ITALIC, 15));
        topup.setFont(new Font("Times", Font.ITALIC, 15));

        profilebutton.setBounds(30,30,140,140);
        changepassword.setBounds(30,220,140,40);
        changeinformation.setBounds(30,280,140,40);

        topup.setBounds(30,340,140,40);


        this.add(changeinformation);
        this.add(profilebutton);
        this.add(changepassword);
        this.add(topup);
        this.setVisible(true);
    }
}
