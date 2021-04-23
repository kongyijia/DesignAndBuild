package control;

import control.Userinformation.RecordPanelController;
import control.Userinformation.UserInformationController;
import control.enroll.EnrollController;
import control.function.FunctionController;
import control.index.IndexController;
import control.staffManage.StaffInsertController;
import control.staffManage.StaffManageController;

import control.RecordManage.*;

import static util.config.*;

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
            case STAFF_MANAGE_NAME:
                return new StaffManageController();
            case STAFF_INSERT_NAME:
                return new StaffInsertController();
            case RECORD_MANAGE_NAME:
                return new RecordManageController();
            case RECORD_PANEL_NAME:
                return new RecordPanelController();
        }
        System.out.println("Can find the view:" + name);
        return null;
    }
}
