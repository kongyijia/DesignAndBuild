package model;

public class Administrator extends Client {
    public Administrator(int id, String nickName, String password, int sex, String phone, String email, int role, boolean cancel) {
        super(id, nickName, password, sex, phone, email, role, cancel);
    }

    public Administrator(int id, String nickName, String password, int sex, String phone, String email, int role) {
        super(id, nickName, password, sex, phone, email, role);
    }

    public Administrator() {
    }
}
