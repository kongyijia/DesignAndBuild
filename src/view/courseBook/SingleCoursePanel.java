package view.courseBook;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to display the course type.
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class SingleCoursePanel extends JPanel {
    private String courseName;
    private JLabel nameLabel;

    public SingleCoursePanel(String courseName){
        this.courseName = courseName;

        initialize();
    }

    public SingleCoursePanel(){
        this.setPreferredSize(new Dimension(280,180));
        this.setBackground(Color.white);
    }

    private void initialize(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(280,180));

        nameLabel = new JLabel(courseName, JLabel.CENTER);
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        nameLabel.setBounds(40,100,200,30);
        this.add(nameLabel);
    }

}
