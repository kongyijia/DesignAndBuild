package control.videoManagement;

import control.EditPersonalPageModal.EditPersonalModalController;
import control.MainFrame;
import control.VideoSquare.VideoSquareController;
import util.config;
import view.videoManagement.videoManagement.VideoManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  This class is inherited from {@link VideoSquareController}
 *  <br>
 *  This class mainly focus on the process of modifying Video information
 *  <br>
 *  It provides some specific entrances for coach and admin to change information of videos uploaded by themselves
 *  <br>
 *
 *  @author Zai Song
 *  @version 1.0
 *  @since 23 April 2021
 */
public class VideoManagementController extends VideoSquareController implements ActionListener {

    private final VideoManagement videoManagement;

    public VideoManagementController() {
        super(config.VIDEO_MANAGEMENT, new VideoManagement());
        videoManagement = (VideoManagement) this.panel;
        this.onSearch();
        this.bind();
    }

    /**
     * add action listener to buttons
     * enable them to react to coach's and admin's action
     */
    public void bind() {
        videoManagement.addListener(this);
        videoManagement.getUploadButton().addActionListener(this);
    }

    /**
     * react to admin's or coach's action
     * specify which part of the UI is currently interact with the admin or coach
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == videoManagement.getSearchButton()) {
            this.onSearch();
        } else if(e.getSource() == videoManagement.getResetButton()) {
            this.onReset();
        } else if(e.getSource() == videoManagement.getUploadButton()){
            MainFrame.getInstance().goTo(config.VIDEO_UPLOAD);
        }
    }
}
