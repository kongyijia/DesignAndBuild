package control.getVIP;

import control.MainFrame;
import control.Userinformation.UserInformationController;
import model.Client;
import model.User;
import model.mapping.ClientMapping;
import view.buyVip.BuyVip;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class
GetVipController
{
    BuyVip vipDialog = null;
    private User user = null;
    private Component parentComponent = null;
    final boolean[] lock = {false,false,false};
    public GetVipController(Component parentComponent, Client user)
    {
        this.user = (User) user;
        this.parentComponent = parentComponent;
        init();
    }

    private void init()
    {
        vipDialog = new BuyVip(parentComponent);
        DecimalFormat accountTem = new DecimalFormat("######0.00");
        vipDialog.getBalance().setText("" + accountTem.format(user.getAccount()));
        if (Objects.equals(user.getVip(), "BIG"))
        {
            lock[0] = true;
            lock[1] = true;
            lock[2] = true;
            vipDialog.getBig().setSelected(true);
            vipDialog.getVideo().setSelected(true);
            vipDialog.getCourse().setSelected(true);
            vipDialog.getBig().setEnabled(false);
            vipDialog.getVideo().setEnabled(false);
            vipDialog.getCourse().setEnabled(false);
        }
        if (Objects.equals(user.getVip(), "VIDEO"))
        {
            lock[1] = true;
            vipDialog.getVideo().setSelected(true);
            vipDialog.getVideo().setEnabled(false);
        }
        if (Objects.equals(user.getVip(), "COURSE"))
        {
            lock[2] = true;
            vipDialog.getCourse().setSelected(true);
            vipDialog.getCourse().setEnabled(false);
        }
        addButton();
        vipDialog.setVisible(true);
    }
    private void addButton()
    {
        final boolean[] flag = {false};
        vipDialog.getCenBtn().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                vipDialog.dispose();
            }
        });

        vipDialog.getBig().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 获取事件源（即开关按钮本身）
                JToggleButton toggleBtn = (JToggleButton) e.getSource();
                if (toggleBtn.isSelected())
                {
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) - 2000.0));
                    if (vipDialog.getVideo().isSelected() && !lock[1])
                        vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) + 1200.0));
                    if (vipDialog.getCourse().isSelected() && !lock[2])
                        vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) + 1200.0));
                    if(!lock[1])
                    {
                        vipDialog.getVideo().setSelected(true);
                        vipDialog.getVideo().setEnabled(false);
                    }
                    if (!lock[2])
                    {
                        vipDialog.getCourse().setSelected(true);
                        vipDialog.getCourse().setEnabled(false);
                    }
                }
                else
                {
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) + 2000.0));
                    if (!lock[1])
                    {
                        vipDialog.getVideo().setSelected(false);
                        vipDialog.getVideo().setEnabled(true);
                    }
                    if (!lock[2])
                    {
                        vipDialog.getCourse().setSelected(false);
                        vipDialog.getCourse().setEnabled(true);
                    }
                }
            }
        });

        vipDialog.getVideo().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 获取事件源（即开关按钮本身）
                JToggleButton toggleBtn = (JToggleButton) e.getSource();
                if (toggleBtn.isSelected())
                {
                    if (vipDialog.getCourse().isSelected() && !lock[0])
                    {
                        vipDialog.getBig().setSelected(true);
                        vipDialog.getBig().setEnabled(false);
                        flag[0] = true;
                    }
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) - 1200.0));
                }
                else
                {
                    if (vipDialog.getCourse().isSelected() && flag[0] && !lock[0])
                    {
                        flag[0] = false;
                        vipDialog.getBig().setSelected(false);
                        vipDialog.getBig().setEnabled(true);
                    }
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) + 1200.0));
                }
            }
        });

        vipDialog.getCourse().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 获取事件源（即开关按钮本身）
                JToggleButton toggleBtn = (JToggleButton) e.getSource();
                if (toggleBtn.isSelected())
                {
                    if (vipDialog.getVideo().isSelected() && !lock[0])
                    {
                        vipDialog.getBig().setSelected(true);
                        vipDialog.getBig().setEnabled(false);
                        flag[0] = true;
                    }
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) - 1200.0));
                }
                else
                {
                    if (vipDialog.getVideo().isSelected() && flag[0] && !lock[0])
                    {
                        flag[0] = false;
                        vipDialog.getBig().setSelected(false);
                        vipDialog.getBig().setEnabled(true);
                    }
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) + 1200.0));
                }
            }
        });

        vipDialog.getOkBtn().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (Double.parseDouble(vipDialog.getBalance().getText()) < 0)
                    JOptionPane.showMessageDialog(parentComponent,"Sorry, your credit is running low!");
                else
                {
                    try
                    {
                        user.setAccount(Double.parseDouble(vipDialog.getBalance().getText()));
                        if(vipDialog.getBig().isSelected())
                            user.setVip("BIG");
                        else
                        {
                            if (vipDialog.getVideo().isSelected())
                                user.setVip("VIDEO");
                            else if (vipDialog.getCourse().isSelected())
                                user.setVip("COURSE");
                        }
                        ClientMapping.modify((Client) user);
                        MainFrame.getInstance().setClient((Client) user);
                        JOptionPane.showMessageDialog(parentComponent,"Your VIP : " + user.getVip());
                        vipDialog.dispose();
                    } catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }
            }
        });


    }
}
