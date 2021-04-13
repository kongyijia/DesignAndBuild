
package view.Userinformation;

import control.MainFrame;
import model.Client;
import model.User;
import util.config;

import javax.swing.*;
import java.awt.*;

public class Userdescription extends JPanel {
    public Client self = null;
    public JFrame parentframe;

    public Userdescription() {
        this.setLayout(null);// Depends on size
        this.setBounds(0, 0, 800, 510);
        update();
        this.setVisible(true);
    }

    public void update() {
        parentframe = MainFrame.getInstance();
        self = MainFrame.getInstance().getClient();
        this.removeAll();
        this.setBackground(new Color(226, 209, 160));
        this.add(new buildProfilepanel());
        this.add(new buildinformation(self));
        this.add(new builddescription(self));
        this.updateUI();
    }


}