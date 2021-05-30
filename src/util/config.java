package util;

import javax.swing.*;
import java.util.Locale;

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

     Object lock = new Object();

     static void init()
     {
          Locale.setDefault(Locale.ENGLISH);
          sun.awt.AppContext.getAppContext().put("JComponent.defaultLocale", Locale.ENGLISH);

          UIManager.put("FileChooser.openDialogTitleText", "Open");
          UIManager.put("FileChooser.lookInLabelText", "LookIn");
          UIManager.put("FileChooser.openButtonText", "Open");
          UIManager.put("FileChooser.cancelButtonText", "Cancel");
          UIManager.put("FileChooser.fileNameLabelText", "FileName");
          UIManager.put("FileChooser.filesOfTypeLabelText", "TypeFiles");
          UIManager.put("FileChooser.openButtonToolTipText", "OpenSelectedFile");
          UIManager.put("FileChooser.cancelButtonToolTipText", "Cancel");
          UIManager.put("FileChooser.fileNameHeaderText", "FileName");
          UIManager.put("FileChooser.upFolderToolTipText", "UpOneLevel");
          UIManager.put("FileChooser.homeFolderToolTipText", "Desktop");
          UIManager.put("FileChooser.newFolderToolTipText", "CreateNewFolder");
          UIManager.put("FileChooser.listViewButtonToolTipText", "List");
          UIManager.put("FileChooser.newFolderButtonText", "CreateNewFolder");
          UIManager.put("FileChooser.renameFileButtonText", "RenameFile");
          UIManager.put("FileChooser.deleteFileButtonText", "DeleteFile");
          UIManager.put("FileChooser.filterLabelText", "TypeFiles");
          UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");
          UIManager.put("FileChooser.fileSizeHeaderText", "Size");
          UIManager.put("FileChooser.fileDateHeaderText", "DateModified");
     }

}
