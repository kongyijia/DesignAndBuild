package control.Userinformation;

import control.Controller;
import control.EditPersonalPageModal.EditCoachModalController;
import control.EditPersonalPageModal.EditUserModalController;
import control.MainFrame;
import model.Client;
import util.config;
import view.Userinformation.UserDescription;
import view.Userinformation.BuildProfilePanel;
import view.Userinformation.ShowCustomDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserInformationController extends Controller
{
    private Client user;
    private final UserDescription userdescription;

    private EditCoachModalController editCoachModalController;
    private EditUserModalController editUserModalController;

    public UserInformationController(){
        super(config.USERDESCRIPTION_PANEL_NAME, new UserDescription());
        user = MainFrame.getInstance().getClient();
        this.userdescription = (UserDescription) this.panel;
        buttons();
        this.panel.setVisible(true);
        this.setH_gap(150);
    }

    public void changepassword(String newpwd)
    {
        user.setPassword(newpwd);
    }

    public boolean checkpassword(String oldpwd)
    {
        String correctpassword = user.getPassword();
        return Objects.equals(correctpassword, oldpwd);
    }


    private void buttons()
    {
        BuildProfilePanel.getProfilebutton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    JOptionPane.showMessageDialog(null,"Developing!");
            }
        });
        BuildProfilePanel.getChangeinformation().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(MainFrame.getInstance().getClient().getRole() == 2){
                    if(editUserModalController != null){
                        editUserModalController.getJFrame().dispose();
                    }
                    editUserModalController = new EditUserModalController();
                } else if(MainFrame.getInstance().getClient().getRole() == 1) {
                    if(editCoachModalController != null){
                        editCoachModalController.getJFrame().dispose();
                    }
                    editCoachModalController = new EditCoachModalController();
                }
            }
        });
        BuildProfilePanel.getTopup().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //TODO
            }
        });
        BuildProfilePanel.getChangepassword().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ShowCustomDialog dialog = new ShowCustomDialog(userdescription);

                dialog.getBt1().addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        dialog.times++;
                        if (dialog.times % 2 != 0)
                        {
                            dialog.getOpwd().setEchoChar((char) 0);
                            dialog.getNpwd().setEchoChar((char) 0);
                            dialog.getCpwd().setEchoChar((char) 0);
                        }
                        else
                        {
                            dialog.getOpwd().setEchoChar('●');
                            dialog.getNpwd().setEchoChar('●');
                            dialog.getCpwd().setEchoChar('●');
                        }
                    }
                });

                dialog.getCenBtn().addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        dialog.dispose();
                    }
                });

                dialog.getOkBtn().addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (checkpassword(new String(dialog.getOpwd().getPassword())))
                        {
                            if (new String(dialog.getNpwd().getPassword()).equals(new String(dialog.getCpwd().getPassword())))
                            {
                                changepassword(new String(dialog.getNpwd().getPassword()));
                                JOptionPane.showMessageDialog(new JPanel(), "Change Successfully.");
                                dialog.dispose();
                            }
                            else
                                JOptionPane.showMessageDialog(null,"The entered password is different");
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Wrong Password!");
                    }
                });
                dialog.setVisible(true);

            }


        });


    }

//    private void topUp(float money)
//    {
//        user =
//    }

    @Override
    public void update()
    {
        this.user = MainFrame.getInstance().getClient();
        if (user != null)
                userdescription.update();
    }
}
