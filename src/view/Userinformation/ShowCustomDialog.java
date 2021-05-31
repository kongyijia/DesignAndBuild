package view.Userinformation;

import control.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 *     Class {@code ShowCustomDialog} is a class extends JDialog.
 * </p>
 * <p>
 *     Change the password dialog. <br>
 * </p>
 * @see JDialog
 * @see control.Userinformation.UserInformationController
 */
public class ShowCustomDialog extends JDialog
{
    private JButton okBtn = new JButton("OK");
    private JButton CenBtn = new JButton("Cancel");
    private JButton bt1 = new JButton("show");
    public int times = 0;
    // 创建一个标签显示消息内容
    private JLabel npassword = new JLabel("New Password:");
    private JLabel cpassword = new JLabel("Confirm New Password:");
    private JLabel opassword = new JLabel("Original Password:");


    private JPasswordField opwd = new JPasswordField();
    private JPasswordField npwd = new JPasswordField();
    private JPasswordField cpwd = new JPasswordField();


    /**
     *
     * Getter function of component.
     * @return javax.swing.JPasswordField
     * @author Zhanao Zhang
     * @date 2021/5/31 21:30
     * @version V1.0
     */
    public JPasswordField getOpwd() { return opwd; }

    /**
     *
     * Getter function of component.
     * @return javax.swing.JPasswordField
     * @author Zhanao Zhang
     * @date 2021/5/31 21:30
     * @version V1.0
     */
    public JPasswordField getNpwd()
    {
        return npwd;
    }

    /**
     *
     * Getter function of component.
     * @return javax.swing.JPasswordField
     * @author Zhanao Zhang
     * @date 2021/5/31 21:30
     * @version V1.0
     */
    public JPasswordField getCpwd()
    {
        return cpwd;
    }

    /**
     *
     * Getter function of OkButton.
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:30
     * @version V1.0
     */
    public JButton getOkBtn()
    {
        return okBtn;
    }

    /**
     *
     * Getter function of CenButton.
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:30
     * @version V1.0
     */
    public JButton getCenBtn()
    {
        return CenBtn;
    }

    /**
     *
     * Getter function of Bt1Button.
     * @return javax.swing.JButton
     * @author Zhanao Zhang
     * @date 2021/5/31 21:30
     * @version V1.0
     */
    public JButton getBt1()
    {
        return bt1;
    }

    /**
     *
     * Show the change password Dialog.
     * @param parentComponent father component
     * @return void
     * @author Zhanao Zhang
     * @date 2021/5/31 21:31
     * @version V1.0
     */

    public ShowCustomDialog(Component parentComponent) {

        // 创建一个模态对话框
        super(MainFrame.getInstance() ,"Change Your Password", true);
        JFrame Owner = MainFrame.getInstance();

        // 设置对话框的宽高
        this.setSize(300, 150);
        // 设置对话框大小不可改变
        this.setResizable(false);
        // 设置对话框相对显示的位置
        this.setLocationRelativeTo(parentComponent);


        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JPanel pwdpanel = new JPanel(new GridLayout(3,3));
        JPanel btnpanel = new JPanel(new GridLayout(1,2));


        // 添加组件到面板
        pwdpanel.add(opassword);
        pwdpanel.add(opwd);
        pwdpanel.add(npassword);
        pwdpanel.add(npwd);
        pwdpanel.add(cpassword);
        pwdpanel.add(cpwd);
        btnpanel.add(okBtn);
        btnpanel.add(CenBtn);
        btnpanel.add(bt1);
        panel.add(pwdpanel);
        panel.add(btnpanel);

        // 设置对话框的内容面板
        this.setContentPane(panel);
        // 显示对话框

    }


}
