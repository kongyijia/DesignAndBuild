package view.staffManagement;

import javax.swing.*;

public class PeopleSearchComponent {
    private String name;
    private JLabel label;
    private JComboBox<String> comboBox;

    public PeopleSearchComponent(String name, String[] objects, int index){
        int X = 250;
        int Y = 10;
        int HEIGHT = 30;
        int LABEL_WIDTH = 50;
        int WIDTH = 150;

        this.name = name;

        label = new JLabel(name + " :");
        label.setBounds(X + index * WIDTH, Y, LABEL_WIDTH, HEIGHT);

        comboBox = new JComboBox<String>(objects);
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(X + LABEL_WIDTH+ index * WIDTH, Y, WIDTH - LABEL_WIDTH - 10, HEIGHT);
    }

    public String getName() {
        return name;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JLabel getLabel() {
        return label;
    }
}
