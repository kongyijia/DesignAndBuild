package model;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Time;
import java.util.Date;

/**
 * Course model class.
 *
 * @author Yubo Wu
 * @version 1.1
 * @since 12 May 2021
 */
public class Course {
    /**
     * Course ID
     */
    private int id;
    /**
     * Course date
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
    /**
     * Course start time
     */
    @JSONField(format = "HH:mm:ss")
    private Time start;
    /**
     * UserID in {@link User}
     */
    private int userId;
    /**
     * CoachID in {@link Coach}
     */
    private int coachId;
    /**
     * Course type
     */
    private String type;
    /**
     * Course price
     */
    private double price;

    /**
     * Constructor of {@link Course}.
     *
     * @param id      course id
     * @param date    course date
     * @param start   course start time
     * @param userId  userID in {@link User}
     * @param coachId coachID in {@link Coach}
     * @param type    course type
     * @param price   course price
     */
    public Course(int id, Date date, Time start, int userId, int coachId, String type, double price) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.userId = userId;
        this.coachId = coachId;
        this.type = type;
        this.price = price;
    }

    /**
     * Constructor of {@link Course}. Mainly used in fastJSON.
     */
    public Course() {
    }

    /**
     * @return {@link Course#id}
     */
    public int getId() {
        return id;
    }

    /**
     * @param id {@link Course#id}
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return {@link Course#date}
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date {@link Course#date}
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return {@link Course#start}
     */
    public Time getStart() {
        return start;
    }

    /**
     * @param start {@link Course#start}
     */
    public void setStart(Time start) {
        this.start = start;
    }

    /**
     * @return {@link Course#userId}
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId {@link Course#userId}
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return {@link Course#coachId}
     */
    public int getCoachId() {
        return coachId;
    }

    /**
     * @param coachId {@link Course#coachId}
     */
    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    /**
     * @return {@link Course#type}
     */
    public String getType() {
        return type;
    }

    /**
     * @param type {@link Course#type}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return {@link Course#price}
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price {@link Course#price}
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", date=" + date +
                ", start=" + start +
                ", userId=" + userId +
                ", coachId=" + coachId +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
