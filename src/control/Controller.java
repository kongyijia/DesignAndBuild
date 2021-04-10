package control;

import javax.swing.*;
import java.awt.*;

// 所有Controller的父类，一个Controller对应一个功能页面
public abstract class Controller {
    protected Component panel;

    public Controller(String name, Component panel) {
        this.panel = panel;
        this.panel.setVisible(false);
        // 将自己自动注册到管理列表
        MainFrame.getInstance().registerObserver(name, this);
    }

    public Component getPanel() {
        return panel;
    }

    public abstract void update();
}
