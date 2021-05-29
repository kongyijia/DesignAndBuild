package util;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Util {

    public static boolean isNickNameLegal(String nickName){
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return nickName.matches(regex);
    }

    public static int isPhoneLegal(String phone){
        if(phone.length() != 11) return 0;
        for (int i = 0; i < phone.length(); i++)
            if(phone.charAt(i) < '0' || phone.charAt(i) > '9')
                return -1;
            return 1;
    }

    public static boolean isEmailLegal(String email){
        String regex = "^\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
        return email.matches(regex);
    }

    public static void showDialog(Component component,String info){
        Object[] Okay = {"OK"};
        JOptionPane.showOptionDialog(component, info, "Message",JOptionPane.YES_NO_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE,null, Okay, Okay[0]);
    }

    public static boolean isVideoTypeLegal(String type){
        String regex = "^[a-zA-Z0-9_]{0,}$";
        return type.matches(regex);
    }

    public static ArrayList<String> toArrayList(Object[] args){
        ArrayList<String> arrayList = new ArrayList<>();
        for(Object i : args){
            arrayList.add(i.toString());
        }
        return arrayList;
    }

    public static java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time.valueOf(str);
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

}
