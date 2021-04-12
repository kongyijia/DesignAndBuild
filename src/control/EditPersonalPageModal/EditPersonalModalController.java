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

        } else if(this.editPersonalModal.getPhoneTextField().equals("")){

        } else if(this.editPersonalModal.getEmailTextField().equals("")){

        } else if(!Util.isNickNameLegal(this.editPersonalModal.getNickNameTextField())){

        } else if(!Util.isPhoneLegal(this.editPersonalModal.getPhoneTextField())){

        } else if(!Util.isEmailLegal(this.editPersonalModal.getEmailTextField())){

        } else {
            this.edit();
            if(ClientMapping.modify(this.client) == 6){

            } else if(ClientMapping.modify(this.client) == 2) {

            } else {
                //MainFrame.getInstance().setClient(this.client);
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
