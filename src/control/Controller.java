package control;

import java.awt.*;

/**
 *  This is superclass of all controller.
 *  A controller controls one view.
 *  When a new controller is created, it will be register to the management list {@link MainFrame}.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public abstract class Controller {
    protected Component panel;
    protected int v_gap = 0;
    protected int h_gap = 0;

    public Controller(String name, Component panel) {
        this.panel = panel;
        this.panel.setVisible(false);

        // Automatically register to tha management list
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

    public abstract void update();
}
