package control.function;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import control.ControllerFactory;
import control.Controller;
import util.config;
import control.MainFrame;
import view.function.FunctionPanel;
import view.function.InfoButton;
import view.function.MenuButton;
import model.Client;

import javax.swing.*;

/**
 * This class is used to control view display and data interaction of {@link FunctionPanel}
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class FunctionController extends Controller{
    private FunctionPanel functionPanel;

    public FunctionController(){
        super(config.FUNCTION_PANEL_NAME, new FunctionPanel());

        this.functionPanel = (FunctionPanel) this.panel;
        functionPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { functionPanel.getMenuPanel().setVisible(false); }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
        functionPanel.addListener(new FunctionActionListener());

        // add menuPanel visible listener
        functionPanel.getAvatarButton().addActionListener(e -> functionPanel.getMenuPanel().setVisible(!functionPanel.getMenuPanel().isVisible()));

        // add exit Listener
        functionPanel.getExitButton().addActionListener(e -> {
            functionPanel.getMenuPanel().setVisible(false);
            MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
        });
    }

    /**
     * This method used to switch view.
     * <P>
     * Views loaded on demand.
     * The view will be created by {@link ControllerFactory} if {@link MainFrame#getList()} doesn't have this view.
     *
     * @param name the view name which will be switched to
     */
    public void goTo(String name){
        Controller controller = MainFrame.getInstance().getList().get(name);
        this.functionPanel = (FunctionPanel) this.panel;
        // create the panel if not established yet
        if(controller == null ) {
            controller = ControllerFactory.create(name);
            functionPanel.getShowPanel().add(controller.getPanel(), name);
        }
        else {
            controller.update();
        }
        functionPanel.getCardLayout().setHgap(controller.getH_gap());
        functionPanel.getCardLayout().setVgap(controller.getV_gap());
        functionPanel.getCardLayout().show(functionPanel.getShowPanel(), name);
    }

    /**
     * This method is used to refresh the {@link FunctionPanel} page
     */
    @Override
    public void update() {
        Client currentClient = MainFrame.getInstance().getClient();
        if (currentClient != null) {
            if (currentClient.getRole() != functionPanel.getClient().getRole()) {
                // clear old button
                functionPanel.getMenuButtons().forEach((k, v) -> functionPanel.getMenuPanel().remove(v));
                functionPanel.getInfoButtons().forEach((k, v) -> functionPanel.getInfoPanel().remove(v));
                functionPanel.getInfoButtons().clear();
                functionPanel.getMenuButtons().clear();

                // add new button
                functionPanel.button_init(currentClient.getRole());
                functionPanel.addListener(new FunctionActionListener());
                functionPanel.setClient(currentClient);
            }
            functionPanel.getWelcomeLabel().setText("Welcome " + currentClient.getNickName() + " !");
            ImageIcon avatarIcon = new ImageIcon(currentClient.getAvatarSrc());
            JButton avatarButton = functionPanel.getAvatarButton();
            avatarIcon.setImage(avatarIcon.getImage().getScaledInstance(avatarButton.getWidth(),avatarButton.getHeight(), Image.SCALE_DEFAULT));
            avatarButton.setIcon(avatarIcon);
        }
    }

    class FunctionActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(functionPanel.getMenuButtons().containsValue(e.getSource())){
                MenuButton menuButton = (MenuButton) e.getSource();
                functionPanel.getMenuPanel().setVisible(false);
                goTo(menuButton.getKey());
            }
            else if(functionPanel.getInfoButtons().containsValue(e.getSource())){
                InfoButton infoButton = (InfoButton) e.getSource();
                goTo(infoButton.getKey());
            }
        }
    }
}
