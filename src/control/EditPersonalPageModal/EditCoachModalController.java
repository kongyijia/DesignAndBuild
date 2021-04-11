package control.EditPersonalPageModal;

import model.Coach;

public class EditCoachModalController extends EditPersonalModalController {
    private Coach coach;

    public EditCoachModalController(Coach coach) {
        super(coach);
        this.editPersonalModal.setDescriptionTextArea(coach.getDescription());
        this.setCoach(coach);
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    protected void edit(){
        this.coach.setNickName(this.editPersonalModal.getNickNameTextField());
        this.coach.setSex(this.editPersonalModal.getSexualityComboBox());
        this.coach.setPhone(this.editPersonalModal.getPhoneTextField());
        this.coach.setEmail(this.editPersonalModal.getEmailTextField());
        this.coach.setDescription(this.editPersonalModal.getDescriptionTextArea());
        System.out.println(this.coach.getNickName()+this.coach.getSex());
    }

}
