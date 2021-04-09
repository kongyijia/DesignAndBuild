
package view;
import model.User;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Userdescription extends JPanel{
    public static User self = null;
    public JFrame parentframe;
    public Userdescription(User user,JFrame owner){
        self = user;
        parentframe = owner;
        this.setLayout(null);// Depends on size
        this.add(buildProfilepanel());
        this.add(buildinformation());
        this.add(builddescription());


    }

    private void showCustomDialog(JFrame Owner ,Component parentComponent) {

        int[] times = {0};
        // 创建一个模态对话框
        final JDialog dialog = new JDialog(Owner ,"Change Your Password", true);
        // 设置对话框的宽高
        dialog.setSize(300, 150);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        // 创建一个标签显示消息内容
        JLabel opassword = new JLabel("Original Password:");
        JLabel npassword = new JLabel("New Password:");
        JLabel cpassword = new JLabel("Confirm New Password:");
        JButton bt1 = new JButton("show");

        JPasswordField opwd = new JPasswordField();
        JPasswordField npwd = new JPasswordField();
        JPasswordField cpwd = new JPasswordField();

        // 创建一个按钮用于关闭对话框
        JButton okBtn = new JButton("OK");
        JButton CenBtn = new JButton("Cancel");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkpassword(new String(opwd.getPassword())))
                {
                    if (new String(npwd.getPassword()).equals(new String(cpwd.getPassword())))
                    {
                        changepassword(new String(npwd.getPassword()));
                        JOptionPane.showMessageDialog(new JPanel(), "Change Successfully.");
                        dialog.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(Owner,"The entered password is different");
                }
                else
                    JOptionPane.showMessageDialog(Owner,"Wrong Password!");
            }
        });

        bt1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                times[0]++;
                if (times[0] % 2 != 0)
                {
                    opwd.setEchoChar((char) 0);
                    npwd.setEchoChar((char) 0);
                    cpwd.setEchoChar((char) 0);
                }
                else
                {
                    opwd.setEchoChar('*');
                    npwd.setEchoChar('*');
                    cpwd.setEchoChar('*');
                }

            }
        });

        CenBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.dispose();
            }
        });

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
        dialog.setContentPane(panel);
        // 显示对话框
        dialog.setVisible(true);
    }

    private static void changepassword(String newpwd)
    {
        self.setPassword(newpwd);
    }

    private static boolean checkpassword(String oldpwd)
    {
        String correctpassword = self.getPassword();
        return Objects.equals(correctpassword, oldpwd);
    }



    private JPanel buildProfilepanel()
    {
        JPanel profilepanel = new JPanel();
        profilepanel.setSize(200,510);
        profilepanel.setLocation(200,0);
        profilepanel.setBackground(Color.red);
        profilepanel.setLayout(null);
        JButton profilebutton = new JButton("Profile Picture");
        JButton changepassword = new JButton("Change Password");
        JButton changeinformation = new JButton("modify personal information");
        JButton topup = new JButton("Top Up");

        changeinformation.setFont(new Font("Times", Font.ITALIC, 10));
        profilebutton.setFont(new Font("Times", Font.ITALIC, 15));
        changepassword.setFont(new Font("Times", Font.ITALIC, 15));
        topup.setFont(new Font("Times", Font.ITALIC, 15));

        profilebutton.setBounds(30,30,140,140);
        changepassword.setBounds(30,220,140,40);
        changeinformation.setBounds(30,280,140,40);

        topup.setBounds(30,340,140,40);

        profilebutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(profilepanel,"Developing!");
            }
        });

        changepassword.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showCustomDialog(parentframe,profilepanel);
                //todo
            }
        });

        changeinformation.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //TODO
            }
        });

        topup.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO

            }
        });

        profilepanel.add(changeinformation);
        profilepanel.add(profilebutton);
        profilepanel.add(changepassword);
        profilepanel.add(topup);
        profilepanel.setVisible(true);
        return profilepanel;
    }

    private JPanel buildinformation(){
        JPanel information = new JPanel();
        information.setBounds(500,20,500,330);
        information.setBackground(Color.cyan);
        JLabel Label_name = new JLabel("Name: ");
        JLabel Label_sex = new JLabel("Sex: ");
        JLabel Label_Phone = new JLabel("Phone: ");
        JLabel Label_mail = new JLabel("E-mail: ");
        JLabel Label_account = new JLabel("Account: ");
        JLabel Label_level = new JLabel("Level: ");
        JLabel Label_Time = new JLabel("Study Time: ");
        JLabel Label_vip = new JLabel("VIP Level: ");
        GridLayout layout = new GridLayout(8, 2);
        information.setLayout(layout);// Depends on size
        if (self != null)
        {
            JLabel name = new JLabel(self.getNickName());
            int sex_tem = self.getSex();
            String sex_string = null;
            if (sex_tem == 1)
                sex_string = "male";
            if (sex_tem == 0)
                sex_string = "female";
            JLabel sex = new JLabel(sex_string);
            JLabel phone = new JLabel(self.getPhone());
            JLabel mail = new JLabel(self.getEmail());
            JLabel account = new JLabel("" + self.getAccount());
            JLabel level = new JLabel("" + self.getLevel());
            JLabel time = new JLabel("" + self.getLearningTime());
            JLabel vip = new JLabel("" + self.getVip());
            information.add(Label_name);
            information.add(name);
            information.add(Label_sex);
            information.add(sex);
            information.add(Label_Phone);
            information.add(phone);
            information.add(Label_mail);
            information.add(mail);
            information.add(Label_account);
            information.add(account);
            information.add(Label_level);
            information.add(level);
            information.add(Label_Time);
            information.add(time);
            information.add(Label_vip);
            information.add(vip);
        }
        information.setVisible(true);
        return information;
    }

    private JPanel builddescription()
    {
        JPanel description = new JPanel();
        description.setBounds(500,370,500,140);
        description.setBackground(Color.YELLOW);
        description.setLayout(null);

        JLabel des = new JLabel("Description:");
        des.setBounds(0,0,100,40);
        Font font = new Font("Times",  Font.ITALIC | Font.BOLD, 15 );
        des.setFont(font);

        JTextArea destext = new JTextArea(self.getDescription());
        destext.setFont(font);
        destext.setBounds(0,40,500,80);
        destext.setEditable(false);

        description.add(des);
        description.add(destext);
        description.setVisible(true);
        return description;
    }


}