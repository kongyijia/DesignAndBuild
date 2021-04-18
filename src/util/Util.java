package util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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

}
