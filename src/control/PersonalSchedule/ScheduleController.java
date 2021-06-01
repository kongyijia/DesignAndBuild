package control.PersonalSchedule;

import control.Controller;
import control.MainFrame;
import model.Client;
import model.Course;
import model.User;
import model.mapping.ClientMapping;
import model.mapping.CourseMapping;
import util.Util;
import util.config;
import view.Schedule.Schedule2;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Calendar;

/**
 *  This class is inherited from {@link Controller}
 *  <br>
 *  This class mainly focus on the process of switching the page, delete the course and update the data in json.
 *  <br>
 *  @author Xinyu Zhou
 *  @version 2.0
 *  @since 2021/5/12
 */
public class ScheduleController extends Controller {
    public static final int LABELWIDTH=120;
    public static final int LABELHIGHT=20;
    public static final int BUTTONWIDTH=60;
    public static final int BUTTONHIGHT=200;
    public static final int PANELWIDTH=130;
    public static final int PANELHIGHT=90;
    public static final int PANELGAP=10;
    public static final int GAP=20;
    private static Schedule2 schedule2;

    public static Client client= MainFrame.getInstance().getClient();
    public static int today;

    private static int offsetOfPage =0;
    public static JPanel[][] classPanelArray=new JPanel[4][7];
    public static JLabel[] dayLabel =new JLabel[7];

    public ScheduleController() throws FileNotFoundException {
        super(config.SCHEDULE_NAME,new Schedule2());
        schedule2 = (Schedule2) this.panel;
        update();
        schedule2.addaddListener(e->{
            if(e.getSource()== schedule2.getNext()){
                client= MainFrame.getInstance().getClient();
                setOffsetOfPage(getOffsetOfPage()+1);
                schedule2.setDate(generateDate());
                try {
                    schedule2.setSchedulePanel(generateSchedule());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            if(e.getSource()== schedule2.getLast()){
                client= MainFrame.getInstance().getClient();
                setOffsetOfPage(getOffsetOfPage()-1);
                schedule2.setDate(generateDate());
                try {
                    schedule2.setSchedulePanel(generateSchedule());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });


    }
    /**
     *
     * Update all components.
     * @return void
     * @author Xinyu Zhou
     * @date 2021/5/28
     * @version V2.0
     */

    @Override
    public void update() {
        client= MainFrame.getInstance().getClient();
        setOffsetOfPage(0);
        schedule2.setDate(generateDate());
        try {
            schedule2.setSchedulePanel(generateSchedule());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setOffsetOfPage(int num) {
        offsetOfPage=num;
    }
    public static JPanel getSchedule(int day, int slot){
        return classPanelArray[day][slot];
    }
    public int getOffsetOfPage() {
        return offsetOfPage;
    }
    /**
     * generate the JPanel which contains 28 courses.
     * @return JPanel
     * @author Xinyu Zhou
     * @date 2021/5/12
     * @version V1.0
     */

    public static JPanel generateSchedule() throws FileNotFoundException {
        JPanel schedulePanel=new JPanel();
        generateClass();
        for(int i=0;i<4;i++){
            for(int j=0; j<7; j++){
                schedulePanel.add(getSchedule(i,j));
            }
        }
        return schedulePanel;
    }
    /**
     * generate 28 courses JPanels and add them to classPanelArray.
     * @return void
     * @author Xinyu Zhou
     * @date 2021/5/15
     * @version V1.2
     */
    public static void generateClass() throws FileNotFoundException {

        for (int i=0;i<4;i++) {
            for (int j = 0; j < 7; j++) {
                classPanelArray[i][j]=new JPanel();
                classPanelArray[i][j].setLayout(null);
                classPanelArray[i][j].setBackground(Color.decode("#EAEAEA"));
                classPanelArray[i][j].setBounds(j * (PANELGAP + PANELWIDTH), i * (PANELGAP + PANELHIGHT), PANELWIDTH, PANELHIGHT);
                setSchedule(i,j,generateCoursePanel(j,i));
            }
        }
    }

    /**
     * generate a specific course JPanel.
     * @param i the row of the course panel.
     * @param j the column of the course panel.
     * @return JPanel
     * @author Xinyu Zhou
     * @date 2021/5/28
     * @version V2.0
     */
    public static JPanel generateCoursePanel(int j, int i) throws FileNotFoundException {
        JLabel l1=new JLabel("");
        JLabel l2=new JLabel("");
        JLabel l3=new JLabel("");
        JLabel l4=new JLabel("");
        JPanel p=new JPanel();
        JButton button = new JButton("delete");
        button.addActionListener(e ->{
            Object[] buttonName = {"Confirm", "Cancel"};
            int result = JOptionPane.showOptionDialog(null,
                    "Are you sure to delete this course?\n ",
                    "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonName, buttonName);
            if (result == JOptionPane.YES_OPTION) {
                try {
                   User user = (User) client;
                   user.setAccount(user.getAccount()+findCourse(dayLabel[j].getText(),i).get(0).getPrice());
                    int returnValue = ClientMapping.modify(user);
                    if (returnValue == ClientMapping.SUCCESS) {
                        Util.showDialog(null,"Delete success");
                    }
                    CourseMapping.delete(findCourse(dayLabel[j].getText(),i).get(0).getId());
                    MainFrame.getInstance().setClient(user.getId());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    schedule2.setSchedulePanel(generateSchedule());
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }

            }
        });
        button.setBounds(0, 65,90,20);
        if(!getTelenum(dayLabel[j].getText(),i).isEmpty()) {
            l1.setText(getTelenum(dayLabel[j].getText(), i).get(0));//get string
            l1.setBounds(0, 0, 120, 10);
            l2.setText(getTelenum(dayLabel[j].getText(), i).get(1));//get string
            l2.setBounds(0, 15, 120, 10);
            l3.setText(getTelenum(dayLabel[j].getText(), i).get(2));//get string
            l3.setBounds(0, 35, 120, 10);
            l4.setText(getTelenum(dayLabel[j].getText(), i).get(3));//get string
            l4.setBounds(0, 50, 120, 10);
            try {
                if(isfuture(dayLabel[j].getText())&&client.getRole()==2)
                    p.add(button);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            p.setBackground(Color.decode("#B4EEB4"));
        }else{
            p.setBackground(Color.decode("#E0FFFF"));
        }
        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        return p;
    }
    /**
     * judge the date  is after today
     * @param date The date to be judged
     * @return boolean
     * @author Xinyu Zhou
     * @date 2021/5/28
     * @version V2.0
     */
    private static boolean isfuture(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sd1=df.parse(df.format(Calendar.getInstance().getTime()));
        Date sd2=df.parse(date);

        return sd1.before(sd2);
    }
    /**
     * update all the course JPanels in the classPanelArray.
     * @param slot the row of the course JPanel.
     * @param day The column of the course JPanel.
     * @param course the course Panel with user and coach information.
     * @return void
     * @author Xinyu Zhou
     * @date 2021/5/28
     * @version V2.0
     */
    public static void setSchedule(int slot, int day, JPanel course){
        classPanelArray[slot][day].removeAll();
        classPanelArray[slot][day].add(course);
        course.setBounds(0,0,PANELWIDTH,PANELWIDTH);
        course.setLayout(null);
    }
    /**
     * generate the JPanel for the date on the top of the schedule.
     * @return JPanel
     * @author Xinyu Zhou
     * @date 2021/5/12
     * @version V1.0
     */
    public static JPanel generateDate(){
        JPanel date= new JPanel();
        Calendar c = Calendar.getInstance();
        String []dayOfWeek= new String[7];
        today= getWeek(c,dayOfWeek);
        for(int i=0;i<7;i++){
            dayLabel[i]=new JLabel(dayOfWeek[i]);
            if(today==i&&offsetOfPage==0)
                dayLabel[i].setForeground(Color.red);
            dayLabel[i].setBounds((i+4)*GAP+(i+1)*LABELWIDTH,0,LABELWIDTH,LABELHIGHT);
            date.add(dayLabel[i]);
        }
        return date;
    }
    /**
     * update the date information in the date array and return the location of today's date.
     * @param c Calendar.
     * @param dayOfWeek a string array which contain 7 dates for the date JPanel.
     * @return int
     * @author Xinyu Zhou
     * @date 2021/5/12
     * @version V1.0
     */
    public static int getWeek(Calendar c, String[] dayOfWeek){
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        // c.add(Calendar.DAY_OF_YEAR,7*offsetOfPage);
        int today=-1;
        if(c.get(Calendar.DAY_OF_WEEK)-2==-1)//today is Sunday
            today=6;
        else //e.g. today=0 when today is Monday
            today=c.get(Calendar.DAY_OF_WEEK)-2;

        c.add(Calendar.DAY_OF_YEAR,-1-today+offsetOfPage*7);
        for(int i=0;i<7;i++){
            c.add(Calendar.DAY_OF_YEAR,1);
            dayOfWeek[i]=f.format(c.getTime());
        }
        return today;
    }
    /**
     * check the course.json to find the course match the login client role and the time of the course.
     * @param date the date of the course.
     * @param slot the row of the course JPanel.
     * @return ArrayList<Course>
     * @author Xinyu Zhou
     * @date 2021/5/12
     * @version V1.0
     */

    public static ArrayList<Course> findCourse(String date, int slot) throws FileNotFoundException {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<Course> course=new ArrayList<Course>();

        map.put("date",date);
        switch(slot) {
            case 0:
                map.put("start","09:00:00");
                break;
            case 1:
                map.put("start","13:00:00");
                break;
            case 2:
                map.put("start","16:00:00");
                break;
            case 3:
                map.put("start","19:00:00");
                break;
        }
        if(client.getRole()==1)
            map.put("coachId",client.getId()+"");
        if(client.getRole()==2)
            map.put("userId",client.getId()+"");
        course= CourseMapping.find(map);
        return course;
    }
    /**
     * get the nickname and telephone number information for the coach and student in one course.
     * @param date the date of the course.
     * @param slot the row of the course JPanel.
     * @return ArrayList<String>
     * @author Xinyu Zhou
     * @date 2021/5/12
     * @version V1.0
     */
    public static ArrayList<String> getTelenum(String date, int slot) throws FileNotFoundException {
        ArrayList<Course> course=new ArrayList<Course>();
        ArrayList<String> info=new ArrayList<String>();
        course=findCourse(date,slot);
        HashMap<String, String> condition1 = new HashMap<>();//find coach
        HashMap<String, String> condition2 = new HashMap<>();//find user
        if(!course.isEmpty()) {
            condition1.put("id", course.get(0).getCoachId()+"");
            condition2.put("id",course.get(0).getUserId()+"");

            info.add("coach:"+ ClientMapping.find(condition1).get(0).getNickName());
            info.add(ClientMapping.find(condition1).get(0).getPhone());
            info.add("user:"+ClientMapping.find(condition2).get(0).getNickName());
            info.add(ClientMapping.find(condition2).get(0).getPhone());
        }
        return info;
    }

}
