package control.EditPersonalPageModal;

import control.MainFrame;
import model.Coach;

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

    protected void edit(){
        super.edit();
        this.coach.setDescription(this.editPersonalModal.getDescriptionTextArea());
    }
}
