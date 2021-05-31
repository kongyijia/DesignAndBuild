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
        schedule2 = (Schedule2) this.panel;    // 强制转换为当前控制器对应的页面类型
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
            l1.setText(getTelenum(dayLabel[j].getText(), i).get(0));//调取接口获取string
            l1.setBounds(0, 0, 120, 10);
            l2.setText(getTelenum(dayLabel[j].getText(), i).get(1));//调取接口获取string
            l2.setBounds(0, 15, 120, 10);
            l3.setText(getTelenum(dayLabel[j].getText(), i).get(2));//调取接口获取string
            l3.setBounds(0, 35, 120, 10);
            l4.setText(getTelenum(dayLabel[j].getText(), i).get(3));//调取接口获取string
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

    private static boolean isfuture(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date sd1=df.parse(df.format(Calendar.getInstance().getTime()));
        Date sd2=df.parse(date);

        return sd1.before(sd2);
    }

    public static void setSchedule(int slot, int day, JPanel course){
        classPanelArray[slot][day].removeAll();
        classPanelArray[slot][day].add(course);
        course.setBounds(0,0,PANELWIDTH,PANELWIDTH);
        course.setLayout(null);
    }
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
