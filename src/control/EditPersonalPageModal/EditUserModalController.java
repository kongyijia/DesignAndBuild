package control.EditPersonalPageModal;

import control.MainFrame;
import model.User;

public class EditUserModalController extends EditPersonalModalController {
    private User user;

    public EditUserModalController() {
        super();
        this.setUser();
        this.editPersonalModal.setDescriptionTextArea(user.getDescription());
        this.showModal();
    }

    public void setUser() {
        this.user = (User) this.client;
    }

    protected void edit() {
        super.edit();
        this.user.setDescription(this.editPersonalModal.getDescriptionTextArea());
    }
}
