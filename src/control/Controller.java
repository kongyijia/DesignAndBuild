package control;

import javax.swing.*;
import java.awt.*;

// 所有Controller的父类，一个Controller对应一个功能页面
public abstract class Controller {
    protected Component panel;
    protected int v_gap = 0;
    protected int h_gap = 0;
    public int role = 0;

    public Controller(String name, Component panel) {
        this.panel = panel;
        this.panel.setVisible(false);
        // 将自己自动注册到管理列表
        MainFrame.getInstance().registerObserver(name, this);
    }

    public Component getPanel() {
        return panel;
    }

    public int getV_gap() {
        return v_gap;
    }

    public void setV_gap(int v_gap) {
        this.v_gap = v_gap;
    }

    public int getH_gap() {
        return h_gap;
    }

    public void setH_gap(int h_gap) {
        this.h_gap = h_gap;
    }

    public void setRole(int role) {this.role = role; }

    public int getRole() {return role; }

    public abstract void update();
}
