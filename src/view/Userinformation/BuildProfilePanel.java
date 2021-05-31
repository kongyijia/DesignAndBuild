package view.Userinformation;

import control.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *     Class {@code BuildProfilePanel} is a class extends JPanel.
 * </p>
 * <p>
 *     A container that include the {@code Client} function.<br>
 *         such as : <pre>
 *             change profile picture.
 *             change password.
 *             modify information.
 *             top-up.
 *             get Vip.
 *         </pre>
 * </p>
 * @author Zhanao Zhang
 * @version V1.0
 */
public class BuildProfilePanel extends JPanel
{
    private static final JButton profileButton = new JButton("Profile Picture");
    private static final JButton changePassword = new JButton("Change Password");
    private static final JButton personalInformation = new JButton("modify information");
    private static final JButton topup = new JButton("Top Up");
    private static final JButton getVip = new JButton("Get VIP!! 0.0");

    /**
     *
     * Getter function of {@code profile} button.
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:22
     * @version V1.0
     */
    public static JButton getProfilebutton()
    {
        return profileButton;
    }
    /**
     *
     * Getter function of {@code personalInformation} button.
     * @see control.Userinformation.UserInformationController
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:22
     * @version V1.0
     */
    public static JButton getPersonalInformation()
    {
        return personalInformation;
    }

    /**
     *
     * Getter function of {@code changePassword} button.
     * @see control.Userinformation.UserInformationController
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:22
     * @version V1.0
     */
    public static JButton getChangePassword()
    {
        return changePassword;
    }

    /**
     *
     * Getter function of {@code topUp} button.
     * @see control.Userinformation.UserInformationController
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:22
     * @version V1.0
     */
    public static JButton getTopup()
    {
        return topup;
    }

    /**
     *
     * Getter function of {@code getVip} button.
     * @see control.Userinformation.UserInformationController
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:22
     * @version V1.0
     */
    public static JButton getGetVip()
    {
        return getVip;
    }

    /**
     *
     * Initialize the {@code Client} information panel.
     * @see UserDescription
     * @return void
     * @author Zhanao Zhang
     * @date 2021/5/31 21:25
     * @version V1.0
     */
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
