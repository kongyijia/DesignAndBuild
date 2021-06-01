package view.courseBook;


import model.CourseInfo;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * This class is used to display the information of a single course schedule.
 *
 * If there is a course, the coach, type and price of the course will be displayed.
 * If there is no course, it will be blank.
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class SingleSchedulePanel extends JPanel {
    private boolean isAvailable;

    public SingleSchedulePanel(CourseInfo courseInfo){
        this.isAvailable = false;
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        JLabel coachLabel = new JLabel("Coach: " + courseInfo.getCoach().getNickName(), JLabel.LEFT);
        coachLabel.setBounds(5,10,130,20);

        JLabel typeLabel = new JLabel("Type: " + courseInfo.getCourse().getType(), JLabel.LEFT);
        typeLabel.setBounds(5,30,130,20);

        JLabel priceLabel = new JLabel("Price:" + new DecimalFormat("#.00").format(courseInfo.getCourse().getPrice()), JLabel.LEFT);
        priceLabel.setBounds(5,50,130,20);

        this.add(coachLabel);
        this.add(typeLabel);
        this.add(priceLabel);
    }

    public SingleSchedulePanel(Boolean isAvailable){
        this.isAvailable = isAvailable;
        this.setLayout(new BorderLayout());
        if (isAvailable){
            this.setBackground(Color.decode("#B4EEB4"));
        }
        else {
            this.add(new JLabel("Already booked", JLabel.CENTER), BorderLayout.CENTER);
            this.setBackground(Color.red);
        }
    }
}
