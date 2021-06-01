package control.videoManagement;

import control.Controller;
import control.MainFrame;
import model.Video;
import model.mapping.VideoMapping;
import util.Util;
import util.config;
import view.videoManagement.uploadVideo.UpLoadVideo;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  This class is inherited from {@link Controller}
 *  <br>
 *  This class mainly focus on the process of modifying new Video information
 *  <br>
 *  It provides some specific method for coach and admin to modify videos
 *  <br>
 *
 *  @author Zai Song
 *  @version 1.0
 *  @since 23 April 2021
 */
public class EditVideoController extends UploadVideoController {

    private Video currentVideo;

    public EditVideoController(){
        super(config.VIDEO_MODIFY, new UpLoadVideo(EditVideoController.getTypes()));
        this.modifyUi();
        this.getCurrentVideoFromManagement();
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

    public void modifyUi(){
        this.uploadForm.modifyToEdit();
    }

    /**
     * init form value based on selected video
     * @param video
     */
    public void setVideo(Video video){
        this.uploadForm.setVideoTextField(video.getName());
        this.uploadForm.setVideoTagComboBox(video.getTag());
        ArrayList<String> args = video.getTypes();
        this.uploadForm.setMultiComboBox(args.toArray(new String[0]));
    }

    /**
     * get selected video info from {@link VideoManagementController}
     */
    public void getCurrentVideoFromManagement(){
        Controller c = MainFrame.getInstance().getController(config.VIDEO_MANAGEMENT);
        VideoManagementController v = (VideoManagementController) c;
        Video video = v.getCurrentVideo();
        this.setVideo(video);
        this.setCurrentVideo(video);
    }

    /**
     * to generate new object {@link Video} containing info for new info
     * @return
     */
    public Video generateVideo(){
        String name = this.uploadForm.getVideoNameTextField();
        int tag = this.uploadForm.getVideoTagComboBox();
        ArrayList<String> types = Util.toArrayList(this.uploadForm.getMultiComboBox());
        this.currentVideo.setName(name);
        this.currentVideo.setTag(tag);
        this.currentVideo.setTypes(types);
        return this.currentVideo;
    }

    /**
     * this method is called when admin or coach try to confirm their info for changing
     * validity checking are include in this function
     * if it pass all the checking, the information will be stored
     * @throws IOException
     */
    public void onConfirm(){
        if (this.uploadForm.getVideoNameTextField().equals("")){
            Util.showDialog(MainFrame.getInstance(), "Your video name cannot be empty");
        } else if (this.uploadForm.getMultiComboBox().length == 0 || this.uploadForm.getMultiComboBox()[0].equals("")) {
            Util.showDialog(MainFrame.getInstance(), "You must choose at least one type for your video");
        } else {
            try {
                int result = VideoMapping.modify(this.generateVideo());
                if(result == 0){
                    MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
                } else {
                    Util.showDialog(null,"Edit failed. Please try again");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * this method is to delete existing video
     */
    public void onDelete(){
        try {
            VideoMapping.delete(this.currentVideo.getId());
            MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * react to amdin's or coach's action
     * specify which part of the UI is currently interact with the user or coach
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.onAction(e);
        if(e.getSource() == this.uploadForm.getConfirmButton()){
            this.onConfirm();
        } else if (e.getSource() == this.uploadForm.getDeleteButton()){
            this.onDelete();
        }
    }

    /**
     * every time the main function panel turn to this panel
     * this method will be called
     * call the get method to init form data
     */
    public void update(){
        this.getCurrentVideoFromManagement();
    }

}
