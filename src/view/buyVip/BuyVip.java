package view.buyVip;

import control.MainFrame;

import javax.swing.*;
import java.awt.*;

public class BuyVip extends JDialog
{
    private JButton okBtn = new JButton("OK");
    private JButton cenBtn = new JButton("Cancel");
    private JToggleButton Big = new JToggleButton("BIG");
    private JToggleButton Video = new JToggleButton("Video");
    private JToggleButton Course = new JToggleButton("Course");

    // 创建一个标签显示消息内容
    private JLabel balanceLabel = new JLabel("Your Balance ");
    private JLabel Balance = new JLabel();

    public JButton getOkBtn()
    {
        return okBtn;
    }

    public JButton getCenBtn()
    {
        return cenBtn;
    }

    public JToggleButton getBig()
    {
        return Big;
    }

    public JToggleButton getVideo()
    {
        return Video;
    }

    public JToggleButton getCourse()
    {
        return Course;
    }

    public JLabel getBalance()
    {
        return Balance;
    }

    public BuyVip(Component parentComponent) {

        // 创建一个模态对话框
        super(MainFrame.getInstance() ,"Choose Your VIP Type", true);

        // 设置对话框的宽高
        this.setSize(600, 300);
        // 设置对话框大小不可改变
        this.setResizable(false);
        // 设置对话框相对显示的位置
        this.setLocationRelativeTo(parentComponent);


        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();
        panel.setLayout(null);

//        balanceLabel.setOpaque(true);
//        Balance.setOpaque(true);
//        balanceLabel.setBackground(Color.GREEN);
//        Balance.setBackground(Color.GREEN);


        balanceLabel.setFont(new Font("Times", Font.ITALIC, 15));
        Balance.setFont(new Font("Times", Font.ITALIC, 17));





        balanceLabel.setBounds(190,20,100,40);
        Balance.setBounds(340,20,100,40);
        Big.setBounds(70,80,120,120);
        Video.setBounds(240,80,120,120);
        Course.setBounds(410,80,120,120);
        okBtn.setBounds(180,220,100,40);
        cenBtn.setBounds(330,220,100,40);


        // 添加组件到面板
        panel.add(balanceLabel);
        panel.add(Balance);
        panel.add(Big);
        panel.add(Video);
        panel.add(Course);
        panel.add(okBtn);
        panel.add(cenBtn);

        // 设置对话框的内容面板
        this.setContentPane(panel);

    }

}
