package models;

import java.sql.Time;
import java.util.ArrayList;

public class User extends Client {
    private double account;
    private int level;
    private int learningTime;
    private int vip;
    private ArrayList<Course> courseSubscription;
    private String description;

    public User(int id, String nickName, String password, int sex, String phone, String email, int role, boolean cancel, double account, int level, int learningTime, int vip, ArrayList<Course> courseSubscription, String description) {
        super(id, nickName, password, sex, phone, email, role, cancel);
        this.account = account;
        this.level = level;
        this.learningTime = learningTime;
        this.vip = vip;
        this.courseSubscription = courseSubscription;
        this.description = description;
    }

    public User(int id, String nickName, String password, int sex, String phone, String email, int role) {
        super(id, nickName, password, sex, phone, email, role);
        // default value
        this.account = 0.0;
        this.level = 1;
        this.learningTime = 0;
        this.vip = 1;
        this.courseSubscription = new ArrayList<>();
        this.description = "";
    }

    public User(int id, String nickName, String password, int sex, String phone, String email, int role, String description) {
        super(id, nickName, password, sex, phone, email, role);
        this.description = description;
        // default value
        this.account = 0.0;
        this.level = 1;
        this.learningTime = 0;
        this.vip = 1;
        this.courseSubscription = new ArrayList<>();
    }

    public User() {
        this.courseSubscription = new ArrayList<>();
    }

    public double getAccount() {
        return account;
    }

    public int getLevel() {
        return level;
    }

    public int getLearningTime() {
        return learningTime;
    }

    public int getVip() {
        return vip;
    }

    public ArrayList<Course> getCourseSubscription() {
        return courseSubscription;
    }

    public String getDescription() {
        return description;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLearningTime(int learningTime) {
        this.learningTime = learningTime;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public void addCourseSubscription(Course course) {
        this.courseSubscription.add(course);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
