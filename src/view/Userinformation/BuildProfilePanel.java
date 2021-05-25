package view.Userinformation;

import javax.swing.*;
import java.awt.*;

public class BuildProfilePanel extends JPanel
{
    private static JButton profileButton = new JButton("Profile Picture");
    private static JButton changePassword = new JButton("Change Password");
    private static JButton personalInformation = new JButton("modify personal information");
    private static JButton topup = new JButton("Top Up");

    public static JButton getProfilebutton()
    {
        return profileButton;
    }

    public static JButton getPersonalInformation()
    {
        return personalInformation;
    }

    public static JButton getChangePassword()
    {
        return changePassword;
    }

    public static JButton getTopup()
    {
        return topup;
    }

    public BuildProfilePanel()
    {
        this.setSize(200,510);
        this.setLocation(0,0);
        this.setBackground(new Color(125, 101, 53));
        this.setLayout(null);

        personalInformation.setFont(new Font("Times", Font.ITALIC, 10));
        profileButton.setFont(new Font("Times", Font.ITALIC, 15));
        changePassword.setFont(new Font("Times", Font.ITALIC, 15));
        topup.setFont(new Font("Times", Font.ITALIC, 15));

        profileButton.setBounds(30,30,140,140);
        changePassword.setBounds(30,220,140,40);
        personalInformation.setBounds(30,280,140,40);

        topup.setBounds(30,340,140,40);


        this.add(personalInformation);
        this.add(profileButton);
        this.add(changePassword);
        this.add(topup);
        this.setVisible(true);
    }
}
