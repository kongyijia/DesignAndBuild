package control.PersonalSchedule;

import control.Controller;
import control.MainFrame;
import model.Client;
import util.config;
import view.Schedule.GeneratePanel;
import view.Schedule.Schedule2;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    GeneratePanel g=new GeneratePanel();

    public ScheduleController() throws FileNotFoundException {
        super(config.SCHEDULE_NAME,new Schedule2());
        schedule2 = (Schedule2) this.panel;    // 强制转换为当前控制器对应的页面类型

        schedule2.addaddListener(e->{
            if(e.getSource()== schedule2.getNext()){
                g.client= MainFrame.getInstance().getClient();
                schedule2.setOffsetOfPage(schedule2.getOffsetOfPage()+1);
                schedule2.setDate(g.generateDate());
                try {
                    schedule2.setSchedulePanel(g.generateSchedule());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            if(e.getSource()== schedule2.getLast()){
                g.client= MainFrame.getInstance().getClient();
                schedule2.setOffsetOfPage(schedule2.getOffsetOfPage()-1);
                schedule2.setDate(g.generateDate());
                try {
                    schedule2.setSchedulePanel(g.generateSchedule());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });


    }

    @Override
    public void update() {
        g.client= MainFrame.getInstance().getClient();
        schedule2.setOffsetOfPage(0);
        schedule2.setDate(g.generateDate());
        try {
            schedule2.setSchedulePanel(g.generateSchedule());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("update schedule!!");
    }


}