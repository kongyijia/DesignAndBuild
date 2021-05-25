package control.courseBook;

import control.Controller;
import control.MainFrame;
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

        update();
    }

    private ArrayList<String> getAllType() {
        ArrayList<String> types = new ArrayList<>();
        try {
            types = VideoTypeMapping.readAllVideoTypes();
        } catch (Exception e){
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public void update() {
        System.out.println("Course Book Page update");
        // clear old panel
        courseBookPanel.getDataPanel().removeAll();
        courseBookPanel.getCourseMap().clear();

        HashMap<String, SingleCoursePanel> courseMap = new HashMap<>();
        getAllType().forEach(item -> courseMap.put(item, new SingleCoursePanel(item)));
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
    }
}
