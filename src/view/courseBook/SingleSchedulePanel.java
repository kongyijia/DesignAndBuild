package view.courseBook;


import model.CourseInfo;

import javax.swing.*;
import java.awt.*;

public class SingleSchedulePanel extends JPanel {
    private boolean isAvailable;

    public SingleSchedulePanel(CourseInfo courseInfo){
        this.isAvailable = false;
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        JLabel userLabel = new JLabel("User: " + courseInfo.getUser().getNickName(), JLabel.LEFT);
        userLabel.setBounds(5,10,130,20);
        userLabel.setBackground(Color.cyan);

        JLabel coachLabel = new JLabel("Coach: " + courseInfo.getCoach().getNickName(), JLabel.LEFT);
        coachLabel.setBounds(5,30,130,20);

        JLabel typeLabel = new JLabel("Type: " + courseInfo.getCourse().getType(), JLabel.LEFT);
        typeLabel.setBounds(5,50,130,20);

        this.add(userLabel);
        this.add(coachLabel);
        this.add(typeLabel);
    }

    public SingleSchedulePanel(Boolean isAvailable){
        this.isAvailable = isAvailable;
        this.setLayout(new BorderLayout());
        if (isAvailable){
            this.setBackground(Color.decode("#B4EEB4"));
        }
        else {
            this.add(new JLabel("Already booked", JLabel.CENTER), BorderLayout.CENTER);
            this.setBackground(Color.lightGray);
        }
    }
}
