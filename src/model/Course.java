package model;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Time;
import java.util.Date;

/**
 * Course model class.
 *
 * @author Yubo Wu
 * @version 1.0
 * @since 12 May 2021
 */
public class Course {
    private int id;
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
    @JSONField(format = "HH:mm:ss")
    private Time start;
    private int userId;
    private int coachId;
    private String type;

    public Course(int id, Date date, Time start, int userId, int coachId, String type) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.userId = userId;
        this.coachId = coachId;
        this.type = type;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                '}';
    }
}
