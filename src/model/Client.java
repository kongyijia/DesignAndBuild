package model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Date;

/**
 * client model class. It represents all type of client ({@link User}, {@link Coach} and {@link Administrator}).
 *
 * @author Yubo Wu
 * @version 1.2
 * @since 18 April 2021
 */
public class Client {
    private int id;
    private String nickName;
    private String password;
    private int sex; // 0: female; 1: male
    private String phone;
    private String email;
    private int role; // 0: admin; 1:coach; 2: user
    private boolean cancel; // false: active; true: cancel
    private ArrayList<RecordHistory> recordHistory; // record history
    private String avatarSrc;


    public Client(int id, String nickName, String password, int sex, String phone, String email, int role, boolean cancel, ArrayList<RecordHistory> recordHistory, String avatarSrc) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.cancel = cancel;
        this.recordHistory = recordHistory;
        this.avatarSrc = avatarSrc;
    }

    public Client(int id, String nickName, String password, int sex, String phone, String email, int role) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.role = role;
        // default
        this.cancel = false;
        this.recordHistory = new ArrayList<>();
        this.avatarSrc = "";
    }

    public Client() {
    }

    public Client(int id, String nickName, String password, int sex, String phone, String email, int role, String avatarSrc) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.avatarSrc = avatarSrc;
        // default
        this.cancel = false;
        this.recordHistory = new ArrayList<>();
    }

    public ArrayList<RecordHistory> getRecordHistory() {
        return recordHistory;
    }

    public void setRecordHistory(ArrayList<RecordHistory> recordHistory) {
        this.recordHistory = recordHistory;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public static class RecordHistory {
        private int videoId;
        private int learningTime;
        private int progress;

        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date latestPlayingDateTime;

        public RecordHistory(int videoId, int learningTime, int progress, Date latestPlayingDateTime) {
            this.videoId = videoId;
            this.learningTime = learningTime;
            this.progress = progress;
            this.latestPlayingDateTime = latestPlayingDateTime;
        }

        public RecordHistory() {
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getLearningTime() {
            return learningTime;
        }

        public void setLearningTime(int learningTime) {
            this.learningTime = learningTime;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public Date getLatestPlayingDateTime() {
            return latestPlayingDateTime;
        }

        public void setLatestPlayingDateTime(Date latestPlayingDateTime) {
            this.latestPlayingDateTime = latestPlayingDateTime;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
