package view.courseBook;

import util.config;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CourseBookPanel extends JPanel {
    private static final int INFO_PANEL_HEIGHT = 50;

    private JScrollPane scrollPane;
    private JPanel infoPanel;
    private JPanel dataPanel;
    private HashMap<String, SingleCoursePanel> courseMap = new HashMap<>();

    private JTextField searchInputField;
    private JButton searchButton;
    private JButton resetButton;

    public CourseBookPanel(){
        initialize();
    }

    private void initialize() {
        this.setLayout(null);

        init_infoPanel();
        init_dataPanel();
    }

    private void init_infoPanel() {
        infoPanel = new JPanel();
        infoPanel.setBounds(0,0, config.PAGE_WIDTH, INFO_PANEL_HEIGHT);
        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.lightGray);

        searchInputField = new JTextField();
        searchInputField.setBounds(30, 10, 300, INFO_PANEL_HEIGHT - 20);

        resetButton = new JButton("Reset");
        resetButton.setBounds(350, 10, 80, INFO_PANEL_HEIGHT - 20);
        resetButton.setBackground(Color.white);

        searchButton = new JButton("Search");
        searchButton.setBounds(450, 10, 80, INFO_PANEL_HEIGHT - 20);
        searchButton.setBackground(Color.white);

        JLabel jLabel = new JLabel("Double click to choose one type of course!");
        jLabel.setBounds(600,10,300,INFO_PANEL_HEIGHT -20);

        infoPanel.add(searchInputField);
        infoPanel.add(searchButton);
        infoPanel.add(resetButton);
        infoPanel.add(jLabel);

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

    public HashMap<String, SingleCoursePanel> getCourseMap() {
        return courseMap;
    }

    public void setCourseMap(HashMap<String, SingleCoursePanel> courseMap) {
        this.courseMap = courseMap;
    }

    public JPanel getDataPanel() {
        return dataPanel;
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
}
