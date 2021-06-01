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
    /**
     * Coach level. {@code 0} is normal;{@code 1} is advanced;{@code 2} is outstanding.
     */
    private int level;
    /**
     * Coach description
     */
    private String description;

    /**
     * Constructor of {@link Coach}. Mainly used in fastJSON.
     *
     * @param id            Coach ID
     * @param nickName      Coach nickname
     * @param password      Coach password
     * @param sex           Coach sex
     * @param phone         Coach phone number
     * @param email         Coach email
     * @param role          Coach role, which is {@code 1}
     * @param cancel        Coach cancel status
     * @param recordHistory Coach record history
     * @param avatarSrc     Coach avatar source path
     * @param level         {@link Coach#level}
     * @param description   {@link Coach#description}
     */
    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role
            , boolean cancel, ArrayList<Client.RecordHistory> recordHistory, String avatarSrc, int level
            , String description) {
        super(id, nickName, password, sex, phone, email, role, cancel, recordHistory, avatarSrc);
        this.level = level;
        this.description = description;
    }

    /**
     * Constructor of {@link Coach}. Mainly used in fastJSON.
     *
     * @param id          Coach ID
     * @param nickName    Coach nickname
     * @param password    Coach password
     * @param sex         Coach sex
     * @param phone       Coach phone number
     * @param email       Coach email
     * @param role        Coach role, which is {@code 1}
     * @param level       {@link Coach#level}
     * @param description {@link Coach#description}
     */
    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role, int level, String description) {
        super(id, nickName, password, sex, phone, email, role);
        this.level = level;
        this.description = description;
    }

    /**
     * Constructor of {@link Coach}. With default value.
     * <br>
     * {@link Coach#level} is {@code 0}
     *
     * @param id          coach id
     * @param nickName    coach nickname
     * @param password    coach password
     * @param sex         coach sex
     * @param phone       coach phone number
     * @param email       coach email
     * @param role        coach role, which is {@code 1}
     * @param description {@link Coach#description}
     */
    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role, String description) {
        super(id, nickName, password, sex, phone, email, role);
        this.description = description;
        // default value
        this.level = 0;
    }

    /**
     * Constructor of {@link Coach}. With default value.
     * <br>
     * {@link Coach#level} is {@code 0}
     * <br>
     * {@link Coach#description} is an empty {@link String}
     *
     * @param id       coach id
     * @param nickName coach nickname
     * @param password coach password
     * @param sex      coach sex
     * @param phone    coach phone number
     * @param email    coach email
     * @param role     coach role, which is {@code 1}
     */
    public Coach(int id, String nickName, String password, int sex, String phone, String email, int role) {
        super(id, nickName, password, sex, phone, email, role);
        // default value
        this.description = "";
        this.level = 0;
    }

    /**
     * Constructor with none params of {@link Coach}. Mainly used in fastJSON.
     * <br>
     * Default value: {@link Coach#level} is {@code 0}
     */
    public Coach() {
        this.level = 0;
    }

    /**
     * @return {@link Coach#level}
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level {@link Coach#level}
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return {@link Coach#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description {@link Coach#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Convert a certain level of a coach to price of the lecture.
     *
     * @param coach the coach you want to get whose price of the course
     * @return the price
     */
    public static double level2price(Coach coach) {
        return (coach.getLevel() + 1) * 400;
    }
}
