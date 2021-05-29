package view.Userinformation;

import model.Client;
import model.Coach;
import model.User;

import javax.swing.*;
import java.awt.*;

public class BuildDescription extends JPanel
{
    public BuildDescription(Client receiveuser)
    {
        JTextArea destext = new JTextArea();
        if (receiveuser.getRole() == 1) //coach
        {
            Coach self = (Coach) receiveuser;
            destext = new JTextArea(self.getDescription());
        }

        if (receiveuser.getRole() == 2) // user
        {
            User self = (User) receiveuser;
            destext = new JTextArea(self.getDescription());
        }
        this.setBounds(300,370,500,140);
        this.setLayout(null);

        JLabel des = new JLabel("description:");
        des.setBounds(0,0,100,40);
        Font font = new Font("Times",  Font.ITALIC | Font.BOLD, 15 );
        des.setFont(font);

        destext.setFont(font);
        destext.setBounds(0,40,500,80);
        destext.setEditable(false);

        this.add(des);
        this.add(destext);
        this.setVisible(true);
    }

}
