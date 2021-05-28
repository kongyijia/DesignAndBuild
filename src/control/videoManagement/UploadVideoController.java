package control.videoManagement;

import control.Controller;
import control.MainFrame;
import it.sauronsoftware.jave.EncoderException;
import model.Video;
import model.mapping.VideoMapping;
import model.mapping.VideoTypeMapping;
import util.Util;
import util.config;
import view.videoManagement.AddTypeModal;
import view.videoManagement.uploadVideo.UpLoadVideo;
import view.videoManagement.uploadVideo.UploadForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UploadVideoController extends Controller implements ActionListener {

    protected UpLoadVideo upLoadVideo;
    protected UploadForm uploadForm;
    protected AddTypeModal addTypeModal;

    public UploadVideoController()  {
        super(config.VIDEO_MANAGEMENT, new UpLoadVideo(UploadVideoController.getTypes()));
        init();
    }

    public UploadVideoController(String controllerConfig, JPanel jPanel){
        super(controllerConfig,jPanel);
        init();
    }

    private void init(){
        this.upLoadVideo = (UpLoadVideo) panel;
        this.uploadForm = this.upLoadVideo.getUploadForm();
        this.addTypeModal = new AddTypeModal();
        this.bindListener();
        this.showPanel();
        this.setH_gap(200);
    }

    protected static ArrayList<String> getTypes(){
        try {
            return VideoTypeMapping.readAllVideoTypes();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void bindListener(){
        this.uploadForm.getAddType().addActionListener(this);
        this.uploadForm.getShowFileChooserButton().addActionListener(this);
        this.uploadForm.getConfirmButton().addActionListener(this);
        this.uploadForm.getReturnButton().addActionListener(this);
        this.addTypeModal.getConfirmButton().addActionListener(this);
        this.addTypeModal.getCancelButton().addActionListener(this);
    }

    public void showPanel() {
        this.upLoadVideo.showPanel();
    }

    public int idIncrement() throws FileNotFoundException {
        ArrayList<Video> videos = VideoMapping.readAllVideos();
        int x = 0;
        for(Video v : videos){
            if (v.getId() > x){
                x = v.getId();
            }
        }
        return x + 1;
    }

    private Video generateVideo() throws FileNotFoundException {
        int id = this.idIncrement();
        int author = MainFrame.getInstance().getClient().getId();
        String name = this.uploadForm.getVideoNameTextField();
        int tag = this.uploadForm.getVideoTagComboBox();
        ArrayList<String> types = Util.toArrayList(this.uploadForm.getMultiComboBox());
        return new Video(id,author,name,tag,types);
    }

    private void onConfirm(){
        if (this.uploadForm.getVideoNameTextField().equals("")){
            Util.showDialog(MainFrame.getInstance(), "Your video name cannot be empty");
        } else if (this.uploadForm.getMultiComboBox().length == 0) {
            Util.showDialog(MainFrame.getInstance(), "You must choose at least one type for your video");
        } else if (this.uploadForm.getVideoSrcTextField().equals("")) {
            Util.showDialog(MainFrame.getInstance(), "You must select one video");
        } else {
            try {
                Video video = this.generateVideo();
                String originalSrc = this.uploadForm.getVideoSrcTextField();
                VideoMapping.add(video,originalSrc);
                MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
            } catch (EncoderException | IOException e) {
                e.printStackTrace();
            }
            System.out.println("song");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.uploadForm.getAddType()){
            this.addTypeModal.setVisible(true);
        } else if(e.getSource() == this.uploadForm.getShowFileChooserButton()) {
            JFileChooser jFileChooser = this.uploadForm.getJFileChooser();
            int result  = jFileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                this.uploadForm.setVideoSrcTextField(path);
            }
        }else if(e.getSource() == this.uploadForm.getConfirmButton()){
            this.onConfirm();
        } else if(e.getSource() == this.uploadForm.getReturnButton()){
            MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
        } else if(e.getSource() == this.addTypeModal.getConfirmButton()){
            String arg = this.addTypeModal.getJTextField();
            if(arg.length() == 0){
                Util.showDialog(this.addTypeModal,"The name cannot be empty");
            } else if(!Util.isVideoTypeLegal(arg)){
                Util.showDialog(this.addTypeModal,"The name should only include numbers and English letters");
            } else {
                try {
                    VideoTypeMapping.add(arg);
                    this.uploadForm.returnMultiComboBox().setValues(VideoTypeMapping.readAllVideoTypes().toArray());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else if(e.getSource() == this.addTypeModal.getCancelButton()){
            this.addTypeModal.setVisible(false);
        }

    }

    @Override
    public void update() {
    }
}
