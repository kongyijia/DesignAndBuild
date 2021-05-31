package control.EditPersonalPageModal;

import model.User;

/**
 *  This class is inherited from {@link EditPersonalModalController}
 *  <br>
 *  This class mainly focus on the process of modifying user information
 *  <br>
 *  It provides some specific methods for user to change their information
 *  <br>
 *  It is called in {@link control.Userinformation.UserInformationController}
 *
 *  @author Zai Song
 *  @version 1.0
 *  @since 23 April 2021
 */

public class EditUserModalController extends EditPersonalModalController {
    private User user;

    public EditUserModalController() {
        super();
        this.setUser();
        this.editPersonalModal.setDescriptionTextArea(this.user.getDescription());
        this.showModal();
    }

    public void setUser() {
        this.user = (User) this.client;
    }

    /**
     * get description the coach write for him/herself
     * <br>
     * set its value into {@link User}
     * <br>
     * after this function, local {@link User} is updated and ready for written into JSON file
     */

    protected void edit() {
        super.edit();
        this.user.setDescription(this.editPersonalModal.getDescriptionTextArea());
    }
}
