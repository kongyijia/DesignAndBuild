import control.MainFrame;

import util.config;

import java.awt.*;

/**
 *  This is ths main entrance of the whole project.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public class MainApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame mainFrame = MainFrame.getInstance();
                    MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
