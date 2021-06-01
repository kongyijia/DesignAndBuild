package control.staffManage;

import control.Controller;
import control.MainFrame;
import model.Client;
import model.Coach;
import model.User;
import model.mapping.ClientMapping;
import util.Util;
import util.config;
import view.Userinformation.BuildInformation;
import view.staffManagement.ClientDetailDialog;
import view.staffManagement.InsertButtonPanel;
import view.staffManagement.PersonPanel;
import view.staffManagement.StaffManagePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @description This class is used to control view display and data interaction of {@link StaffManagePanel}
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class StaffManageController extends Controller {
    private final StaffManagePanel staffManagePanel;

    public StaffManageController() {
        super(config.STAFF_MANAGE_NAME, new StaffManagePanel());
        staffManagePanel = (StaffManagePanel) this.panel;

        staffManagePanel.addListener(new StaffManageListener());

        staffManagePanel.getSearchComboBoxMap().get("role").getComboBox().addItemListener(e -> {
            JComboBox<String> comboBox = staffManagePanel.getSearchComboBoxMap().get("level").getComboBox();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getItem().equals("All") || e.getItem().equals("Admin")) {
                    comboBox.removeAllItems();
                    comboBox.addItem("All");
                    comboBox.setSelectedIndex(0);
                }
                if (e.getItem().equals("Coach")) {
                    comboBox.removeAllItems();
                    comboBox.addItem("All");
                    String[] levelName = new String[]{"normal", "advanced", "outstanding"};
                    for (String s : levelName) comboBox.addItem(s);
                }
                else if (e.getItem().equals("User")) {
                    comboBox.removeAllItems();
                    comboBox.addItem("All");
                    for (int i = 1; i < 13; i++)
                        comboBox.addItem(Integer.toString(i));
                }
                comboBox.updateUI();
            }
        });
        update();
    }

    /**
     * This method is used to clear the search information
     */
    private void search_reset() {
        staffManagePanel.getSearchComboBoxMap().forEach((k, v) -> v.getComboBox().setSelectedIndex(0));
        staffManagePanel.getSearchInputField().setText("");
    }

    /**
     * This method is used to search for clients that meet the filter criteria.
     *
     * @return Clients which meet the filter criteria
     */
    private ArrayList<Client> search_clients() {
        HashMap<String, String> searchMap = new HashMap<>();
        // add search information
        if (!staffManagePanel.getSearchInputField().getText().equals(""))
            searchMap.put("nickName", staffManagePanel.getSearchInputField().getText());
        staffManagePanel.getSearchComboBoxMap().forEach((k, v) -> {
            if (!Objects.equals(v.getComboBox().getSelectedItem(), "All")) {
                switch (k) {
                    case "sex":
                        searchMap.put(k, v.getComboBox().getSelectedItem().equals("female") ? "0" : "1");
                        break;
                    case "role":
                        String role = (String) v.getComboBox().getSelectedItem();
                        if ("Admin".equals(role))
                            searchMap.put(k, "0");
                        else if ("Coach".equals(role))
                            searchMap.put(k, "1");
                        else if ("User".equals(role))
                            searchMap.put(k, "2");
                        break;
                    case "cancel":
                        searchMap.put(k, v.getComboBox().getSelectedItem().equals("Active") ? "false" : "true");
                        break;
                    case "level":
                        searchMap.put(k, (v.getComboBox().getSelectedIndex() - 1) + "");
                        break;
                    default:
                        searchMap.put(k, (String) v.getComboBox().getSelectedItem());
                        break;
                }
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

    /**
     * This method will use {@link PersonPanel} to display qualified client information in {@link StaffManagePanel} page
     */
    private void showClientInfo() {
        // clear old panel
        staffManagePanel.getInfoPanel().removeAll();
        staffManagePanel.getPersonMap().clear();

        // create new insert panel
        InsertButtonPanel insertPanel = new InsertButtonPanel();
        insertPanel.getAdminInsertButton().addActionListener(e -> {
            MainFrame.getInstance().goTo(config.STAFF_INSERT_NAME);
            ((StaffInsertController) MainFrame.getInstance().getController(config.STAFF_INSERT_NAME)).setRole(0);
        });
        insertPanel.getCoachInsertButton().addActionListener(e -> {
            MainFrame.getInstance().goTo(config.STAFF_INSERT_NAME);
            ((StaffInsertController) MainFrame.getInstance().getController(config.STAFF_INSERT_NAME)).setRole(1);
        });
        staffManagePanel.getInfoPanel().add(insertPanel);

        // create new client panels
        staffManagePanel.setClients(search_clients());
        staffManagePanel.getClients().forEach(client -> staffManagePanel.getPersonMap().put(client.getId(), new PersonPanel(client)));
        staffManagePanel.getPersonMap().forEach((k, v) -> {
            staffManagePanel.getInfoPanel().add(v);
            v.getDeleteButton().addActionListener(new PersonPanelListener(k, v));
            v.getDetailButton().addActionListener(new PersonPanelListener(k, v));
        });
        int client_num = staffManagePanel.getClients().size();
        if (client_num < 5) {
            // use blank panel to occupy space
            for (int i = 0; i < 5 - client_num; i++) {
                staffManagePanel.getInfoPanel().add(new PersonPanel());
            }
        }
        staffManagePanel.updateUI();
        if (client_num == 0)
            Util.showDialog(staffManagePanel, "No qualified clients were found!");
    }

    /**
     * This method is used to refresh the {@link StaffManagePanel} page
     */
    @Override
    public void update() {
        search_reset();
        showClientInfo();
    }

    /**
     * This listener is designed for {@link StaffManagePanel}
     * and is used to search for qualified clients.
     */
    class StaffManageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // add search listener
            if (e.getSource() == staffManagePanel.getSearchButton()) {
                showClientInfo();
                staffManagePanel.updateUI();
            }
            // add reset listener
            if (e.getSource() == staffManagePanel.getResetButton()) {
                search_reset();
            }
        }
    }

    /**
     * This listener is designed for {@link PersonPanel} and is used to modify or delete the chose client
     */
    class PersonPanelListener implements ActionListener {
        private final int k;
        private final PersonPanel v;

        public PersonPanelListener(int k, PersonPanel v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == v.getDeleteButton()) {
                Object[] buttonName = {"Confirm", "Cancel"};
                int result;
                int flag = 0;
                if (k == MainFrame.getInstance().getClient().getId()){
                    flag = 1;
                    result = JOptionPane.showOptionDialog(staffManagePanel,
                            "Are you sure to delete yourself?\n You will sign out if execute this instruction.",
                            "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                }
                else {
                    result = JOptionPane.showOptionDialog(staffManagePanel,
                            "Are you sure to delete this client?\n ",
                            "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                }

                // confirm to delete
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if (ClientMapping.cancel(k) == ClientMapping.SUCCESS) {
                            Util.showDialog(staffManagePanel, "Delete Success! ");
                            if (flag == 0) {
                                update();
                            }
                            else {
                                MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
                            }

                        } else
                            Util.showDialog(staffManagePanel, "Error! \n     Delete failed !");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Util.showDialog(staffManagePanel, "Error! \n     Delete failed !");
                    }
                }
            }
            // show the detail information of client
            else if (e.getSource() == v.getDetailButton()) {
                ClientDetailDialog clientDetail = new ClientDetailDialog(v.getClient());

                // modify client level
                clientDetail.getModifyLevelButton().addActionListener(actionEvent -> {
                    Object[] buttonName = {"Confirm", "Cancel"};
                    int result = JOptionPane.showOptionDialog(staffManagePanel,
                            "Are you sure to modify level?",
                            "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                    if (result == JOptionPane.YES_OPTION) {
                        int newLevel = clientDetail.getLevel().getSelectedIndex()+1;
                        Client newClient = v.getClient();
                        if (v.getClient().getRole() == 1) {
                            ((Coach) newClient).setLevel(newLevel - 1);
                        } else {
                            ((User) newClient).setLevel(newLevel);
                            ((User) newClient).setLearningTime(newLevel * 100 - 100);
                        }
                        try {
                            if (ClientMapping.modify(newClient) == ClientMapping.SUCCESS) {
                                clientDetail.setBuildinformation(new BuildInformation(newClient));
                                Util.showDialog(clientDetail, "Modify Success! ");
                                update();
                            } else {
                                Util.showDialog(clientDetail, "Error! \n     Modify failed !");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            Util.showDialog(clientDetail, "Error! \n     Modify failed !");
                        }
                    }
                });

                // modify client VIP level
                clientDetail.getModifyVIPButton().addActionListener(actionEvent -> {
                    Object[] buttonName = {"Confirm", "Cancel"};
                    int result = JOptionPane.showOptionDialog(staffManagePanel,
                            "Are you sure to modify VIP level?",
                            "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                    if (result == JOptionPane.YES_OPTION) {
                        String newVIPLevel = (String) clientDetail.getVIPLevel().getSelectedItem();
                        User newUser = (User) v.getClient();
                        newUser.setVip(newVIPLevel);
                        try {
                            if (ClientMapping.modify(newUser) == ClientMapping.SUCCESS) {
                                clientDetail.setBuildinformation(new BuildInformation(newUser));
                                Util.showDialog(clientDetail, "Modify Success! ");
                                update();
                            } else {
                                Util.showDialog(clientDetail, "Error! \n     Modify failed !");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            Util.showDialog(clientDetail, "Error! \n     Modify failed !");
                        }
                    }
                });
                clientDetail.setVisible(true);
            }
        }
    }
}
