import control.MainFrame;
import control.enroll.EnrollController;
import control.function.FunctionController;
import control.index.IndexController;
import util.config;

import java.awt.*;
import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    MainFrame mainFrame = MainFrame.getInstance();
                    mainFrame.add(new FunctionController().getPanel());
                    mainFrame.add(new EnrollController().getPanel());
                    mainFrame.add(new IndexController().getPanel());
                    MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
