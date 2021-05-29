package control.Userinformation;

import control.Controller;
import control.EditPersonalPageModal.EditCoachModalController;
import control.EditPersonalPageModal.EditUserModalController;
import control.MainFrame;
import model.Client;
import model.User;
import model.mapping.ClientMapping;
import util.config;
import view.Userinformation.UserDescription;
import view.Userinformation.BuildProfilePanel;
import view.Userinformation.ShowCustomDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Locale;
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
        try
        {
            ClientMapping.modify(user);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

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
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setApproveButtonText("Open");
                // Set the default displayed folder to the current folder
//                fileChooser.setCurrentDirectory(new File("."));

                // Sets the mode for file selection (select only files, select only folders, files and files are optional)
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                // Sets whether multiple selection is allowed
                fileChooser.setMultiSelectionEnabled(false);

                // Set the file filter used by default
                fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png)", "jpg", "png"));

                // Open the file selection box (the thread will be blocked until the selection box is closed)
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION)
                {
                    // If you click OK, get the selected file path
                    File file = fileChooser.getSelectedFile();
                    String path = file.getAbsolutePath();
                    try
                    {
                        int returnValue = ClientMapping.modifyAvatar(user.getId(), path);
                        if (returnValue == ClientMapping.SUCCESS) {
                            JOptionPane.showMessageDialog(null,"Done!");
                            MainFrame.getInstance().setClient(user.getId());
                        } else if (returnValue == ClientMapping.NOT_IMAGE) {
                            JOptionPane.showMessageDialog(null,"Format Error!");
                        }
                    } catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }

//                    String cwd = System.getProperty("user.dir");
//                    System.out.println(new File(cwd).toURI().relativize(file.toURI()).getPath());
                }
            }
        });
        BuildProfilePanel.getPersonalInformation().addActionListener(new ActionListener()
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
                String inputContent = JOptionPane.showInputDialog(null, "Enter the top-up amount:", "0");
                if (inputContent != null)
                {
                    try
                    {
                        double money = Double.parseDouble(inputContent);
                        DecimalFormat moneyTem = new DecimalFormat(".00");
                        money = Double.parseDouble(moneyTem.format(money));
                        if (checkAccount(money))
                        {
                            User temUser = (User) user;
                            double oriMoney = temUser.getAccount();
                            temUser.setAccount(oriMoney + money);
                            try
                            {
                                ClientMapping.modify(user);
                            } catch (IOException ioException)
                            {
                                ioException.printStackTrace();
                            }
                        } else
                            JOptionPane.showMessageDialog(null, "Wrong Number!", "ERROR", JOptionPane.WARNING_MESSAGE);
                        MainFrame.getInstance().setClient(user);
                    } catch (NumberFormatException er)
                    {
                        JOptionPane.showMessageDialog(null, "Wrong Number!");
                    }
                }
            }
        });
        BuildProfilePanel.getChangePassword().addActionListener(new ActionListener()
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

    private boolean checkAccount(double money)
    {
        if (money > 0 && money <= 5000)
            return true;
        return false;
    }

    @Override
    public void update()
    {
        this.user = MainFrame.getInstance().getClient();
        if (user != null)
        {
            userdescription.update();
        }
    }
}
