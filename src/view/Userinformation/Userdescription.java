
package view.Userinformation;
import control.MainFrame;
import model.Client;
import model.User;
import util.config;

import javax.swing.*;

public class Userdescription extends JPanel{
    public Client self = null;
    public JFrame parentframe;
    public Userdescription(){
        this.setLayout(null);// Depends on size
        this.setBounds(0,0, config.PANEL_WIDTH, config.PANEL_HEIGHT);
        update();
        this.setVisible(true);
    }

    public void update(){
        parentframe = MainFrame.getInstance();
        self =  MainFrame.getInstance().getClient();
        this.removeAll();
        this.add(new buildProfilepanel());
        this.add(new buildinformation(self));
        this.add(new builddescription(self));
    }


}