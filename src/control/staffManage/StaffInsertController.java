package control.staffManage;

import control.Controller;
import control.MainFrame;
import util.config;
import view.staffManagement.StaffInsertPanel;

public class StaffInsertController extends Controller {
    private StaffInsertPanel staffInsertPanel;

    public StaffInsertController() {
        super(config.STAFF_INSERT_NAME, new StaffInsertPanel());
        this.staffInsertPanel = (StaffInsertPanel) this.panel;

        this.setH_gap(300);

        staffInsertPanel.addListener(e -> {
            if (e.getSource() == staffInsertPanel.getBackButton())
                MainFrame.getInstance().goTo(config.STAFF_MANAGE_NAME);
            else if (e.getSource() == staffInsertPanel.getConfirmButton()) {

            }
        });
    }

    @Override
    public void update() {
        System.out.println(config.STAFF_INSERT_NAME + "update");
    }

}
