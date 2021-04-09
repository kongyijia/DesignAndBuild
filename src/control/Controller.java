package control;

import javax.swing.*;

// 所有Controller的父类，一个Controller对应一个功能页面
public abstract class Controller {
    protected JComponent panel;

    public Controller(String name, JComponent panel) {
        this.panel = panel;
        this.panel.setVisible(false);
        // 将自己自动注册到管理列表
        MainFrame.getInstance().registerObserver(name, this);
    }

    public JComponent getPanel() {
        return panel;
    }

    public abstract void update();
}
