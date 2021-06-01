package control;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import control.function.FunctionController;
import model.Client;
import model.mapping.ClientMapping;
import util.config;

/**
 *  This is main controller of the overall frame.
 *  It provides view switching method {@code goto}, view update method and stores current logged client's information.
 *
 *  @author Jufeng Sun
 *  @version 1.0
 *  @since 16 April 2021
 */

public class MainFrame extends JFrame implements Cloneable{
    private static MainFrame mainFrame;
    private HashMap<String, Controller> list;      // management list
    private Client client;                         // current logged client

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
        this.setTitle("London Fitness");
        this.setSize(config.FRAME_WIDTH, config.FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setVisible(true);
    }

    /**
     *
     * @param name The name of the controller registered to the list
     * @param o Controller registered to the list
     */
    public void registerObserver(String name, Controller o) {
        list.put(name, o);
    }

    /**
     *
     * @param name The name of the controller that needs to be removed from the list
     */
    public void removeObserver(String name) {
        list.remove(name);
    }

    /**
     * Call the {@link Controller#update()} method of all controllers in the list
     */
    private void notifyObserver() {
        list.forEach((k, v) -> v.update());
    }

    /**
     * Call the {@link Controller#update()} method of one controller in the list
     * @param name the name of controller
     */
    public void notifyObserver(String name) {
        if (list.containsKey(name))
            list.get(name).update();
    }

    /**
     * This function is used to change current logged client.
     * When this function is called, it will call update() of all members in {@link MainFrame#list}.
     *
     * @param client the new client of current logged client.
     */
    public void setClient(Client client) {
        this.client = client;
        notifyObserver();
    }

    /**
     * This function is used to change current logged client.
     * When this function is called, it will call update() of all members in {@link MainFrame#list}.
     *
     * @param ID the new clientID of current logged client.
     */
    public void setClient(int ID) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(ID));
        try
        {
            ArrayList<Client> clients = ClientMapping.find(map);
            setClient(clients.get(0));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the currently logged in user
     *
     * @return the current logged client.
     */
    public Client getClient() {
        try {
            return (Client) client.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * This method is used to get the currently registered controller list
     * @return Controller List
     */
    public HashMap<String, Controller> getList() {
        return list;
    }

    /**
     * This method is used to get a registered controller
     * @param name the name of controller
     * @return controller needed
     */
    public Controller getController(String name){
            return list.get(name);
    }

    /**
     * This function used to switch view.
     * Two switch mode:
     * Index, enroll, function view switch uses this switch mode, other view switch use {@link FunctionController#goTo(String)}
     * <P>
     * Views loaded on demand.
     * The view will be created by {@link ControllerFactory} if {@link MainFrame#list} doesn't have this view.
     *
     * @param name the view name which will be switched to
     */
    public void goTo(String name) {
        ArrayList<String> firstGate = new ArrayList<>(Arrays.asList(
                config.INDEX_PANEL_NAME, config.FUNCTION_PANEL_NAME, config.ENROLL_PANEL_NAME));
        // Index, enroll and function view switching uses this mode
        if(firstGate.contains(name)) {
            list.forEach((k, v) -> v.getPanel().setVisible(false));
            Controller controller = list.get(name);
            // if this view doesn't exist, create one
            if(controller == null ) {
                controller = ControllerFactory.create(name);
                this.add(controller.getPanel());
            }
            else {
                controller.update();
            }
            controller.getPanel().setVisible(true);
        }
        // the page in functionPanel switching uses functionPanel's switch mode
        else{
            FunctionController functionController = (FunctionController) list.get(config.FUNCTION_PANEL_NAME);
            functionController.goTo(name);
        }
    }


}
