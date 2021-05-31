package util;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to store some commonly used tools in projects
 *
 * @author Jufeng Sun, Zai Song
 * @version 1.0
 * @since 16 April 2021
 */

public class Util {

    /**
     * Used to determine whether the user's nickname is legal,
     * User nickname can only consist of numbers and letters.
     *
     * @param nickName String which need to determine whether the user's nickname is legal
     * @return {@code true}: User nickname is legal, {@code false}: User nickname is not legal
     */
    public static boolean isNickNameLegal(String nickName){
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return nickName.matches(regex);
    }

    /**
     * Used to determine whether the phone number is legal,
     * The phone number can only consist of digits, and the length must be eleven.
     *
     * @param phone String which need to determine whether the phone number is legal
     * @return {@code true}: Phone number is legal, {@code false}: Phone number is not legal
     */
    public static int isPhoneLegal(String phone){
        if(phone.length() != 11) return 0;
        for (int i = 0; i < phone.length(); i++)
            if(phone.charAt(i) < '0' || phone.charAt(i) > '9')
                return -1;
            return 1;
    }

    /**
     * Used to determine whether the email is legal.
     *
     * @param email String which need to determine whether the email is legal
     * @return {@code true}: Email is legal, {@code false}: Email is not legal
     */
    public static boolean isEmailLegal(String email){
        String regex = "^\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
        return email.matches(regex);
    }

    /**
     * Used to show the optionDialog with a {@code OK} button.
     *
     * @param component The parent component that called this method
     * @param info Message to be displayed
     */
    public static void showDialog(Component component,String info){
        Object[] Okay = {"OK"};
        JOptionPane.showOptionDialog(component, info, "Message",JOptionPane.YES_NO_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE,null, Okay, Okay[0]);
    }

    /**
     * Used to determine whether the video type is legal,
     * Video type can only consist of numbers and letters.
     *
     * @param type String which need to determine whether the phone number is legal
     * @return {@code true}: Video type is legal, {@code false}: Video type is not legal
     */
    public static boolean isVideoTypeLegal(String type){
        String regex = "^[a-zA-Z0-9_]{0,}$";
        return type.matches(regex);
    }

    /**
     * This method is used to convert {@link Object} array to {@link ArrayList<String>}
     *
     * @param args Data to be converted
     * @return {@link ArrayList<String>} The converted ArrayList
     */
    public static ArrayList<String> toArrayList(Object[] args){
        ArrayList<String> arrayList = new ArrayList<>();
        for(Object i : args){
            arrayList.add(i.toString());
        }
        return arrayList;
    }

    /**
     * This method is used to convert {@link String} to {@link java.sql.Time}
     *
     * @param strDate Date string to be converted
     * @return {@link java.sql.Time} Corresponding time after conversion
     */
    public static java.sql.Time strToTime(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date d = null;
        try {
            d = format.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time.valueOf(strDate);
    }

    /**
     * This method is used to convert {@link String} to {@link Date}
     *
     * @param strDate Date string to be converted
     * @return {@link Date} Corresponding date after conversion
     */
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
