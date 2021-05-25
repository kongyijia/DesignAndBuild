package control.courseBook;

import control.Controller;
import util.config;
import view.courseBook.TimeBookPanel;

public class TimeBookController extends Controller {
    private TimeBookPanel timeBookPanel;

    public TimeBookController(){
        super(config.COURSE_BOOK_TIME_NAME, new TimeBookPanel());
        timeBookPanel = (TimeBookPanel) this.panel;
    }
    @Override
    public void update() {

    }
}
