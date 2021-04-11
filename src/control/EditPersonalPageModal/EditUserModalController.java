package control.EditPersonalPageModal;

import model.User;

public class EditUserModalController extends EditPersonalModalController {

    private User user;

    public EditUserModalController(User user) {
        super(user);
        this.editPersonalModal.setDescriptionTextArea(user.getDescription());
        this.setUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void edit() {
        this.user.setNickName(this.editPersonalModal.getNickNameTextField());
        this.user.setSex(this.editPersonalModal.getSexualityComboBox());
        this.user.setPhone(this.editPersonalModal.getPhoneTextField());
        this.user.setEmail(this.editPersonalModal.getEmailTextField());
        this.user.setDescription(this.editPersonalModal.getDescriptionTextArea());
    }
}
