package view.staffManagement;

import javax.swing.*;
import java.awt.*;

/**
 *  This is the panel which has creating new admin and coach button.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public class InsertButtonPanel extends JPanel {
    private JButton adminInsertButton;
    private JButton coachInsertButton;

    public InsertButtonPanel() {
        initialize();
    }

    private void initialize() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(280, 180));

        adminInsertButton = new JButton("Create New Admin");
        adminInsertButton.setBounds(40, 40, 200, 30);
        adminInsertButton.setBackground(Color.white);

        coachInsertButton = new JButton("Create New Coach");
        coachInsertButton.setBounds(40, 100, 200, 30);
        coachInsertButton.setBackground(Color.white);

        this.add(adminInsertButton);
        this.add(coachInsertButton);
    }

    public JButton getAdminInsertButton() {
        return adminInsertButton;
    }

    public JButton getCoachInsertButton() {
        return coachInsertButton;
    }
}
