package view.Userinformation;

import control.MainFrame;

import javax.swing.*;
import java.awt.*;

public class BuildProfilePanel extends JPanel
{
    private static final JButton profileButton = new JButton("Profile Picture");
    private static final JButton changePassword = new JButton("Change Password");
    private static final JButton personalInformation = new JButton("modify information");
    private static final JButton topup = new JButton("Top Up");
    private static final JButton getVip = new JButton("Get VIP!! 0.0");

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

    public static JButton getGetVip()
    {
        return getVip;
    }

    public BuildProfilePanel()
    {
        this.setSize(200,510);
        this.setLocation(0,0);
        this.setBackground(new Color(125, 101, 53));
        this.setLayout(null);

        profileButton.setBounds(30,30,140,140);
        changePassword.setBounds(30,220,140,40);
        personalInformation.setBounds(30,280,140,40);
        topup.setBounds(30,340,140,40);
        getVip.setBounds(30,400,140,40);

        String originalPicture = MainFrame.getInstance().getClient().getAvatarSrc();
        ImageIcon logoIcon = new ImageIcon(originalPicture);
        logoIcon.setImage(logoIcon.getImage().getScaledInstance(profileButton.getWidth(),profileButton.getHeight(),Image.SCALE_DEFAULT));
        profileButton.setIcon(logoIcon);

        personalInformation.setFont(new Font("Times", Font.ITALIC, 12));
        profileButton.setFont(new Font("Times", Font.ITALIC, 15));
        changePassword.setFont(new Font("Times", Font.ITALIC, 12));
        topup.setFont(new Font("Times", Font.ITALIC, 15));
        getVip.setFont(new Font("Times", Font.ITALIC, 15));

        this.add(personalInformation);
        this.add(profileButton);
        this.add(changePassword);
        if (MainFrame.getInstance().getClient().getRole() == 2)
        {
            this.add(topup);
            this.add(getVip);
        }
        this.setVisible(true);
    }
}
