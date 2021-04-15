package control.staffManage;

import control.Controller;
import model.Client;
import model.mapping.ClientMapping;
import util.Util;
import util.config;
import view.staffManagement.PersonPanel;
import view.staffManagement.StaffManagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StaffManageController extends Controller {
    private StaffManagePanel staffManagePanel;

    public StaffManageController() {
        super(config.STAFF_MANAGE_NAME, new StaffManagePanel());
        staffManagePanel = (StaffManagePanel) this.panel;

        ArrayList<Client> init_clients = search_clients();
        staffManagePanel.setClients(init_clients);
        staffManagePanel.addListener(new StaffManageListener());
        update();
    }

    // clear the search information
    private void search_reset() {
        staffManagePanel.getSearchComboBoxMap().forEach((k, v) -> v.getComboBox().setSelectedIndex(0));
        staffManagePanel.getSearchInputField().setText("");
    }

    private ArrayList<Client> search_clients() {
        HashMap<String, String> searchMap = new HashMap<>();
        // add search information
        if (!staffManagePanel.getSearchInputField().getText().equals(""))
            searchMap.put("nickName", staffManagePanel.getSearchInputField().getText());
        staffManagePanel.getSearchComboBoxMap().forEach((k, v) -> {
            if (!v.getComboBox().getSelectedItem().equals("All")) {
                if (k.equals("sex"))
                    searchMap.put(k, v.getComboBox().getSelectedItem().equals("female") ? "0" : "1");
                else if (k.equals("role")) {
                    String role = (String) v.getComboBox().getSelectedItem();
                    if ("Admin".equals(role))
                        searchMap.put(k, "0");
                    else if ("Coach".equals(role))
                        searchMap.put(k, "1");
                    else if ("User".equals(role))
                        searchMap.put(k, "2");
                } else
                    searchMap.put(k, (String) v.getComboBox().getSelectedItem());
            }
        });
        // search clients
        ArrayList<Client> clients = new ArrayList<>();
        try {
            clients = ClientMapping.find(searchMap);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return clients;
    }

    @Override
    public void update() {
        System.out.println("Staff Management Page update");
        staffManagePanel.getInfoPanel().removeAll();
        staffManagePanel.getPersonMap().clear();
        staffManagePanel.getClients().forEach(client -> staffManagePanel.getPersonMap().put(client.getId(), new PersonPanel(client)));
        staffManagePanel.getPersonMap().forEach((k, v) -> {
            staffManagePanel.getInfoPanel().add(v);
            v.getDeleteButton().addActionListener(new StaffDeleteListener());
        });
        int client_num = staffManagePanel.getClients().size();
        System.out.println(client_num);
        if (client_num == 0)
            Util.showDialog(staffManagePanel, "No qualified clients were found!");
        else if (client_num < 5 ) {
            // TODO change infoPanel size for matching clients' number
        }
        staffManagePanel.updateUI();
    }

    class StaffManageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // add search listener
            if (e.getSource() == staffManagePanel.getSearchButton()) {
                staffManagePanel.setClients(search_clients());
                update();
            }
            // add reset listener
            if (e.getSource() == staffManagePanel.getResetButton()) {
                search_reset();
            }
        }
    }

    class StaffDeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("delete");
            staffManagePanel.getPersonMap().forEach((k, v) -> {
                if (e.getSource() == v.getDeleteButton()) {
                    Object[] buttonName = {"Confirm", "Cancel"};
                    int result = JOptionPane.showOptionDialog(staffManagePanel,
                            "Are you sure to delete this client?\n ",
                            "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            ClientMapping.delete(k);
                            update();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
