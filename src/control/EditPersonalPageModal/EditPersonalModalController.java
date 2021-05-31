package control.EditPersonalPageModal;

import control.MainFrame;

import model.Client;
import model.mapping.ClientMapping;
import util.Util;
import view.editPersonalModal.EditPersonalModal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *  This class is the superclass of {@link EditUserModalController} and {@link EditCoachModalController}
 *  <br>
 *  This class mainly focus on modifying personal information
 *  <br>
 *  It provides some general methods for other classes to control the process of changing personal information
 *  <br>
 *
 *  @author Zai Song
 *  @version 1.0
 *  @since 23 April 2021
 */

public class EditPersonalModalController implements ActionListener {

    protected EditPersonalModal editPersonalModal;
    protected JFrame jFrame;

    protected Client client;

    public EditPersonalModalController(){
        this.setClient();
        this.editPersonalModal = new EditPersonalModal(this.client);
    }

    private void setClient(){
        this.client = MainFrame.getInstance().getClient();
    }

    public JFrame getJFrame() {
        return jFrame;
    }

    /**
     * initial JFrame, the UI for user or coach to edit their personal info
     */
    protected void initJFrame(){
        this.jFrame = new JFrame("Edit Personal Info");
        this.jFrame.setBounds(0,0,350,650);
        this.jFrame.add(editPersonalModal);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setResizable(false);
        this.jFrame.setVisible(true);
    }

    /**
     * bind action listener for buttons
     * enable some buttons to react to users or coaches action
     */
    protected void bindActionListener(){
        this.editPersonalModal.getConfirmButton().addActionListener(this);
        this.editPersonalModal.getCancelButton().addActionListener(this);
    }

    /**
     * show UI when users and coaches want to
     */
    protected void showModal(){
        this.editPersonalModal.initModalLayout();
        this.bindActionListener();
        this.initJFrame();
    }

    /**
     * get some general values that users or coaches just changed
     * set them into Client
     * when this finished, local variable {@link Client} is updated
     */
    protected void edit(){
        this.client.setNickName(this.editPersonalModal.getNickNameTextField());
        this.client.setSex(this.editPersonalModal.getSexualityComboBox());
        this.client.setPhone(this.editPersonalModal.getPhoneTextField());
        this.client.setEmail(this.editPersonalModal.getEmailTextField());
    }

    /**
     * this function is called when user or coach try to confirm their changes on their info
     * validity checking are include in this function
     * if it pass all the checking, the information will be stored
     * @throws IOException
     */
    protected void onConfirm() throws IOException {
        if(this.editPersonalModal.getNickNameTextField().equals("")){
            Util.showDialog(this.jFrame, "Your nick name cannot be empty!");
        } else if(this.editPersonalModal.getPhoneTextField().equals("")){
            Util.showDialog(this.jFrame, "Your phone number cannot be empty!");
        } else if(this.editPersonalModal.getEmailTextField().equals("")){
            Util.showDialog(this.jFrame, "Your email address cannot be empty!");
        } else if(!Util.isNickNameLegal(this.editPersonalModal.getNickNameTextField())){
            Util.showDialog(this.jFrame, "Your nick name should only consists English words and numbers!");
        } else if(Util.isPhoneLegal(this.editPersonalModal.getPhoneTextField()) == 0){
            Util.showDialog(this.jFrame, "Illegal phone number! Expect 11 numbers!");
        } else if(Util.isPhoneLegal(this.editPersonalModal.getPhoneTextField()) == -1) {
            Util.showDialog(this.jFrame, "Illegal phone number! Only numbers allowed!");
        } else if(!Util.isEmailLegal(this.editPersonalModal.getEmailTextField())){
            Util.showDialog(this.jFrame, "Illegal email address! Please try again!");
        } else {
            this.edit();
            if(ClientMapping.modify(this.client) == 6){
                Util.showDialog(this.jFrame,"Your nick name has been used! Please change another one!");
            } else if(ClientMapping.modify(this.client) == 2) {
                Util.showDialog(this.jFrame, "Error! Cannot find your info!");
            } else {
                MainFrame.getInstance().setClient(this.client);
                this.jFrame.dispose();
            }
        }
    }

    /**
     * interface for exiting this function
     */
    protected void onCancel(){
        this.jFrame.dispose();
    }

    /**
     * react to user's or coach's action
     * specify which part of the UI is currently interact with the user or coach
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.editPersonalModal.getConfirmButton()){
            try {
                this.onConfirm();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if(e.getSource() == this.editPersonalModal.getCancelButton()){
            this.onCancel();
        }
    }

}
