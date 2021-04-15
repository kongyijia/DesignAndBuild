package view.staffManagement;

import model.Client;
import util.config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class StaffManagePanel extends JPanel {
    public static final int SEARCH_PANEL_HEIGHT = 50;

    private JPanel searchPanel;
    private JPanel infoPanel;
    private JScrollPane scrollPane;
    private HashMap<Integer, PersonPanel> personMap = new HashMap<>();
    private ArrayList<Client> clients = new ArrayList<>();

    // searchPanel components
    private HashMap<String, PeopleSearchComponent> searchComboBoxMap = new HashMap<>();
    private JTextField searchInputField;
    private JButton searchButton;
    private JButton resetButton;

    public StaffManagePanel() {
        this.setLayout(null);

        init_searchPanel();
        init_infoPanel();
    }

    private void init_searchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBackground(Color.white);
        searchPanel.setBounds(0, 0, config.PAGE_WIDTH, SEARCH_PANEL_HEIGHT);

        searchInputField = new JTextField();
        searchInputField.setBounds(30, 10, 200, SEARCH_PANEL_HEIGHT - 20);

        searchComboBoxMap.put("role", new PeopleSearchComponent("Type", new String[]{"All", "Admin", "Coach", "User"}, searchComboBoxMap.size()));
        searchComboBoxMap.put("sex", new PeopleSearchComponent("Sex", new String[]{"All", "male", "female"}, searchComboBoxMap.size()));
        searchComboBoxMap.put("level", new PeopleSearchComponent("Level", new String[]{"All", "0", "1", "2"}, searchComboBoxMap.size()));

        searchButton = new JButton("Search");
        searchButton.setBounds(config.PAGE_WIDTH - 100, 10, 80, SEARCH_PANEL_HEIGHT - 20);

        resetButton = new JButton("Reset");
        resetButton.setBounds(config.PAGE_WIDTH - 200, 10, 80, SEARCH_PANEL_HEIGHT - 20);

        searchPanel.add(searchInputField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);
        searchComboBoxMap.forEach((k, v) -> {
            searchPanel.add(v.getLabel());
            searchPanel.add(v.getComboBox());
        });
        this.add(searchPanel);
    }

    private void init_infoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 4, 10, 10));
        infoPanel.setBackground(Color.white);

        scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBounds(0, SEARCH_PANEL_HEIGHT, config.PAGE_WIDTH - 14, config.PAGE_HEIGHT - SEARCH_PANEL_HEIGHT - 15);

        this.add(scrollPane);
    }

    public void addListener(ActionListener actionListener) {
        this.searchButton.addActionListener(actionListener);
        this.resetButton.addActionListener(actionListener);
    }

    public HashMap<Integer, PersonPanel> getPersonMap() {
        return personMap;
    }

    public HashMap<String, PeopleSearchComponent> getSearchComboBoxMap() {
        return searchComboBoxMap;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setPersonMap(HashMap<Integer, PersonPanel> personMap) {
        this.personMap = personMap;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public JTextField getSearchInputField() {
        return searchInputField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
