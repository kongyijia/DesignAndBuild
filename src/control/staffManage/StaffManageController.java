package control.staffManage;

import control.Controller;
import control.MainFrame;
import model.Client;
import model.mapping.ClientMapping;
import util.Util;
import util.config;
import view.staffManagement.ClientDetailFrame;
import view.staffManagement.InsertButtonPanel;
import view.staffManagement.PersonPanel;
import view.staffManagement.StaffManagePanel;

import javax.swing.*;
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
                } else if (k.equals("cancel"))
                    searchMap.put(k, v.getComboBox().getSelectedItem().equals("Active") ? "false" : "true");
                else
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
        // clear old panel
        staffManagePanel.getInfoPanel().removeAll();
        staffManagePanel.getPersonMap().clear();

        // create new insert panel
        InsertButtonPanel insertPanel = new InsertButtonPanel();
        insertPanel.getAdminInsertButton().addActionListener(e -> {
            MainFrame.getInstance().goTo(config.STAFF_INSERT_NAME);
            setRole(0);
        });
        insertPanel.getCoachInsertButton().addActionListener(e -> {
            MainFrame.getInstance().goTo(config.STAFF_INSERT_NAME);
            setRole(1);
        });
        staffManagePanel.getInfoPanel().add(insertPanel);

        // create new client panels
        staffManagePanel.getClients().forEach(client -> staffManagePanel.getPersonMap().put(client.getId(), new PersonPanel(client)));
        staffManagePanel.getPersonMap().forEach((k, v) -> {
            staffManagePanel.getInfoPanel().add(v);
            v.getDeleteButton().addActionListener(new PersonPanelListener());
            v.getDetailButton().addActionListener(new PersonPanelListener());
        });
        int client_num = staffManagePanel.getClients().size();
        if (client_num == 0)
            Util.showDialog(staffManagePanel, "No qualified clients were found!");
        else if (client_num < 5) {
            for (int i = 0; i < 5 - client_num; i++) {
                staffManagePanel.getInfoPanel().add(new PersonPanel());
            }
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

    class PersonPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            staffManagePanel.getPersonMap().forEach((k, v) -> {
                // delete client
                if (e.getSource() == v.getDeleteButton()) {
                    Object[] buttonName = {"Confirm", "Cancel"};
                    int result = JOptionPane.showOptionDialog(staffManagePanel,
                            "Are you sure to delete this client?\n ",
                            "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                    // confirm to delete
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            if (ClientMapping.cancel(k) == ClientMapping.SUCCESS) {
                                Util.showDialog(staffManagePanel, "Delete Success! ");
                                staffManagePanel.setClients(search_clients());
                                update();
                            } else
                                Util.showDialog(staffManagePanel, "Error! \n     Delete failed !");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                // show the detail information of client
                else if (e.getSource() == v.getDetailButton()) {

                    ClientDetailFrame clientDetail = new ClientDetailFrame(v.getClient());

                }
            });
        }
    }
}
