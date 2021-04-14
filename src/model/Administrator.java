package model;

import java.util.ArrayList;

/**
 * Administrator model class.
 *
 * @author Yubo Wu
 * @version 1.1
 * @see Client
 * @since 14 April 2021
 */
public class Administrator extends Client {
    public Administrator(int id, String nickName, String password, int sex, String phone, String email, int role, boolean cancel, ArrayList<Integer> recordHistory, String avatarSrc) {
        super(id, nickName, password, sex, phone, email, role, cancel, recordHistory, avatarSrc);
    }

    public Administrator(int id, String nickName, String password, int sex, String phone, String email, int role) {
        super(id, nickName, password, sex, phone, email, role);
    }

    public Administrator() {
    }
}
