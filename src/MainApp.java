import java.awt.*;


import views.MainFrame;

import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    MainFrame frame = new MainFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
