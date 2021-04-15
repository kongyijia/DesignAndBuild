package view.staffManagement;

import model.Client;
import view.Userinformation.*;

import javax.swing.*;

public class ClientDetailFrame extends JFrame {
    private Client client;
    private buildinformation buildinformation;
    private builddescription builddescription;
    private JPanel changePanel;
    private JButton modifyLevelButton;
    private JButton modifyVIPButton;
    private JComboBox<String> level;
    private JComboBox<String> VIPLevel;


    public ClientDetailFrame(Client client){
        this.client = client;
        this.setTitle("Detail information of " + client.getNickName());
        initialize();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initialize(){
        this.setLayout(null);
        this.setSize(400,600);
        buildinformation = new buildinformation(client);
        builddescription = new builddescription(client);
        buildinformation.setBounds(0,0,400,300);
        builddescription.setBounds(0,300,400,200);
        this.add(builddescription);
        this.add(buildinformation);

        changePanel = new JPanel();
        changePanel.setBounds(0,500,400,100);

        modifyLevelButton = new JButton("Modify");
        modifyVIPButton = new JButton("Modify");
    }
}
