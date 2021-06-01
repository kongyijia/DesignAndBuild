package view.buyVip;

import control.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *     Class {@code BuyVip} is a class extends class {@code JDialog}.
 * </p>
 *
 * <p>
 *     A window pops up for the user to select the type of VIP to be recharged.
 * </p>
 *
 * @author Zhanao Zhang
 * @version V1.0
 */

public class BuyVip extends JDialog
{
    private JButton okBtn = new JButton("OK");
    private JButton cenBtn = new JButton("Cancel");
    private JToggleButton Big = new JToggleButton("Premium");
    private JToggleButton Video = new JToggleButton("Video");
    private JToggleButton Course = new JToggleButton("Course");

    private JLabel balanceLabel = new JLabel("Your Balance ");
    private JLabel Balance = new JLabel();

    /**
     *
     * Getter function for {@code OK} button.
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 18:09
     * @version V1.0
     */
    public JButton getOkBtn()
    {
        return okBtn;
    }

    /**
     *
     * Getter function for {@code Cancel} button.
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 18:10
     * @version V1.0
     */
    public JButton getCenBtn()
    {
        return cenBtn;
    }

    /**
     *
     * Getter function for {@code Big} button.
     * @return javax.swing.JToggleButton
     * @author Zhanao Zhang
     * @date 2021/5/31 18:10
     * @version V1.0
     */
    public JToggleButton getBig()
    {
        return Big;
    }

    /**
     *
     * Getter function for {@code Video} button.
     * @return javax.swing.JToggleButton
     * @author Zhanao Zhang
     * @date 2021/5/31 18:10
     * @version V1.0
     */
    public JToggleButton getVideo()
    {
        return Video;
    }

    /**
     *
     * Getter function for {@code Course} button.
     * @return javax.swing.JToggleButton
     * @author Zhanao Zhang
     * @date 2021/5/31 18:10
     * @version V1.0
     */
    public JToggleButton getCourse()
    {
        return Course;
    }

    /**
     *
     * Getter function for {@code Balance} JLabel.
     * @return javax.swing.JLabel
     * @author Zhanao Zhang
     * @date 2021/5/31 18:11
     * @version V1.0
     */
    public JLabel getBalance()
    {
        return Balance;
    }

    /**
     *
     * pop-up window.
     * @param parentComponent parent component
     * @author Zhanao Zhang
     * @date 2021/5/31 18:12
     * @version V1.0
     */
    public BuyVip(Component parentComponent) {

        super(MainFrame.getInstance() ,"Choose Your VIP Type", true);

        this.setSize(600, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(parentComponent);


        JPanel panel = new JPanel();
        panel.setLayout(null);


        balanceLabel.setFont(new Font("Times", Font.ITALIC, 15));
        Balance.setFont(new Font("Times", Font.ITALIC, 17));





        balanceLabel.setBounds(190,20,100,40);
        Balance.setBounds(340,20,100,40);
        Big.setBounds(70,80,120,120);
        Video.setBounds(240,80,120,120);
        Course.setBounds(410,80,120,120);
        okBtn.setBounds(180,220,100,40);
        cenBtn.setBounds(330,220,100,40);


        panel.add(balanceLabel);
        panel.add(Balance);
        panel.add(Big);
        panel.add(Video);
        panel.add(Course);
        panel.add(okBtn);
        panel.add(cenBtn);
        this.setContentPane(panel);

    }

}
