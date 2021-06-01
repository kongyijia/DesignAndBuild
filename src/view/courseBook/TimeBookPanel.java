package view.courseBook;

import util.config;
import view.basicComponents.ColorSquare;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class is used to display the course schedule in the course selection interface,
 * and the user can see the available courses and the non-selectable courses in each week
 *
 * This class is used for users to select courses, mainly used to select course schedule.
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class TimeBookPanel extends JPanel {
    private static final int INFO_PANEL_HEIGHT = 50;
    public static final int LABELWIDTH=120;
    public static final int LABELHIGHT=20;
    public static final int BUTTONWIDTH=60;
    public static final int BUTTONHIGHT=200;
    public static final int PANELWIDTH=130;
    public static final int PANELHIGHT=90;
    public static final int PANELGAP=10;
    public static final int GAP=20;
    public static final String[] WEEKDAYS = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    public static final String[] SLOTS = {"9: 00~11: 00","13: 00~15: 00","16: 00~18: 00","19: 00~21: 00"};

    private JButton nextButton;
    private JButton lastButton;
    private JButton backButton;
    private ArrayList<JLabel> weekLabels = new ArrayList<>();
    private ArrayList<JLabel> slots = new ArrayList<>();
    private ArrayList<JLabel> dateLabels = new ArrayList<>();

    private JPanel schedulePanel;
    private JPanel infoPanel;
    private JPanel hintPanel;

    public TimeBookPanel() {
        init();
    }

    private void init(){
        this.setLayout(null);

        schedulePanel_init();
        infoPanel_init();
        hintPanel_init();

        nextButton = new JButton("Next");
        nextButton.setBounds(1190 - BUTTONWIDTH,80,BUTTONWIDTH,PANELHIGHT * 4 + PANELGAP * 3);
        nextButton.setBackground(Color.decode("#EAEAEA"));

        lastButton = new JButton("Last");
        lastButton.setBounds(0,80,BUTTONWIDTH,PANELHIGHT*4+PANELGAP*3);
        lastButton.setBackground(Color.decode("#EAEAEA"));

        for (int i = 0; i < SLOTS.length; i++){
            JLabel label = new JLabel(SLOTS[i]);
            slots.add(label);
            label.setBounds(10 + 3 * GAP,80 + i * (PANELHIGHT + PANELGAP), PANELWIDTH, PANELHIGHT);
            this.add(label);
        }
        showDate(0);

        this.add(nextButton);
        this.add(lastButton);
    }

    private void infoPanel_init() {
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.lightGray);
        infoPanel.setBounds(0,0, config.PAGE_WIDTH, INFO_PANEL_HEIGHT);

        backButton = new JButton("Back");
        backButton.setBounds(config.PAGE_WIDTH - 100, 10, 80, INFO_PANEL_HEIGHT - 20);
        backButton.setBackground(Color.decode("#EAEAEA"));

        for (int i = 0; i < WEEKDAYS.length; i++){
            JLabel label = new JLabel(WEEKDAYS[i]);
            weekLabels.add(label);
            label.setBounds((4 + i) * GAP + (i + 1) * LABELWIDTH,15,LABELWIDTH,LABELHIGHT);
            infoPanel.add(label);
        }

        infoPanel.add(backButton);
        this.add(infoPanel);
    }

    private void schedulePanel_init() {
        schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(4,7,10,10));
        schedulePanel.setBounds(160,80,PANELWIDTH*7+PANELGAP*6,PANELHIGHT*4+PANELGAP*3);

        this.add(schedulePanel);
    }

    private void hintPanel_init() {
        hintPanel = new JPanel();
        hintPanel.setLayout(null);
        hintPanel.setBounds(0, 90 + PANELHIGHT * 4 + PANELGAP * 3, config.PAGE_WIDTH, config.PAGE_HEIGHT - PANELHIGHT * 4 + PANELGAP * 3);

        JLabel hintLabel = new JLabel("Hint: ");
        hintLabel.setBounds(180, 5, 50, 20);
        hintPanel.add(hintLabel);

        ColorSquare lightgraySquare = new ColorSquare(10, Color.lightGray);
        lightgraySquare.setBounds(230,10,10,10);
        hintPanel.add(lightgraySquare);

        JLabel lightgrayLabel = new JLabel("This course is not available and you cannot book it.");
        lightgrayLabel.setBounds(250, 5, 300, 20);
        hintPanel.add(lightgrayLabel);

        ColorSquare greenSquare = new ColorSquare(10, Color.decode("#B4EEB4"));
        greenSquare.setBounds(580,10,10,10);
        hintPanel.add(greenSquare);

        JLabel greenLabel = new JLabel("This course is available and double-click to book this course.");
        greenLabel.setBounds(610, 5, 350, 20);
        hintPanel.add(greenLabel);

        this.add(hintPanel);
    }

    /**
     * Used to determine the displayed date
     *
     * @param dayOffset Offset value of week number, the value of this week is 0,
     *                  the value of the previous week is -1,
     *                  and the value of the next week is 1
     */
    public void showDate(int dayOffset) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
            calendar.add(Calendar.DATE, -1);
        calendar.add(Calendar.DATE, 7 * dayOffset);
        for (int i = 0; i < 7; i++){
            JLabel label;
            if (dateLabels.size() != 7) {
                label = new JLabel(dateFormat.format(calendar.getTime()));
                label.setBounds((i + 4) * GAP + (i + 1) * LABELWIDTH, 50, LABELWIDTH, LABELHIGHT);
                dateLabels.add(label);
                this.add(label);
            }
            else {
                label = dateLabels.get(i);
                label.setText(dateFormat.format(calendar.getTime()));
            }
            if (calendar.getTime().equals(Calendar.getInstance().getTime()))
                label.setForeground(Color.red);
            else
                label.setForeground(Color.black);
            calendar.add(Calendar.DATE, 1);
        }
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getLastButton() {
        return lastButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public ArrayList<JLabel> getDateLabels() {
        return dateLabels;
    }

    public JPanel getSchedulePanel() {
        return schedulePanel;
    }

}
