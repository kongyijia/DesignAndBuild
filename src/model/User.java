package model;

import java.sql.Time;
import java.util.ArrayList;

/**
 * User model class.
 *
 * @author Yubo Wu
 * @version 1.3
 * @see Client
 * @since 8 May 2021
 */
public class User extends Client {
    private double account;
    private int level;
    private int learningTime;
    private String vip;
    private ArrayList<Course> courseSubscription;
    private String description;

    /**
     * Constructor of {@link User}. Mainly used in fastJSON.
     */
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

    /**
     * Constructor of {@link User}. With some default values.
     * <p>
     * {@link User#account} is {@code 0.0}
     * <p>
     * {@link User#level} is {@code 1}
     * <p>
     * {@link User#learningTime} is {@code 0}
     * <p>
     * {@link User#vip} is {@code Plain}
     * <p>
     * {@link User#courseSubscription} is an empty {@link ArrayList}
     * <p>
     * {@link User#description} is an empty {@link String}
     * <p>
     */
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

    /**
     * Constructor of {@link User}. With some default values.
     * <p>
     * {@link User#account} is {@code 0.0}
     * <p>
     * {@link User#level} is {@code 1}
     * <p>
     * {@link User#learningTime} is {@code 0}
     * <p>
     * {@link User#vip} is {@code Plain}
     * <p>
     * {@link User#courseSubscription} is an empty {@link ArrayList}
     * <p>
     */
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

    /**
     * Constructor of {@link User}. Mainly used in fastJSON.
     */
    public User() {
        this.courseSubscription = new ArrayList<>();
    }

    /**
     * @return {@link User#account}
     */
    public double getAccount() {
        return account;
    }

    /**
     * @return {@link User#level}
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return {@link User#learningTime}
     */
    public int getLearningTime() {
        return learningTime;
    }

    /**
     * @return {@link User#vip}
     */
    public String getVip() {
        return vip;
    }

    /**
     * @return {@link User#courseSubscription}
     */
    public ArrayList<Course> getCourseSubscription() {
        return courseSubscription;
    }

    /**
     * @return {@link User#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param account {@link User#account}
     */
    public void setAccount(double account) {
        this.account = account;
    }

    /**
     * @param level {@link User#level}
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param learningTime {@link User#learningTime}
     */
    public void setLearningTime(int learningTime) {
        this.learningTime = learningTime;
    }

    /**
     * @param vip {@link User#vip}
     */
    public void setVip(String vip) {
        this.vip = vip;
    }

    /**
     * @param course {@link User#courseSubscription}
     */
    public void addCourseSubscription(Course course) {
        this.courseSubscription.add(course);
    }

    /**
     * @param description {@link User#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
