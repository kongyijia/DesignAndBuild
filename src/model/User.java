package model;

import java.sql.Time;
import java.util.ArrayList;

/**
 * User model class.
 *
 * @author Yubo Wu
 * @version 1.2
 * @see Client
 * @since 18 April 2021
 */
public class User extends Client {
    private double account;
    private int level;
    private int learningTime;
    private String vip;
    private ArrayList<Course> courseSubscription;
    private String description;

    public User(int id, String nickName, String password, int sex, String phone, String email
            , int role, boolean cancel, ArrayList<RecordHistory> recordHistory, String avatarSrc, double account
            , int level, int learningTime, String vip, ArrayList<Course> courseSubscription, String description) {
        super(id, nickName, password, sex, phone, email, role, cancel, recordHistory, avatarSrc);
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
        this.vip = "Plain";
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
        this.vip = "Plain";
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

    public String getVip() {
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

    public void setVip(String vip) {
        this.vip = vip;
    }

    public void addCourseSubscription(Course course) {
        this.courseSubscription.add(course);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
