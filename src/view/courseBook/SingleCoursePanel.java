package view.courseBook;

import javax.swing.*;
import java.awt.*;

public class SingleCoursePanel extends JPanel {
    private String courseName;
    private JLabel nameLabel;

    public SingleCoursePanel(String courseName){
        this.courseName = courseName;

        initialize();
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
