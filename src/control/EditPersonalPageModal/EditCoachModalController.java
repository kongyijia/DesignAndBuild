package control.EditPersonalPageModal;

import model.Administrator;
import model.Client;
import model.Coach;
import model.User;

import java.io.IOException;

/**
 *  This class is inherited from {@link EditPersonalModalController}
 *  <br>
 *  This class mainly focus on the process of modifying coach information
 *  <br>
 *  It provides some specific methods for user to change coach information
 *  <br>
 *  It is called in {@link control.Userinformation.UserInformationController}
 *
 *  @author Zai Song
 *  @version 1.0
 *  @since 23 April 2021
 */

public class EditCoachModalController extends EditPersonalModalController {
    private Coach coach;

    public EditCoachModalController() {
        super();
        this.setCoach();
        this.editPersonalModal.setDescriptionTextArea(this.coach.getDescription());
        this.showModal();
    }

    public void setCoach() {
        this.coach = (Coach) this.client;
    }

    /**
     * get description the coach write for him/herself
     * <br>
     * set its value into {@link Coach}
     * <br>
     * after this function, local {@link Coach} is updated and ready for written into JSON file
     */
    protected void edit(){
        super.edit();
        this.coach.setDescription(this.editPersonalModal.getDescriptionTextArea());
    }
}
