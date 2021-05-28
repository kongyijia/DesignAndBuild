package control.videoManagement;

import model.Video;
import util.config;
import view.videoManagement.uploadVideo.UpLoadVideo;
import view.videoManagement.uploadVideo.UploadForm;

import java.io.IOException;
import java.util.ArrayList;

public class EditVideoController extends UploadVideoController {

    public EditVideoController(){
        super(config.VIDEO_MODIFY, new UpLoadVideo(EditVideoController.getTypes()));
        //this.setVideo(video);
    }

    public void setVideo(Video video){
        this.uploadForm.setVideoTextField(video.getName());
        this.uploadForm.setVideoTagComboBox(video.getTag());
        ArrayList<String> args = video.getTypes();
        this.uploadForm.setMultiComboBox(args.toArray(new String[0]));
        this.uploadForm.setVideoSrcTextField(video.getSrc());
    }


}
