package control;


import control.function.FunctionController;
import model.Client;
import model.User;
import model.mapping.ClientMapping;
import util.config;
import javax.swing.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame extends JFrame{
    private static MainFrame mainFrame;
    private HashMap<String, Controller> list;
    private Client client;

    private MainFrame() {
        initialize();
    }

    public static MainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    private void initialize() {
        this.list = new HashMap<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("CourseWork");
        this.setSize(config.FRAME_WIDTH, config.FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setVisible(true);

        // 设置一个默认登录用户，真正用户登录后变更
        // TODO 设置默认用户为暂时做法，之后修改逻辑
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
        this.client = users.get(0);
    }

    public void registerObserver(String name, Controller o) {
        list.put(name, o);
    }

    public void removeObserver(String name) {
        list.remove(name);
    }

    private void notifyObserver() {
        list.forEach((k, v) -> v.update());
    }

    public void setClient(Client client) {
        this.client = client;
        notifyObserver();
    }

    public Client getClient() {
        return client;
    }

    public void goTo(String name){
        ArrayList<String> firstGate = new ArrayList<>();
        firstGate.add(config.INDEX_PANEL_NAME);
        firstGate.add(config.FUNCTION_PANEL_NAME);
        firstGate.add(config.ENROLL_PANEL_NAME);

        // 注册，登录，功能页面跳转用此方法
        if(firstGate.contains(name)) {
            list.forEach((k, v) -> v.getPanel().setVisible(false));

            //更新视图
            Controller controller = list.get(name);
            controller.update();
            controller.getPanel().setVisible(true);
        }
        // 功能页面内的跳转用功能页面的跳转方法
        else{
            FunctionController functionController = (FunctionController) list.get(config.FUNCTION_PANEL_NAME);
            functionController.goTo(name);
        }
    }
}
