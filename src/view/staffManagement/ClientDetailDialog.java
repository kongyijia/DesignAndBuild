package view.staffManagement;

import control.MainFrame;
import model.Client;
import model.Coach;
import model.User;
import view.Userinformation.*;

import javax.swing.*;

/**
 *  This is the dialog to show detail information of one client.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public class ClientDetailDialog extends JDialog {
    private Client client;
    private BuildInformation buildinformation;
    private BuildDescription builddescription;
    private JPanel changePanel;
    private JButton modifyLevelButton =  new JButton("Modify");
    private JButton modifyVIPButton = new JButton("Modify");
    private JComboBox<Integer> level;
    private JComboBox<Integer> VIPLevel;

    public ClientDetailDialog(Client client){
        super(MainFrame.getInstance() ,"Detail information of " + client.getNickName(), true);

        this.client = client;
        initialize();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initialize(){
        this.setLayout(null);
        this.setSize(400,600);
        buildinformation = new BuildInformation(client);
        builddescription = new BuildDescription(client);
        buildinformation.setBounds(0,0,400,300);
        builddescription.setBounds(0,300,400,150);
        this.add(builddescription);
        this.add(buildinformation);

        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(0,450,400,150);

        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setBounds(40,10,50,30);
        changePanel.add(levelLabel);

        if (client.getRole() == 1) {
            level = new JComboBox<>(new Integer[]{0, 1, 2});
            level.setSelectedIndex(((Coach) client).getLevel());
        }
        else if (client.getRole() == 2) {
            JLabel VIPLabel = new JLabel("VIP Level: ");
            VIPLabel.setBounds(20,60,70,30);
            changePanel.add(VIPLabel);

            level = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6});
            level.setSelectedIndex(((User) client).getLevel());

            VIPLevel = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6});
            VIPLevel.setSelectedIndex(((User) client).getVip());
            VIPLevel.setBounds(100,60, 100,30);
            changePanel.add(VIPLevel);

            modifyVIPButton.setBounds(280,60,80,30);
            changePanel.add(modifyVIPButton);
        }
        level.setBounds(100,10, 100,30);
        changePanel.add(level);

        modifyLevelButton.setBounds(280,10,80,30);
        changePanel.add(modifyLevelButton);

        this.add(changePanel);
    }

    public JButton getModifyLevelButton() {
        return modifyLevelButton;
    }

    public JButton getModifyVIPButton() {
        return modifyVIPButton;
    }

    public JComboBox<Integer> getLevel() {
        return level;
    }

    public JComboBox<Integer> getVIPLevel() {
        return VIPLevel;
    }

    public void setBuildinformation(BuildInformation information) {
        this.remove(buildinformation);
        this.buildinformation = information;
        buildinformation.setBounds(0,0,400,300);
        this.add(buildinformation);
        this.validate();
    }
}
