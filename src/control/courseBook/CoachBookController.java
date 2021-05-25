package control.courseBook;

import control.Controller;
import control.MainFrame;
import model.Coach;
import model.mapping.ClientMapping;
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


public class CoachBookController extends Controller {
    private CoachBookPanel coachBookPanel;

    private int coachID;

    public CoachBookController() {
        super(config.COURSE_BOOK_COACH_NAME, new CoachBookPanel());
        coachBookPanel = (CoachBookPanel) this.panel;

        coachBookPanel.addListener(new CoachBookButtonListener());
        update();
    }

    @Override
    public void update() {
        System.out.println("Coach Book Page update");
        // clear old panel
        coachBookPanel.getDataPanel().removeAll();
        coachBookPanel.getPersonMap().clear();

        HashMap<Integer, PersonPanel> personMap = new HashMap<>();
        search_coaches().forEach(item -> personMap.put(item.getId(), new PersonPanel(item)));
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
    }

    private ArrayList<Coach> search_coaches(){
        HashMap<String, String> searchMap = new HashMap<>();
        searchMap.put("cancel","false");
        // add search information
        if (!coachBookPanel.getSearchInputField().getText().equals(""))
            searchMap.put("nickName", coachBookPanel.getSearchInputField().getText());
        coachBookPanel.getSearchComboBoxMap().forEach((k, v) -> {
            if (!v.getComboBox().getSelectedItem().equals("All")) {
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

    // clear the search information
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
                update();
            }
        }
    }

    public int getCoachID() {
        return coachID;
    }
}


