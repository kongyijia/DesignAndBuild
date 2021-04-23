package view.staffManagement;

import javax.swing.*;
import java.awt.*;

/**
 * Controller of watching record management for administrators.
 *
 * @author Shengbo Wang
 * @version 1.2
 * @see RecordManagePanel
 * @since 19 April 2021
 */
public class RecordManagePanel extends JPanel{
    public JTextArea filterText;
    public JButton filterButton, filterReset;

    public RecordManagePanel (){
        this.setLayout(null);
        filterText = new JTextArea();
        filterText.setBounds(100,0,100,20);
        this.add(filterText);
        filterButton = new JButton("Filter");
        filterButton.setBounds(500,0,100,20);
        this.add(filterButton);
        filterReset = new JButton("Reset");
        filterReset.setBounds(700,0,100,20);
        this.add(filterReset);
    }
}
