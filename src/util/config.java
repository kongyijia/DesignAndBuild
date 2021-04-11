package util;

public interface config {
     int FRAME_WIDTH = 1200;
     int FRAME_HEIGHT = 600;

     int PANEL_WIDTH = FRAME_WIDTH;
     int PANEL_HEIGHT =  FRAME_HEIGHT - 40;

     int PAGE_Y = 50;
     int PAGE_WIDTH = FRAME_WIDTH;
     int PAGE_HEIGHT = FRAME_HEIGHT - PAGE_Y;

     String INDEX_PANEL_NAME = "index";
     String ENROLL_PANEL_NAME = "enroll";
     String FUNCTION_PANEL_NAME = "function";

     String EDIT_PERSONAL_MODAL = "editPersonalModal";
}
