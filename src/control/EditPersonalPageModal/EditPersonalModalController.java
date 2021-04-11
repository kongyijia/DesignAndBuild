package control.EditPersonalPageModal;

import control.Controller;
import model.Client;
import util.config;
import view.editPersonalModal.EditPersonalModal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPersonalModalController extends Controller implements ActionListener {

    protected EditPersonalModal editPersonalModal;
    protected JFrame jFrame;

    public EditPersonalModalController(Client client){
        super(config.EDIT_PERSONAL_MODAL, new EditPersonalModal(client));
        this.showModal();
    }

    protected void initJFrame(){
        this.jFrame = new JFrame("Edit Personal Info");
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
        this.editPersonalModal = (EditPersonalModal) this.panel;
        this.editPersonalModal.initModalLayout();
        this.bindActionListener();
        this.initJFrame();
    }

    protected void edit(){}

    protected void onConfirm(){
        this.edit();
        System.out.println("confirm");
    }

    protected void onCancel(){
        System.out.println("cancel");
        this.jFrame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.editPersonalModal.getConfirmButton()){
            this.onConfirm();
        } else if(e.getSource() == this.editPersonalModal.getCancelButton()){
            this.onCancel();
        }
    }

    @Override
    public void update() {

    }
}
