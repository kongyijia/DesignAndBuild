package control.courseBook;

import control.Controller;
import control.MainFrame;
import model.*;
import model.mapping.ClientMapping;
import model.mapping.CourseMapping;
import util.Util;
import util.config;
import view.courseBook.SingleSchedulePanel;
import view.courseBook.TimeBookPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description This class is used to control view display and data interaction of {@link TimeBookPanel}
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class TimeBookController extends Controller {
    public static final String[] START_TIMES = new String[]{"09:00:00", "13:00:00", "16:00:00", "19:00:00"};

    private final TimeBookPanel timeBookPanel;
    private int dayOffset = 0;

    public TimeBookController() {
        super(config.COURSE_BOOK_TIME_NAME, new TimeBookPanel());
        timeBookPanel = (TimeBookPanel) this.panel;

        timeBookPanel.getBackButton().addActionListener(e -> MainFrame.getInstance().goTo(config.COURSE_BOOK_COACH_NAME));
        timeBookPanel.getNextButton().addActionListener(e -> {
            timeBookPanel.showDate(++dayOffset);
            showScheduleInfo();
        });
        timeBookPanel.getLastButton().addActionListener(e -> {
            timeBookPanel.showDate(--dayOffset);
            showScheduleInfo();
        });

        update();
    }

    /**
     * This method is used to refresh the {@link TimeBookPanel} page
     */
    @Override
    public void update() {
        timeBookPanel.showDate(dayOffset = 0);
        showScheduleInfo();

        timeBookPanel.updateUI();
    }

    /**
     * This method will use {@link SingleSchedulePanel} to display qualified coach information in {@link TimeBookPanel} page
     */
    private void showScheduleInfo() {
        timeBookPanel.getSchedulePanel().removeAll();
        SingleSchedulePanel[][] schedules = new SingleSchedulePanel[4][7];
        Calendar calendar = Calendar.getInstance();
        Date today = Util.strToDate(String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)));

        HashMap<String, String> searchUserScheduleMap = new HashMap<>();
        searchUserScheduleMap.put("userId", Integer.toString(MainFrame.getInstance().getClient().getId()));

        HashMap<String, String> searchCoachScheduleMap = new HashMap<>();
        CoachBookController coachController = (CoachBookController) MainFrame.getInstance().getController(config.COURSE_BOOK_COACH_NAME);
        searchCoachScheduleMap.put("coachId", Integer.toString(coachController.getCoachID()));

        try {
            ArrayList<Course> courses = CourseMapping.find(searchUserScheduleMap);
            ArrayList<JLabel> dates = timeBookPanel.getDateLabels();
            courses.forEach(course -> {
                for (int i = 0; i < 4; i++) {
                    if (Util.strToTime(START_TIMES[i]).equals(course.getStart())) {
                        for (int j = 0; j < 7; j++) {
                            if (dates.get(j).getText().equals(new SimpleDateFormat("yyyy-MM-dd").format(course.getDate()))) {
                                CourseInfo courseInfo = CourseInfo.generateCourseInfo(course);
                                schedules[i][j] = new SingleSchedulePanel(courseInfo);
                                break;
                            }
                        }
                    }
                }
            });

            courses = CourseMapping.find(searchCoachScheduleMap);
            courses.forEach(course -> {
                for (int i = 0; i < 4; i++) {
                    if (Util.strToTime(START_TIMES[i]).equals(course.getStart())) {
                        for (int j = 0; j < 7; j++) {
                            if (dates.get(j).getText().equals(new SimpleDateFormat("yyyy-MM-dd").format(course.getDate()))) {
                                if (schedules[i][j] == null)
                                    schedules[i][j] = new SingleSchedulePanel(false);
                                break;
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                SingleSchedulePanel panel = schedules[i][j];
                if (panel == null) {
                    panel = new SingleSchedulePanel(true);
                    schedules[i][j] = panel;
                    if (dayOffset >= 0 && today.compareTo(Util.strToDate(timeBookPanel.getDateLabels().get(j).getText())) <= 0)
                        panel.addMouseListener(new CourseBookMouseAdapter(i, j));
                    else
                        panel.setBackground(Color.lightGray);
                }
                timeBookPanel.getSchedulePanel().add(panel);
            }
        }
    }

    /**
     * This listener is designed for {@link SingleSchedulePanel} and is used to select the course you want to book
     */
    class CourseBookMouseAdapter extends MouseAdapter {
        private final int i;
        private final int j;

        public CourseBookMouseAdapter(int i, int j) {
            this.i = i;
            this.j = j;
        }

        /**
         * Double click to select the course you want to book
         *
         * @param e MouseEvent
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (e.getClickCount() == 2) {
                User user = (User) MainFrame.getInstance().getClient();
                Coach coach;
                double coursePrice;

                try {
                    CoachBookController coachController = (CoachBookController) MainFrame.getInstance().getController(config.COURSE_BOOK_COACH_NAME);
                    int coachID = coachController.getCoachID();
                    HashMap<String, String> coachSearch = new HashMap<>();
                    coachSearch.put("id", Integer.toString(coachID));
                    coach = ClientMapping.findCoach(coachSearch).get(0);

                    double discount = 1;
                    if ( user.getVip().equals("Premium") || user.getVip().equals("Course") )
                        discount = 0.8;
                    coursePrice = Coach.level2price(coach) * discount;
                } catch (Exception exception) {
                    Util.showDialog(timeBookPanel, "Error! \n     Book failed !");
                    exception.printStackTrace();
                    return;
                }

                Object[] buttonName = {"Confirm", "Cancel"};
                int result = JOptionPane.showOptionDialog(timeBookPanel,
                        "Are you sure to book this course?\n You need to pay " + new DecimalFormat("#.00").format(coursePrice),
                        "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if (user.getAccount() < coursePrice){
                            Util.showDialog(timeBookPanel, "Error! \n     Book failed: Insufficient balance! ");
                            return;
                        }
                        int userID = user.getId();
                        int coachID = coach.getId();

                        CourseBookController courseController = (CourseBookController) MainFrame.getInstance().getController(config.COURSE_BOOK_NAME);
                        String type = courseController.getCourseType();

                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeBookPanel.getDateLabels().get(j).getText());
                        Time start = Util.strToTime(START_TIMES[i]);

                        Course course = new Course(new Random().nextInt(Integer.MAX_VALUE), date, start, userID, coachID, type, coursePrice);

                        int state = CourseMapping.add(course);
                        while (state == CourseMapping.DUPLICATE_ID) {
                            course.setId(new Random().nextInt(Integer.MAX_VALUE));
                            state = CourseMapping.add(course);
                        }
                        if (state == CourseMapping.SUCCESS) {
                            user.setAccount(user.getAccount() - coursePrice);
                            ClientMapping.modify(user);
                            Util.showDialog(timeBookPanel, "Book Success! ");
                            update();
                            MainFrame.getInstance().goTo(config.VIDEOSQUARE_PANEL_NAME);
                        }
                        else
                            Util.showDialog(timeBookPanel, "Error! \n     Book failed !");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Util.showDialog(timeBookPanel, "Error! \n     Book failed !");
                    }
                }
            }
        }
    }
}
