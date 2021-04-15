package view.staffManagement;

import control.MainFrame;
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
    private JLabel cancelLabel;

    private JButton deleteButton;
    private JButton detailButton;

    public PersonPanel(Client client){
        this.client = client;

        initialize();
    }

    public PersonPanel(){
        this.setPreferredSize(new Dimension(280,180));
        this.setBackground(Color.white);
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

        // show client isCancel
        if (client.isCancel()){
            cancelLabel = new JLabel("Canceled");
            cancelLabel.setForeground(Color.RED);
            cancelLabel.setBounds(200,10,80,30);
            this.add(cancelLabel);
        }

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
        emailLabel.setBounds(20,100,200,30);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(50,140,80,25);
        deleteButton.setBackground(Color.white);

        detailButton = new JButton("Detail");
        detailButton.setBounds(150,140,80,25);
        detailButton.setBackground(Color.white);

        if (MainFrame.getInstance().getClient().getId() == client.getId() || client.isCancel())
            deleteButton.setVisible(false);
        if (client.getRole() == 0)
            detailButton.setVisible(false);

        this.add(avatarLabel);
        this.add(nameLabel);
        this.add(levelLabel);
        this.add(sexLabel);
        this.add(phoneLabel);
        this.add(emailLabel);
        this.add(deleteButton);
        this.add(detailButton);
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getDetailButton() {
        return detailButton;
    }

    public Client getClient() {
        return client;
    }
}
