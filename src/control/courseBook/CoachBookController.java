package control.courseBook;

import control.Controller;
import control.MainFrame;
import model.Coach;
import model.mapping.ClientMapping;
import util.Util;
import util.config;
import view.courseBook.CoachBookPanel;
import view.staffManagement.PersonPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class is used to control view display and data interaction of {@link CoachBookPanel}
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class CoachBookController extends Controller {
    private final CoachBookPanel coachBookPanel;

    private int coachID;

    public CoachBookController() {
        super(config.COURSE_BOOK_COACH_NAME, new CoachBookPanel());
        coachBookPanel = (CoachBookPanel) this.panel;

        coachBookPanel.addListener(new CoachBookButtonListener());
        update();
    }

    /**
     * This method is used to refresh the {@link CoachBookPanel} page
     */
    @Override
    public void update() {
        search_reset();
        showCoachInfo();

        coachBookPanel.updateUI();
    }

    /**
     * This method will use {@link PersonPanel} to display qualified coach information in {@link CoachBookPanel} page
     */
    private void showCoachInfo(){
        // clear old panel
        coachBookPanel.getDataPanel().removeAll();
        coachBookPanel.getPersonMap().clear();

        HashMap<Integer, PersonPanel> personMap = new HashMap<>();
        ArrayList<Coach> coaches = search_coaches();
        if (coaches.size() == 0){
            Util.showDialog(coachBookPanel, "No qualified coach were found!");
            return;
        }

        coaches.forEach(item -> personMap.put(item.getId(), new PersonPanel(item)));
        coachBookPanel.setPersonMap(personMap);
        coachBookPanel.getPersonMap().forEach((k, v) ->{
            v.getDeleteButton().setVisible(false);
            v.getDetailButton().setVisible(false);
            coachBookPanel.getDataPanel().add(v);
            v.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getClickCount() == 2) {
                        coachID = k;
                        MainFrame.getInstance().goTo(config.COURSE_BOOK_TIME_NAME);
                    }
                }
            });
        });

        if (coaches.size() < 5) {
            // use blank panel to occupy space
            for (int i = 0; i < 5 - coaches.size(); i++) {
                coachBookPanel.getDataPanel().add(new PersonPanel());
            }
        }
    }

    /**
     * This method is used to search for coaches that meet the filter criteria.
     *
     * @return Coaches who meet the filter criteria
     */
    private ArrayList<Coach> search_coaches(){
        HashMap<String, String> searchMap = new HashMap<>();
        searchMap.put("cancel","false");
        // add search information
        if (!coachBookPanel.getSearchInputField().getText().equals(""))
            searchMap.put("nickName", coachBookPanel.getSearchInputField().getText());
        coachBookPanel.getSearchComboBoxMap().forEach((k, v) -> {
            if (!"All".equals(v.getComboBox().getSelectedItem())) {
                if (k.equals("sex"))
                    searchMap.put(k, Objects.equals(v.getComboBox().getSelectedItem(), "male") ? "1" : "0");
                else if (k.equals("level"))
                    searchMap.put(k,  (v.getComboBox().getSelectedIndex() - 1) + "");
                else
                    searchMap.put(k, (String) v.getComboBox().getSelectedItem());
            }
        });
        // search coaches
        ArrayList<Coach> coaches = new ArrayList<>();
        try {
            coaches = ClientMapping.findCoach(searchMap);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return coaches;
    }

    /**
     * This method is used to clear the search information
     */
    private void search_reset() {
        coachBookPanel.getSearchComboBoxMap().forEach((k, v) -> v.getComboBox().setSelectedIndex(0));
        coachBookPanel.getSearchInputField().setText("");
    }

    class CoachBookButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == coachBookPanel.getBackButton()){
                MainFrame.getInstance().goTo(config.COURSE_BOOK_NAME);
            }
            else if (e.getSource() == coachBookPanel.getResetButton()){
                search_reset();
            }
            else if (e.getSource() == coachBookPanel.getSearchButton()){
                showCoachInfo();
                coachBookPanel.updateUI();
            }
        }
    }

    public int getCoachID() {
        return coachID;
    }
}


