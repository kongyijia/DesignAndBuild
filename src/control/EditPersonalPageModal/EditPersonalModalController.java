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

    protected void initJFrame(){
        this.jFrame = new JFrame("Edit Personal Info");
        this.jFrame.setBounds(0,0,350,650);
        this.jFrame.add(editPersonalModal);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setResizable(false);
        this.jFrame.setVisible(true);
    }

    protected void bindActionListener(){
        this.editPersonalModal.getConfirmButton().addActionListener(this);
        this.editPersonalModal.getCancelButton().addActionListener(this);
    }

    protected void showModal(){
        this.editPersonalModal.initModalLayout();
        this.bindActionListener();
        this.initJFrame();
    }

    protected void edit(){
        this.client.setNickName(this.editPersonalModal.getNickNameTextField());
        this.client.setSex(this.editPersonalModal.getSexualityComboBox());
        this.client.setPhone(this.editPersonalModal.getPhoneTextField());
        this.client.setEmail(this.editPersonalModal.getEmailTextField());
    }

    protected void onConfirm() throws IOException {
        System.out.println("confirm");
        if(this.editPersonalModal.getNickNameTextField().equals("")){
            Util.showDialog(this.jFrame, "Your nick name cannot be empty!");
        } else if(this.editPersonalModal.getPhoneTextField().equals("")){
            Util.showDialog(this.jFrame, "Your phone number cannot be empty!");
        } else if(this.editPersonalModal.getEmailTextField().equals("")){
            Util.showDialog(this.jFrame, "Your email address cannot be empty!");
        } else if(!Util.isNickNameLegal(this.editPersonalModal.getNickNameTextField())){
            Util.showDialog(this.jFrame, "Your nick name should only consists English words and numbers!");
        } else if(!Util.isPhoneLegal(this.editPersonalModal.getPhoneTextField())){
            Util.showDialog(this.jFrame, "Illegal phone number! Expect 11 numbers!");
        } else if(!Util.isEmailLegal(this.editPersonalModal.getEmailTextField())){
            Util.showDialog(this.jFrame, "Illegal email address! Please try again!");
        } else {
            this.edit();
            if(ClientMapping.modify(this.client) == 6){
                Util.showDialog(this.jFrame,"Your nick name has been used! Please change another one!");
            } else if(ClientMapping.modify(this.client) == 2) {
                Util.showDialog(this.jFrame, "Error! Cannot find your info!");
            } else {
                System.out.println(this.client.getNickName());
                MainFrame.getInstance().setClient(this.client);
                this.jFrame.dispose();
            }
        }
    }

    protected void onCancel(){
        System.out.println("cancel");
        this.jFrame.dispose();
    }

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
