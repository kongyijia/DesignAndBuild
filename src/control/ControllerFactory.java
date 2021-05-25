package control;

import control.Userinformation.UserInformationController;
import control.courseBook.CoachBookController;
import control.courseBook.CourseBookController;
import control.courseBook.TimeBookController;
import control.enroll.EnrollController;
import control.function.FunctionController;
import control.index.IndexController;
import control.staffManage.StaffInsertController;
import control.staffManage.StaffManageController;

import control.RecordManage.*;

import control.VideoSquare.VideoSquareController;

import static util.config.*;

import java.io.FileNotFoundException;

/**
 *  This factory will create new controller correspond the view's name.
 *
 *  @version 1.0
 *  @since 16 April 2021
 */

public class ControllerFactory {
    public static Controller create(String name){
        switch (name){
            case INDEX_PANEL_NAME:
                return new IndexController();
            case ENROLL_PANEL_NAME:
                return new EnrollController();
            case USERDESCRIPTION_PANEL_NAME:
                return new UserInformationController();
            case FUNCTION_PANEL_NAME:
                return new FunctionController();
            case VIDEOSQUARE_PANEL_NAME:
			try {
				return new VideoSquareController();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            case STAFF_MANAGE_NAME:
                return new StaffManageController();
            case STAFF_INSERT_NAME:
                return new StaffInsertController();
            case RECORD_MANAGE_NAME:
                return new RecordManageController();
            case COURSE_BOOK_NAME:
                return new CourseBookController();
            case COURSE_BOOK_COACH_NAME:
                return new CoachBookController();
            case COURSE_BOOK_TIME_NAME:
                return new TimeBookController();
        }
        System.out.println("Can find the view:" + name);
        return null;
    }
}
