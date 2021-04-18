package control.videoManagement;

import model.Video;

import java.io.IOException;
import java.util.ArrayList;

public class EditVideoController extends UploadVideoController {

    public EditVideoController(Video video) throws IOException {
        super();
        this.setVideo(video);
    }

    private void setVideo(Video video){
        this.uploadForm.setVideoTextField(video.getName());
        this.uploadForm.setVideoTagComboBox(video.getTag());
        ArrayList<String> args = video.getTypes();
        this.uploadForm.setMultiComboBox(args.toArray(new String[args.size()]));
        this.uploadForm.setVideoSrcTextField(video.getSrc());
    }


}
