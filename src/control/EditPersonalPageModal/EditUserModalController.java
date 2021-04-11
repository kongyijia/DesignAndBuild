package control.EditPersonalPageModal;

import control.MainFrame;
import model.User;

public class EditUserModalController extends EditPersonalModalController {
    private User user;

    public EditUserModalController() {
        super();
        this.setUser();
        this.editPersonalModal.setDescriptionTextArea(user.getDescription());
    }

    public void setUser() {
        this.user = (User) MainFrame.getInstance().getClient();
    }

    protected void edit() {
        this.user.setNickName(this.editPersonalModal.getNickNameTextField());
        this.user.setSex(this.editPersonalModal.getSexualityComboBox());
        this.user.setPhone(this.editPersonalModal.getPhoneTextField());
        this.user.setEmail(this.editPersonalModal.getEmailTextField());
        this.user.setDescription(this.editPersonalModal.getDescriptionTextArea());
        MainFrame.getInstance().setClient(this.user);
    }
}
