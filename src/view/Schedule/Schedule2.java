package view.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Schedule2 extends JPanel {
    private static JButton next = new JButton ("Next");
    private static JButton last = new JButton ("Last");
    private static final JLabel monday = new JLabel("Monday");
    private static final JLabel tuesday = new JLabel("Tuesday");
    private static final JLabel wednesday = new JLabel("Wednesday");
    private static final JLabel thursday = new JLabel("Thursday");
    private static final JLabel friday = new JLabel("Friday");
    private static final JLabel saturday = new JLabel("Saturday");
    private static final JLabel sunday = new JLabel("Sunday");
    private static final JLabel slot1 = new JLabel("9: 00~11: 00");
    private static final JLabel slot2 = new JLabel("13: 00~15: 00");
    private static final JLabel slot3 = new JLabel("16: 00~18: 00");
    private static final JLabel slot4 = new JLabel("19: 00~21: 00");
    private static JPanel slot=new JPanel();
    private static JPanel week=new JPanel();
    private static JPanel date= new JPanel();
    private static JPanel schedulePanel = new JPanel();
    // private static JPanel personalSchedule=new JPanel();
    public static final int LABELWIDTH=120;
    public static final int LABELHIGHT=20;
    public static final int BUTTONWIDTH=60;
    public static final int BUTTONHIGHT=200;
    public static final int PANELWIDTH=130;
    public static final int PANELHIGHT=90;
    public static final int PANELGAP=10;
    public static final int GAP=20;

    GeneratePanel g=new GeneratePanel();

    public void setDate(JPanel generateDate) {
        date.removeAll();
        date.add(generateDate);
        generateDate.setBounds(0,0,1200,50);
        generateDate.setLayout(null);
        generateDate.setBackground(Color.decode("#C1C1C1"));
    }
    public void setSchedulePanel(JPanel generateSchedule) {
        schedulePanel.removeAll();
        schedulePanel.add(generateSchedule);
        generateSchedule.setBounds(0,0,PANELWIDTH*7+PANELGAP*6,PANELHIGHT*4+PANELGAP*3);
        generateSchedule.setLayout(null);
        generateSchedule.setBackground(Color.decode("#EAEAEA"));
    }
    public JButton getNext(){
        return next;
    }
    public JButton getLast(){
        return last;
    }
    public Schedule2() throws FileNotFoundException {
        this.setLayout(null);
        this.setBounds(0, 0, 1200, 560);
        this.setBackground(Color.decode("#EAEAEA"));
        this.init();
        this.setVisible(true);
    }

    public void init() throws FileNotFoundException {
        week.setLayout(null);
        week.setBackground(Color.decode("#7F7F7F"));
        week.setBounds(0,0,1200,50);

        setDate(g.generateDate());
        date.setLayout(null);
        date.setBounds(0,50,1200,30);

        setSchedulePanel(g.generateSchedule());
        schedulePanel.setLayout(null);
        schedulePanel.setBounds(160,80,PANELWIDTH*7+PANELGAP*6,PANELHIGHT*4+PANELGAP*3);

        slot.setLayout(null);
        slot.setBackground(Color.decode("#EAEAEA"));
        slot.setBounds(3*GAP,80,LABELWIDTH-30,400);

        next.setBounds(1200-BUTTONWIDTH,80,BUTTONWIDTH,PANELHIGHT*4+PANELGAP*3);
        last.setBounds(-10,80,BUTTONWIDTH,PANELHIGHT*4+PANELGAP*3);
        next.setBackground(Color.decode("#EAEAEA"));
        last.setBackground(Color.decode("#EAEAEA"));

        monday.setBounds(4*GAP+LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);
        tuesday.setBounds(5*GAP+2*LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);
        wednesday.setBounds(6*GAP+3*LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);
        thursday.setBounds(7*GAP+4*LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);
        friday.setBounds(8*GAP+5*LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);
        saturday.setBounds(9*GAP+6*LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);
        sunday.setBounds(10*GAP+7*LABELWIDTH,GAP,LABELWIDTH,LABELHIGHT);

        slot1.setBounds(0,0,PANELWIDTH,PANELHIGHT);
        slot2.setBounds(0,PANELHIGHT+PANELGAP,PANELWIDTH,PANELHIGHT);
        slot3.setBounds(0,2*(PANELHIGHT+PANELGAP),PANELWIDTH,PANELHIGHT);
        slot4.setBounds(0,3*(PANELHIGHT+PANELGAP),PANELWIDTH,PANELHIGHT);

        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);
        week.add(saturday);
        week.add(sunday);

        slot.add(slot1);
        slot.add(slot2);
        slot.add(slot3);
        slot.add(slot4);


        this.add(last);
        this.add(next);
        this.add(week);
        this.add(date);
        this.add(slot);
        this.add(schedulePanel);
    }




    public void addaddListener(ActionListener actionListener){
       this.getLast().addActionListener(actionListener);
        this.getNext().addActionListener(actionListener);
    }

}
