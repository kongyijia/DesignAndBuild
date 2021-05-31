package view.courseBook;

import util.config;
import view.staffManagement.PeopleSearchComponent;
import view.staffManagement.PersonPanel;
import view.staffManagement.StaffManagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * @description This class is used for users to select courses, mainly used to select coach.
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class CoachBookPanel extends JPanel {
    private static final int INFO_PANEL_HEIGHT = 50;

    private JPanel dataPanel;
    private JPanel infoPanel;
    private JScrollPane scrollPane;

    private HashMap<Integer, PersonPanel> personMap = new HashMap<>();
    // searchPanel components
    private HashMap<String, PeopleSearchComponent> searchComboBoxMap = new HashMap<>();
    private JTextField searchInputField;
    private JButton searchButton;
    private JButton resetButton;
    private JButton backButton;

    public CoachBookPanel(){
        initialize();
    }

    private void initialize() {
        this.setLayout(null);

        init_infoPanel();
        init_dataPanel();
    }

    private void init_infoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(0,0, config.PAGE_WIDTH, INFO_PANEL_HEIGHT);
        infoPanel.setBackground(Color.lightGray);

        searchInputField = new JTextField();
        searchInputField.setBounds(30, 10, 200, INFO_PANEL_HEIGHT - 20);

        searchComboBoxMap.put("sex", new PeopleSearchComponent("Sex", new String[]{"All", "male", "female"}, searchComboBoxMap.size()));
        searchComboBoxMap.put("level", new PeopleSearchComponent("Level", new String[]{"All", "0", "1", "2"}, searchComboBoxMap.size()));

        backButton = new JButton("Back");
        backButton.setBounds(config.PAGE_WIDTH - 100, 10, 80, INFO_PANEL_HEIGHT - 20);
        backButton.setBackground(Color.white);

        searchButton = new JButton("Search");
        searchButton.setBounds(config.PAGE_WIDTH - 500, 10, 80, INFO_PANEL_HEIGHT - 20);
        searchButton.setBackground(Color.white);

        resetButton = new JButton("Reset");
        resetButton.setBounds(config.PAGE_WIDTH - 600, 10, 80, INFO_PANEL_HEIGHT - 20);
        resetButton.setBackground(Color.white);

        JLabel jLabel = new JLabel("Double click to choose one coach!");
        jLabel.setBounds(800,10,300,INFO_PANEL_HEIGHT -20);

        infoPanel.add(searchInputField);
        infoPanel.add(backButton);
        infoPanel.add(searchButton);
        infoPanel.add(resetButton);
        infoPanel.add(jLabel);
        searchComboBoxMap.forEach((k, v) -> {
            infoPanel.add(v.getLabel());
            infoPanel.add(v.getComboBox());
        });

        this.add(infoPanel);
    }

    private void init_dataPanel() {
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(0, 4, 10, 10));
        dataPanel.setBackground(Color.white);

        scrollPane = new JScrollPane(dataPanel);
        scrollPane.setBounds(0, INFO_PANEL_HEIGHT, config.PAGE_WIDTH, config.PAGE_HEIGHT - INFO_PANEL_HEIGHT);

        this.add(scrollPane);
    }

    public JPanel getDataPanel() {
        return dataPanel;
    }

    public HashMap<Integer, PersonPanel> getPersonMap() {
        return personMap;
    }

    public void setPersonMap(HashMap<Integer, PersonPanel> personMap) {
        this.personMap = personMap;
    }

    public HashMap<String, PeopleSearchComponent> getSearchComboBoxMap() {
        return searchComboBoxMap;
    }

    public JTextField getSearchInputField() {
        return searchInputField;
    }

    public void addListener(ActionListener actionListener){
        this.resetButton.addActionListener(actionListener);
        this.backButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
