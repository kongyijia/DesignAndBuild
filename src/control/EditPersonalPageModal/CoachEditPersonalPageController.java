package control.EditPersonalPageModal;

import model.Coach;

public class CoachEditPersonalPageController extends EditPersonalPage {
    private Coach coach;

    public CoachEditPersonalPageController(Coach coach) {
        super(coach);
        this.editPersonalPageModal.setDescriptionTextArea(coach.getDescription());
        this.setCoach(coach);
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    protected void edit(){
        this.coach.setNickName(this.editPersonalPageModal.getNickNameTextField());
        this.coach.setSex(this.editPersonalPageModal.getSexualityComboBox());
        this.coach.setPhone(this.editPersonalPageModal.getPhoneTextField());
        this.coach.setEmail(this.editPersonalPageModal.getEmailTextField());
        this.coach.setDescription(this.editPersonalPageModal.getDescriptionTextArea());
        System.out.println(this.coach.getNickName()+this.coach.getSex());
    }

}
