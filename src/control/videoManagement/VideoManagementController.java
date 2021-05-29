package control.videoManagement;

import control.Controller;
import model.Video;
import util.config;
import view.videoManagement.videoManagement.VideoManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoManagementController extends Controller implements ActionListener {

    private Video currentVideo;
    private final VideoManagement videoManagement;

    public VideoManagementController() {
        super(config.VIDEO_MANAGEMENT, new VideoManagement());
        videoManagement = (VideoManagement) this.panel;
    }

    public VideoManagement getVideoManagement() {
        return videoManagement;
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }


    @Override
    public void update() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
