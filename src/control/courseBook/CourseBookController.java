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

public class CourseBookController extends Controller {
    private CourseBookPanel courseBookPanel;
    private String courseType = "";

    public CourseBookController() {
        super(config.COURSE_BOOK_NAME, new CourseBookPanel());
        courseBookPanel = (CourseBookPanel) this.panel;

        courseBookPanel.getResetButton().addActionListener(e -> reset());
        courseBookPanel.getSearchButton().addActionListener(e -> showCourses());

        update();
    }

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

    private void reset(){
        courseBookPanel.getSearchInputField().setText("");
    }

    @Override
    public void update() {
        System.out.println("Course Book Page update");
        reset();
        showCourses();
    }

    public String getCourseType() {
        return courseType;
    }
}
