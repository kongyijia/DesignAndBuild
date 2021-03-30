package models;

public class Client {
    private int id;
    private String nickName;
    private String password;
    private int sex; // 0: female; 1: male
    private String phone;
    private String email;
    private int role; // 0: admin; 1:coach; 2: user
    private boolean cancel; // false: active; true: cancel

    public Client(int id, String nickName, String password, int sex, String phone, String email, int role, boolean cancel) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.cancel = cancel;
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
    }

    public Client() {
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
}
