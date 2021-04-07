
package view;


import model.User;
import model.mapping.ClientMapping;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test{
    public static void main(String[] args)
    {
        JFrame test = new JFrame("Test");
        test.setSize(1200, 532);
        test.setLocation(200, 200);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "123333");
        ArrayList<User> users = null;
        try
        {
            users = ClientMapping.findUser(map);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


        Userdescription x = new Userdescription(users.get(0),test);
        test.getContentPane().add(x);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);

        System.out.println("Hello world!");
    }
}