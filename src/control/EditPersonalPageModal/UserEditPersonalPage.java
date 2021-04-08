package control.EditPersonalPageModal;

import model.User;

public class UserEditPersonalPage extends EditPersonalPage {

    private User user;

    public UserEditPersonalPage(User user) {
        super(user);
        this.editPersonalPageModal.setDescriptionTextArea(user.getDescription());
        this.setUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void edit() {
        this.user.setNickName(this.editPersonalPageModal.getNickNameTextField());
        this.user.setSex(this.editPersonalPageModal.getSexualityComboBox());
        this.user.setPhone(this.editPersonalPageModal.getPhoneTextField());
        this.user.setEmail(this.editPersonalPageModal.getEmailTextField());
        this.user.setDescription(this.editPersonalPageModal.getDescriptionTextArea());
    }
}
