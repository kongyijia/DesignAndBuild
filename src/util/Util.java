package util;

public class Util {

    public static boolean isNickNameLegal(String nickName){
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return nickName.matches(regex);
    }

    public static boolean isPhoneLegal(String phone){
        return phone.length() == 11;
    }

    public static boolean isEmailLegal(String email){
        String regex = "^\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
        return email.matches(regex);
    }



}
