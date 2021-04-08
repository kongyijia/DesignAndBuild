package control.EditPersonalPageModal;

import model.Client;
import view.editPersonalPageModal.EditPersonalPageModal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPersonalPage implements ActionListener {

    protected EditPersonalPageModal editPersonalPageModal;

    public EditPersonalPage(Client client){
        this.showModal(client);
    }

    protected void bindActionListener(){
        this.editPersonalPageModal.getConfirmButton().addActionListener(this);
        this.editPersonalPageModal.getCancelButton().addActionListener(this);
    }

    protected void showModal(Client client){
        this.editPersonalPageModal = new EditPersonalPageModal(client);
        this.editPersonalPageModal.initModalLayout();
        this.bindActionListener();
    }

    protected void edit(){}

    protected void onConfirm(){
        this.edit();
        System.out.println("confirm");
    }

    protected void onCancel(){
        System.out.println("cancel");
        this.editPersonalPageModal.getJFrame().dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.editPersonalPageModal.getConfirmButton()){
            this.onConfirm();
        } else if(e.getSource() == this.editPersonalPageModal.getCancelButton()){
            this.onCancel();
        }
    }
}
