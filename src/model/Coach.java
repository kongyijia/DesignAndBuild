package model;

import java.util.ArrayList;

/**
 * Coach model class.
 *
 * @author Yubo Wu
 * @version 1.3
 * @see Client
 * @since 28 May 2021
 */
public class Coach extends Client {
    private int level; // 0: 低级（默认）; 1: 高级; 2: 特级
    private String description;

    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role
            , boolean cancel, ArrayList<Client.RecordHistory> recordHistory, String avatarSrc, int level
            , String description) {
        super(id, nickName, password, sex, phone, email, role, cancel, recordHistory, avatarSrc);
        this.level = level;
        this.description = description;
    }

    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role, int level, String description) {
        super(id, nickName, password, sex, phone, email, role);
        this.level = level;
        this.description = description;
    }

    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role, String description) {
        super(id, nickName, password, sex, phone, email, role);
        this.description = description;
        // default value
        this.level = 0;
    }

    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role) {
        super(id, nickName, password, sex, phone, email, role);
        // default value
        this.description = "";
        this.level = 0;
    }

    public Coach() {
        this.level = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static double level2price(Coach coach) {
        return (coach.getLevel() + 1) * 400;
    }
}
