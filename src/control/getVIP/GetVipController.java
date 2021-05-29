package control.getVIP;

import control.MainFrame;
import model.Client;
import model.User;
import view.buyVip.BuyVip;

import java.awt.*;
import java.text.DecimalFormat;

public class GetVipController
{
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
        BuyVip vipDialog = new BuyVip(parentComponent);
        DecimalFormat accountTem = new DecimalFormat("######0.00");
        vipDialog.getBalance().setText("" + accountTem.format(user.getAccount()));
        if (user.getVip() == "Plain")
        {

        }

        vipDialog.setVisible(true);
    }
}
