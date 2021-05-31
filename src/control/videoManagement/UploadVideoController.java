package control.videoManagement;

import control.Controller;
import control.MainFrame;
import control.VideoSquare.VideoSquareController;
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

/**
 *  This class is inherited from {@link Controller}
 *  <br>
 *  This class mainly focus on the process of adding new Video information
 *  <br>
 *  It provides some specific method for coach and admin to add new videos
 *  <br>
 *
 *  @author Zai Song
 *  @version 1.0
 *  @since 23 April 2021
 */
public class UploadVideoController extends Controller implements ActionListener {

    protected UpLoadVideo upLoadVideo;
    protected UploadForm uploadForm;
    protected AddTypeModal addTypeModal;

    public UploadVideoController()  {
        super(config.VIDEO_UPLOAD, new UpLoadVideo(UploadVideoController.getTypes()));
        init();
    }

    public UploadVideoController(String controllerConfig, JPanel jPanel){
        super(controllerConfig,jPanel);
        init();
    }

    /**
     * init layout and UI
     * this will generate basic ui for coach and admin to type in some basic info of a new video
     */
    private void init(){
        this.upLoadVideo = (UpLoadVideo) panel;
        this.uploadForm = this.upLoadVideo.getUploadForm();
        this.addTypeModal = new AddTypeModal();
        this.bindListener();
        this.showPanel();
        this.setH_gap(200);
    }

    /**
     * get type name from database
     * @return all the video type name in ArrayList
     */
    protected static ArrayList<String> getTypes(){
        try {
            return VideoTypeMapping.readAllVideoTypes();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bind action listener for buttons
     * enable some buttons to react to users or coaches action
     */
    private void bindListener(){
        this.uploadForm.getAddType().addActionListener(this);
        this.uploadForm.getShowFileChooserButton().addActionListener(this);
        this.uploadForm.getConfirmButton().addActionListener(this);
        this.uploadForm.getReturnButton().addActionListener(this);
        this.uploadForm.getDeleteButton().addActionListener(this);
        this.addTypeModal.getConfirmButton().addActionListener(this);
        this.addTypeModal.getCancelButton().addActionListener(this);
    }

    /**
     * make UI visible to coach and admin
     */
    public void showPanel() {
        this.upLoadVideo.showPanel();
    }

    /**
     * to generate id for the new video
     * @return the least integer that is larger than the largest id in database, use it as the id of new Video
     * @throws FileNotFoundException
     */
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

    /**
     * to generate {@link Video} for the new video
     * @return An object {@link Video} which contains all the info required to add a new video
     * @throws FileNotFoundException
     */
    public Video generateVideo() throws FileNotFoundException {
        int id = this.idIncrement();
        int author = MainFrame.getInstance().getClient().getId();
        String name = this.uploadForm.getVideoNameTextField();
        int tag = this.uploadForm.getVideoTagComboBox();
        ArrayList<String> types = Util.toArrayList(this.uploadForm.getMultiComboBox());
        return new Video(id,author,name,tag,types);
    }

    /**
     * this method is called when admin or coach try to confirm their info for new video
     * validity checking are include in this function
     * if it pass all the checking, the information will be stored
     * @throws IOException
     */
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
        }
    }

    /**
     * to show the file chooser admin or coach used to select source video
     */
    public void onShowFileChooserButton(){
        JFileChooser jFileChooser = this.uploadForm.getJFileChooser();
        int result  = jFileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            this.uploadForm.setVideoSrcTextField(path);
        }
    }

    /**
     * this method is called when admin or coach try to confirm their new video type
     * validity checking are include in this method
     * if it pass all the checking, the information will be stored
     * @throws IOException
     */
    public void onAddTypeModal(){
        String arg = this.addTypeModal.getJTextField();
        if(arg.length() == 0){
            Util.showDialog(this.addTypeModal,"The name cannot be empty");
        } else if(!Util.isVideoTypeLegal(arg)){
            Util.showDialog(this.addTypeModal,"The name should only include numbers and English letters");
        } else {
            try {
                VideoTypeMapping.add(arg);
                this.uploadForm.returnMultiComboBox().setValues(VideoTypeMapping.readAllVideoTypes().toArray());
                this.addTypeModal.setVisible(false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * react to admin's or coach's action
     * specify which part of the UI is currently interact with the user or coach
     * @param e
     */
    public void onAction(ActionEvent e) {
        if(e.getSource() == this.uploadForm.getAddType()){
            this.addTypeModal.setVisible(true);
        } else if(e.getSource() == this.uploadForm.getShowFileChooserButton()) {
            this.onShowFileChooserButton();
        } else if(e.getSource() == this.uploadForm.getReturnButton()){
            MainFrame.getInstance().goTo(config.VIDEO_MANAGEMENT);
        } else if(e.getSource() == this.addTypeModal.getConfirmButton()){
            this.onAddTypeModal();
        } else if(e.getSource() == this.addTypeModal.getCancelButton()){
            this.addTypeModal.setVisible(false);
        }
    }

    /**
     * reset the form
     * clean up all the value in current form
     * replace them with initial value or null or ""
     */
    public void resetAll(){
        this.uploadForm.setVideoTextField("");
        this.uploadForm.setVideoTagComboBox(1);
        this.uploadForm.setMultiComboBox(new String[0]);
        this.uploadForm.setVideoSrcTextField("");
    }

    /**
     * react to admin's or coach's action
     * specify which part of the UI is currently interact with the user or coach
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.onAction(e);
        if(e.getSource() == this.uploadForm.getConfirmButton()){
            this.onConfirm();
        }
    }

    /**
     * every time the main function panel turn to this panel
     * this method will be called
     */
    @Override
    public void update() {
        resetAll();
    }
}
