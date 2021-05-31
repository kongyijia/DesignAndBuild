package model;

import java.util.ArrayList;

/**
 * Administrator model class.
 *
 * @author Yubo Wu
 * @version 1.2
 * @see Client
 * @since 18 April 2021
 */
public class Administrator extends Client {
    /**
     * Constructor of {@link Administrator}. Mainly used in fastJSON.
     *
     * @param id            administrator id
     * @param nickName      administrator nickname
     * @param password      administrator password
     * @param sex           administrator sex
     * @param phone         administrator phone number
     * @param email         administrator email
     * @param role          administrator role, which is {@code 0}
     * @param cancel        administrator cancel status
     * @param recordHistory administrator recordHistory
     * @param avatarSrc     administrator avatar source path
     */
    public Administrator(int id, String nickName, String password, int sex, String phone, String email, int role
            , boolean cancel, ArrayList<RecordHistory> recordHistory, String avatarSrc) {
        super(id, nickName, password, sex, phone, email, role, cancel, recordHistory, avatarSrc);
    }

    /**
     * Constructor of {@link Administrator}.
     *
     * @param id       administrator id
     * @param nickName administrator nickname
     * @param password administrator password
     * @param sex      administrator sex
     * @param phone    administrator phone number
     * @param email    administrator email
     * @param role     administrator role, which is {@code 0}
     */
    public Administrator(int id, String nickName, String password, int sex, String phone, String email, int role) {
        super(id, nickName, password, sex, phone, email, role);
    }

    /**
     * Constructor of {@link Administrator}. Mainly used in fastJSON.
     */
    public Administrator() {
    }
}
