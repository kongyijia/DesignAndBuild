package control.function;

import control.Controller;

import control.MainFrame;
import view.function.FunctionPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.config;
import view.function.InfoButton;
import view.function.MenuButton;

public class FunctionController extends Controller {
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
        functionPanel.addListener(e -> {
            if(e.getSource() == functionPanel.getAvatarButton()){
                functionPanel.getMenuPanel().setVisible(true);
            }
            else if(e.getSource() == functionPanel.getExitButton()){
                MainFrame.getInstance().setClient(null);
                MainFrame.getInstance().goTo(config.INDEX_PANEL_NAME);
            }
            else if(functionPanel.getMenuButtons().containsValue(e.getSource())){
                MenuButton menuButton = (MenuButton) e.getSource();
                goTo(menuButton.getKey());
            }
            else if(functionPanel.getInfoButtons().containsValue(e.getSource())){
                InfoButton infoButton = (InfoButton) e.getSource();
                goTo(infoButton.getKey());
            }
        });
    }

    public void goTo(String name){
        functionPanel.getCardLayout().show(functionPanel.getShowPanel(), name);
    }

    @Override
    public void update() {

    }

}
