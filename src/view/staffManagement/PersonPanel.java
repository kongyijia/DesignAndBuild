package view.staffManagement;

import model.Client;
import model.Coach;
import model.User;

import javax.swing.*;
import java.awt.*;

public class PersonPanel extends JPanel {
    private Client client;
    private JLabel avatarLabel;
    private JLabel levelLabel;
    private JLabel nameLabel;
    private JLabel sexLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JButton deleteButton;

    public PersonPanel(Client client){
        this.client = client;

        initialize();
    }

    private void initialize(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(280,180));

        // show client avatar
        avatarLabel = new JLabel();
        avatarLabel.setBounds(10,10,70,70);
        ImageIcon avatarIcon = new ImageIcon(client.getAvatarSrc());
        avatarIcon.setImage(avatarIcon.getImage().getScaledInstance(avatarLabel.getWidth(),avatarLabel.getHeight(),Image.SCALE_DEFAULT));
        avatarLabel.setIcon(avatarIcon);

        // show client name
        nameLabel = new JLabel(client.getNickName());
        nameLabel.setBounds(100,10,100,30);

        // show client level
        levelLabel = new JLabel();
        if(client.getRole() == 1)
            levelLabel.setText("Lv : " + ((Coach) client).getLevel() + " (Coach)");
        else if(client.getRole() == 2)
            levelLabel.setText("Lv : " + ((User) client).getLevel() + " (User)");
        else
            levelLabel.setText("Administrator");
        levelLabel.setBounds(100, 40, 100, 30);

        // show client sex
        sexLabel = new JLabel(client.getSex() == 0 ? "female" : "male");
        sexLabel.setBounds(200,40,60,30);

        // show client phone
        phoneLabel = new JLabel("Phone : " + client.getPhone());
        phoneLabel.setBounds(20, 80, 200, 30);

        // show client email
        emailLabel = new JLabel("E-mail : " + client.getEmail());
        emailLabel.setBounds(20,120,200,30);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(240,5,30,30);
        deleteButton.setBorderPainted(false);
        deleteButton.setBorder(null);
        ImageIcon deleteIcon = new ImageIcon("assets/pictures/deleteIcon.jpg");
        deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(deleteButton.getWidth(),deleteButton.getHeight(),Image.SCALE_DEFAULT));
        deleteButton.setIcon(deleteIcon);

        this.add(avatarLabel);
        this.add(nameLabel);
        this.add(levelLabel);
        this.add(sexLabel);
        this.add(phoneLabel);
        this.add(emailLabel);
        this.add(deleteButton);
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
