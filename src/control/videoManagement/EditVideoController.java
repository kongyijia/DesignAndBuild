package control.videoManagement;

import control.Controller;
import control.MainFrame;
import it.sauronsoftware.jave.EncoderException;
import model.Video;
import model.mapping.VideoMapping;
import util.Util;
import util.config;
import view.videoManagement.uploadVideo.UpLoadVideo;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class EditVideoController extends UploadVideoController {

    private Video currentVideo;

    public EditVideoController(){
        super(config.VIDEO_MODIFY, new UpLoadVideo(EditVideoController.getTypes()));
        this.modifyUi();
        this.getCurrentVideoFromManagement();
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

    public void modifyUi(){
        this.uploadForm.modifyToEdit();
    }

    public void setVideo(Video video){
        this.uploadForm.setVideoTextField(video.getName());
        this.uploadForm.setVideoTagComboBox(video.getTag());
        ArrayList<String> args = video.getTypes();
        this.uploadForm.setMultiComboBox(args.toArray(new String[0]));
    }

    public void getCurrentVideoFromManagement(){
        Controller c = MainFrame.getInstance().getController(config.VIDEO_MANAGEMENT);
        VideoManagementController v = (VideoManagementController) c;
        Video video = v.getCurrentVideo();
        this.setVideo(video);
        this.setCurrentVideo(video);
    }

    public Video generateVideo(){
        String name = this.uploadForm.getVideoNameTextField();
        int tag = this.uploadForm.getVideoTagComboBox();
        ArrayList<String> types = Util.toArrayList(this.uploadForm.getMultiComboBox());
        this.currentVideo.setName(name);
        this.currentVideo.setTag(tag);
        this.currentVideo.setTypes(types);
        return this.currentVideo;
    }

    public void onConfirm(){
        if (this.uploadForm.getVideoNameTextField().equals("")){
            Util.showDialog(MainFrame.getInstance(), "Your video name cannot be empty");
        } else if (this.uploadForm.getMultiComboBox().length == 0) {
            Util.showDialog(MainFrame.getInstance(), "You must choose at least one type for your video");
        } else {
            try {
                VideoMapping.modify(this.generateVideo());
                MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDelete(){
        try {
            VideoMapping.delete(this.currentVideo.getId());
            MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.onAction(e);
        if(e.getSource() == this.uploadForm.getConfirmButton()){
            this.onConfirm();
        } else if (e.getSource() == this.uploadForm.getDeleteButton()){
            System.out.println("进到delete了");
            this.onDelete();
        }
    }

    public void update(){
        getCurrentVideoFromManagement();
    }

}
