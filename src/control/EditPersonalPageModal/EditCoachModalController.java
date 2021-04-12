package control.EditPersonalPageModal;

import control.MainFrame;
import model.Coach;

public class EditCoachModalController extends EditPersonalModalController {
    private Coach coach;

    public EditCoachModalController() {
        super();
        this.setCoach();
        this.editPersonalModal.setDescriptionTextArea(coach.getDescription());
    }

    public void setCoach() {
        this.coach = (Coach) MainFrame.getInstance().getClient();
    }

    protected void edit(){
        this.coach.setNickName(this.editPersonalModal.getNickNameTextField());
        this.coach.setSex(this.editPersonalModal.getSexualityComboBox());
        this.coach.setPhone(this.editPersonalModal.getPhoneTextField());
        this.coach.setEmail(this.editPersonalModal.getEmailTextField());
        this.coach.setDescription(this.editPersonalModal.getDescriptionTextArea());
        MainFrame.getInstance().setClient(this.coach);
    }
}
