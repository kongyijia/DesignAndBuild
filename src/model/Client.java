package model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Date;

/**
 * client model class. It represents all type of client ({@link User}, {@link Coach} and {@link Administrator}).
 *
 * @author Yubo Wu
 * @version 1.3
 * @since 8 May 2021
 */
public class Client implements Cloneable {
    /**
     * Client ID
     */
    private int id;
    /**
     * Client nickname, unique
     */
    private String nickName;
    /**
     * Client password
     */
    private String password;
    /**
     * Client sex. {@code 0} for female; {@code 1} for male.
     */
    private int sex; // 0: female; 1: male
    /**
     * Client phone number with 11 digits
     */
    private String phone;
    /**
     * Client email
     */
    private String email;
    /**
     * Client role. {@code 0} for {@link Administrator}; {@code 1} for {@link Coach}; {@code 2} for {@link User}.
     */
    private int role; // 0: admin; 1:coach; 2: user
    /**
     * Client cancel status. {@code false} is Active status; {@code true} is cancel status.
     */
    private boolean cancel; // false: active; true: cancel
    /**
     * Client playback history
     */
    private ArrayList<RecordHistory> recordHistory; // record history
    /**
     * Client avatar source path
     */
    private String avatarSrc;


    /**
     * Constructor of {@link Client}. Mainly used in fastJSON.
     */
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

    /**
     * Constructor of {@link Client}. With default value.
     * <br>
     * {@link Client#cancel} is {@code false}
     * <br>
     * {@link Client#recordHistory} is an empty {@link ArrayList}
     * <br>
     * {@link Client#avatarSrc} is an empty {@link String}
     *
     * @param id       client id
     * @param nickName client nickname
     * @param password client password
     * @param sex      client sex
     * @param phone    client phone
     * @param email    client email
     * @param role     client role: 0 for {@link Administrator}; 1 for {@link Coach}; 2 for {@link User}
     */
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

    /**
     * Constructor with none params of {@link Client}. Mainly used in fastJSON.
     */
    public Client() {
    }

    /**
     * Constructor of {@link Client}. With default value.
     * <br>
     * {@link Client#cancel} is {@code false}
     * <br>
     * {@link Client#recordHistory} is an empty {@link ArrayList}
     *
     * @param avatarSrc avatar source path
     */
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

    /**
     * @return {@link Client#recordHistory}
     */
    public ArrayList<RecordHistory> getRecordHistory() {
        return recordHistory;
    }

    /**
     * @param recordHistory {@link Client#recordHistory}
     */
    public void setRecordHistory(ArrayList<RecordHistory> recordHistory) {
        this.recordHistory = recordHistory;
    }

    /**
     * @return {@link Client#avatarSrc}
     */
    public String getAvatarSrc() {
        return avatarSrc;
    }

    /**
     * @param avatarSrc {@link Client#avatarSrc}
     */
    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    /**
     * @return {@link Client#id}
     */
    public int getId() {
        return id;
    }

    /**
     * @param id {@link Client#id}
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return {@link Client#nickName}
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName {@link Client#nickName}
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return {@link Client#password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password {@link Client#password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return {@link Client#sex}
     */
    public int getSex() {
        return sex;
    }

    /**
     * @param sex {@link Client#sex}
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * @return {@link Client#phone}
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone {@link Client#phone}
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return {@link Client#email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email {@link Client#email}
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return {@link Client#role}
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role {@link Client#role}
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return {@link Client#cancel}
     */
    public boolean isCancel() {
        return cancel;
    }

    /**
     * @param cancel {@link Client#cancel}
     */
    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Used to record the user's playback history.
     * <br>
     * {@link Client.RecordHistory#videoId} to record video ID.
     * <br>
     * {@link Client.RecordHistory#learningTime} to record the learning time of this history.
     * <br>
     * {@link Client.RecordHistory#latestPlayingDateTime} to record the latest datetime the user watched this video.
     */
    public static class RecordHistory {
        /**
         * Video ID
         */
        private int videoId;
        /**
         * Learning time for this video record
         */
        private int learningTime;
        /**
         * The progress of the record
         */
        private int progress;
        /**
         * the latest playing datetime the user watched
         */
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date latestPlayingDateTime;

        /**
         * Constructor of {@link Client.RecordHistory}. Mainly used in fastJSON.
         */
        public RecordHistory(int videoId, int learningTime, int progress, Date latestPlayingDateTime) {
            this.videoId = videoId;
            this.learningTime = learningTime;
            this.progress = progress;
            this.latestPlayingDateTime = latestPlayingDateTime;
        }

        /**
         * Constructor of {@link Client.RecordHistory}. Mainly used in fastJSON.
         */
        public RecordHistory() {
        }

        /**
         * @return {@link Client.RecordHistory#videoId}
         */
        public int getVideoId() {
            return videoId;
        }

        /**
         * @param videoId {@link Client.RecordHistory#videoId}
         */
        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        /**
         * @return {@link Client.RecordHistory#learningTime}
         */
        public int getLearningTime() {
            return learningTime;
        }

        /**
         * @param learningTime {@link Client.RecordHistory#learningTime}
         */
        public void setLearningTime(int learningTime) {
            this.learningTime = learningTime;
        }

        /**
         * @return {@link Client.RecordHistory#progress}
         */
        public int getProgress() {
            return progress;
        }

        /**
         * @param progress {@link Client.RecordHistory#progress}
         */
        public void setProgress(int progress) {
            this.progress = progress;
        }

        /**
         * @return {@link Client.RecordHistory#latestPlayingDateTime}
         */
        public Date getLatestPlayingDateTime() {
            return latestPlayingDateTime;
        }

        /**
         * @param latestPlayingDateTime {@link Client.RecordHistory#latestPlayingDateTime}
         */
        public void setLatestPlayingDateTime(Date latestPlayingDateTime) {
            this.latestPlayingDateTime = latestPlayingDateTime;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
