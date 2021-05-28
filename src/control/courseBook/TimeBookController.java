package control.courseBook;

import control.Controller;
import control.MainFrame;
import model.Course;
import model.CourseInfo;
import model.mapping.CourseMapping;
import util.Util;
import util.config;
import view.courseBook.SingleSchedulePanel;
import view.courseBook.TimeBookPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeBookController extends Controller {
    public static final String[] START_TIMES = new String[]{"09:00:00", "13:00:00", "16:00:00", "19:00:00"};

    private TimeBookPanel timeBookPanel;
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

    @Override
    public void update() {
        timeBookPanel.showDate(dayOffset = 0);
        showScheduleInfo();

        timeBookPanel.updateUI();
    }

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
                }
                timeBookPanel.getSchedulePanel().add(panel);
            }
        }
    }

    class CourseBookMouseAdapter extends MouseAdapter {
        private int i;
        private int j;

        public CourseBookMouseAdapter(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (e.getClickCount() == 2) {
                Object[] buttonName = {"Confirm", "Cancel"};
                int result = JOptionPane.showOptionDialog(timeBookPanel,
                        "Are you sure to book this course?\n ",
                        "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        int userID = MainFrame.getInstance().getClient().getId();

                        CoachBookController coachController = (CoachBookController) MainFrame.getInstance().getController(config.COURSE_BOOK_COACH_NAME);
                        int coachID = coachController.getCoachID();

                        CourseBookController courseController = (CourseBookController) MainFrame.getInstance().getController(config.COURSE_BOOK_NAME);
                        String type = courseController.getCourseType();

                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeBookPanel.getDateLabels().get(j).getText());
                        Time start = Util.strToTime(START_TIMES[i]);

                        Course course = new Course(new Random().nextInt(Integer.MAX_VALUE), date, start, userID, coachID, type);

                        int state = CourseMapping.add(course);
                        while (state == CourseMapping.DUPLICATE_ID) {
                            course.setId(new Random().nextInt(Integer.MAX_VALUE));
                            state = CourseMapping.add(course);
                        }
                        if (state == CourseMapping.SUCCESS) {
                            Util.showDialog(timeBookPanel, "Book Success! ");
                            update();
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
