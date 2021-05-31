package control.courseBook;

import control.Controller;
import control.MainFrame;
import util.Util;
import view.courseBook.CourseBookPanel;
import model.mapping.VideoTypeMapping;
import util.config;
import view.courseBook.SingleCoursePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @description This class is used to control view display and data interaction of {@link CourseBookPanel}
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 16 May 2021
 */

public class CourseBookController extends Controller {
    private final CourseBookPanel courseBookPanel;
    private String courseType = "";

    public CourseBookController() {
        super(config.COURSE_BOOK_NAME, new CourseBookPanel());
        courseBookPanel = (CourseBookPanel) this.panel;

        courseBookPanel.getResetButton().addActionListener(e -> reset());
        courseBookPanel.getSearchButton().addActionListener(e -> showCourses());

        update();
    }

    /**
     * This method is used to search for course types that meet the filter criteria.
     *
     * @return Course types which meet the filter criteria
     */
    private ArrayList<String> searchTypes() {
        ArrayList<String> types = new ArrayList<>();
        String type = courseBookPanel.getSearchInputField().getText();

        try {
            if (type != null && !type.equals("")) {
                types = VideoTypeMapping.search(type);
            }
            else
                types = VideoTypeMapping.readAllVideoTypes();
        } catch (Exception e){
            e.printStackTrace();
        }
        return types;
    }

    /**
     * This method will use {@link SingleCoursePanel} to display qualified coach information in {@link CourseBookPanel} page
     */
    private void showCourses() {
        courseBookPanel.getDataPanel().removeAll();
        courseBookPanel.getCourseMap().clear();

        HashMap<String, SingleCoursePanel> courseMap = new HashMap<>();
        ArrayList<String> types = searchTypes();

        if (types.size() == 0){
            courseBookPanel.updateUI();
            Util.showDialog(courseBookPanel, "No qualified course type were found!");
            return;
        }

        types.forEach(item -> courseMap.put(item, new SingleCoursePanel(item)));
        courseBookPanel.setCourseMap(courseMap);

        courseBookPanel.getCourseMap().forEach((k, v) -> {
            courseBookPanel.getDataPanel().add(v);
            v.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getClickCount() == 2) {
                        courseType = k;
                        MainFrame.getInstance().goTo(config.COURSE_BOOK_COACH_NAME);
                    }
                }
            });
        });

        if (types.size() < 5) {
            // use blank panel to occupy space
            for (int i = 0; i < 5 - types.size(); i++) {
                courseBookPanel.getDataPanel().add(new SingleCoursePanel());
            }
        }

        courseBookPanel.updateUI();
    }

    /**
     * This method is used to clear the search information
     */
    private void reset(){
        courseBookPanel.getSearchInputField().setText("");
    }

    /**
     * This method is used to refresh the {@link CourseBookPanel} page
     */
    @Override
    public void update() {
        reset();
        showCourses();
    }

    /**
     * This method is used to get currently selected course type
     *
     * @return Currently selected course type
     */
    public String getCourseType() {
        return courseType;
    }
}
