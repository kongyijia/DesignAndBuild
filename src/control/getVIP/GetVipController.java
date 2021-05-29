package control.getVIP;

import control.MainFrame;
import model.Client;
import model.User;
import view.buyVip.BuyVip;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Objects;

public class GetVipController
{
    BuyVip vipDialog = null;
    private User user = null;
    private Component parentComponent = null;
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
        if (Objects.equals(user.getVip(), "Big"))
        {
            vipDialog.getBig().setSelected(true);
            vipDialog.getVideo().setSelected(true);
            vipDialog.getCourse().setSelected(true);
            vipDialog.getBig().setEnabled(false);
            vipDialog.getVideo().setEnabled(false);
            vipDialog.getCourse().setEnabled(false);
        }
        if (Objects.equals(user.getVip(), "Video"))
        {
            vipDialog.getVideo().setSelected(true);
            vipDialog.getVideo().setEnabled(false);
        }
        if (Objects.equals(user.getVip(), "Course"))
        {
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
                    vipDialog.getVideo().setSelected(true);
                    vipDialog.getCourse().setSelected(true);
                    vipDialog.getVideo().setEnabled(false);
                    vipDialog.getCourse().setEnabled(false);
                }
                else
                {
                    DecimalFormat accountTem = new DecimalFormat("######0.00");
                    vipDialog.getBalance().setText(accountTem.format(Double.parseDouble(vipDialog.getBalance().getText()) + 2000.0));
                    vipDialog.getVideo().setSelected(false);
                    vipDialog.getCourse().setSelected(false);
                    vipDialog.getVideo().setEnabled(true);
                    vipDialog.getCourse().setEnabled(true);
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
                    if (vipDialog.getCourse().isSelected())
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
                    if (vipDialog.getCourse().isSelected() && flag[0])
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
                    if (vipDialog.getVideo().isSelected())
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
                    if (vipDialog.getVideo().isSelected() && flag[0])
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

    }
}
