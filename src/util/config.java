package util;

public interface config {
     int FRAME_WIDTH = 1200;
     int FRAME_HEIGHT = 600;

     int PANEL_WIDTH = FRAME_WIDTH;
     int PANEL_HEIGHT =  FRAME_HEIGHT - 40;

     int PAGE_Y = 50;
     int PAGE_WIDTH = FRAME_WIDTH;
     int PAGE_HEIGHT = PANEL_HEIGHT - PAGE_Y;

     String INDEX_PANEL_NAME = "index";
     String ENROLL_PANEL_NAME = "enroll";
     String FUNCTION_PANEL_NAME = "function";
     String USERDESCRIPTION_PANEL_NAME = "userdes";
     String VIDEOSQUARE_PANEL_NAME="videoSquare";

     String STAFF_MANAGE_NAME = "staffManagement";
     String STAFF_INSERT_NAME = "staffInsert";
     String RECORD_MANAGE_NAME = "recordManagement";

     String COURSE_BOOK_NAME = "courseBook";
     String COURSE_BOOK_COACH_NAME = "courseBookCoach";
     String COURSE_BOOK_TIME_NAME = "corseBookTime";

     String SCHEDULE_NAME ="personalSchedule";

     String VIDEO_MANAGEMENT = "videoManagement";
     String VIDEO_UPLOAD = "videoUpload";
     String VIDEO_MODIFY = "videoModify";

}
