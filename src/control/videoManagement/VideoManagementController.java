package control.videoManagement;

import control.MainFrame;
import control.VideoSquare.VideoSquareController;
import model.Video;
import util.config;
import view.videoManagement.videoManagement.VideoManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoManagementController extends VideoSquareController implements ActionListener {

    private final VideoManagement videoManagement;

    public VideoManagementController() {
        super(config.VIDEO_MANAGEMENT, new VideoManagement());
        videoManagement = (VideoManagement) this.panel;
        this.onSearch();
        this.bind();
    }

    public void bind() {
        videoManagement.addListener(this);
        videoManagement.getUploadButton().addActionListener(this);
    }

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
