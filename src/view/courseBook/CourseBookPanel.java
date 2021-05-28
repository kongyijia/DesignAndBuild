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
        infoPanel.setBackground(Color.lightGray);

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
}
