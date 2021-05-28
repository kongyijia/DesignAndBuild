package view.Schedule;

import control.MainFrame;
import model.Client;
import model.Course;
import model.Video;
import model.mapping.ClientMapping;
import model.mapping.CourseMapping;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GeneratePanel {
    public Client client= MainFrame.getInstance().getClient();
    public static final int LABELWIDTH=120;
    public static final int LABELHIGHT=20;
    public static final int BUTTONWIDTH=60;
    public static final int BUTTONHIGHT=200;
    public static final int PANELWIDTH=130;
    public static final int PANELHIGHT=90;
    public static final int PANELGAP=10;
    public static final int GAP=20;

    private static int offsetOfPage =0;
    public static JPanel[][] classPanelArray=new JPanel[4][7];
    public JLabel[] dayLabel =new JLabel[7];

    public void setOffsetOfPage(int num) {
        offsetOfPage=num;
    }
    public JPanel getSchedule(int day,int slot){
        return classPanelArray[day][slot];
    }
    public int getOffsetOfPage() {
        return offsetOfPage;
    }
    public JPanel generateSchedule() throws FileNotFoundException {
        JPanel schedulePanel=new JPanel();
        generateClass();
        for(int i=0;i<4;i++){
            for(int j=0; j<7; j++){
                schedulePanel.add(getSchedule(i,j));
            }
        }
        return schedulePanel;
    }


    public void generateClass() throws FileNotFoundException {

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
    public JPanel generateCoursePanel(int j,int i) throws FileNotFoundException {
        JLabel l1=new JLabel("");
        JLabel l2=new JLabel("");
        JLabel l3=new JLabel("");
        JLabel l4=new JLabel("");
        JPanel p=new JPanel();
        if(!getTelenum(dayLabel[j].getText(),i).isEmpty()) {
            l1.setText(getTelenum(dayLabel[j].getText(), i).get(0));//调取接口获取string
            l1.setBounds(0, 0, 120, 10);
            l2.setText(getTelenum(dayLabel[j].getText(), i).get(1));//调取接口获取string
            l2.setBounds(0, 15, 120, 10);
            l3.setText(getTelenum(dayLabel[j].getText(), i).get(2));//调取接口获取string
            l3.setBounds(0, 35, 120, 10);
            l4.setText(getTelenum(dayLabel[j].getText(), i).get(3));//调取接口获取string
            l4.setBounds(0, 50, 120, 10);
            System.out.println(getTelenum(dayLabel[j].getText(),i));
            System.out.println(client.getRole());
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
    public void setSchedule(int slot,int day,JPanel course){
        classPanelArray[slot][day].removeAll();
        classPanelArray[slot][day].add(course);
        course.setBounds(0,0,PANELWIDTH,PANELWIDTH);
        course.setLayout(null);
    }
    public JPanel generateDate(){
        JPanel date= new JPanel();
        Calendar c = Calendar.getInstance();
        String []dayOfWeek= new String[7];
        int today= getWeek(c,dayOfWeek);
        for(int i=0;i<7;i++){
            dayLabel[i]=new JLabel(dayOfWeek[i]);
            if(today==i&&offsetOfPage==0)
                dayLabel[i].setForeground(Color.red);
            dayLabel[i].setBounds((i+4)*GAP+(i+1)*LABELWIDTH,0,LABELWIDTH,LABELHIGHT);
            date.add(dayLabel[i]);
        }
        return date;
    }
    public int getWeek(Calendar c,String[]dayOfWeek){
        System.out.println(offsetOfPage+"!!!");
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
    public ArrayList<Course> findCourse(String date,int slot) throws FileNotFoundException {
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
    public ArrayList<String> getTelenum(String date,int slot) throws FileNotFoundException {
        ArrayList<Course> course=new ArrayList<Course>();
        ArrayList<String> info=new ArrayList<String>();
        course=findCourse(date,slot);
        HashMap<String, String> condition1 = new HashMap<>();//find coach
        HashMap<String, String> condition2 = new HashMap<>();//find user
        if(!course.isEmpty()) {
            condition1.put("id", course.get(0).getCoachId()+"");
            condition2.put("id",course.get(0).getUserId()+"");

            info.add("coach:"+ClientMapping.find(condition1).get(0).getNickName());
            info.add(ClientMapping.find(condition1).get(0).getPhone());
            info.add("user:"+ClientMapping.find(condition2).get(0).getNickName());
            info.add(ClientMapping.find(condition2).get(0).getPhone());
        }
        return info;
    }
}
