package control.videoManagement;

import control.MainFrame;
import model.Video;
import model.mapping.VideoMapping;
import model.mapping.VideoTypeMapping;
import util.Util;
import view.videoManagement.AddTypeModal;
import view.videoManagement.uploadVideo.UpLoadVideo;
import view.videoManagement.uploadVideo.UploadForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UploadVideoController implements ActionListener {

    protected UpLoadVideo upLoadVideo;
    protected UploadForm uploadForm;
    protected AddTypeModal addTypeModal;

    public UploadVideoController() throws IOException {
        this.upLoadVideo = new UpLoadVideo(this.getTypes());
        this.uploadForm = this.upLoadVideo.getUploadForm();
        this.addTypeModal = new AddTypeModal();
        this.bindListener();
    }

    private ArrayList<String> getTypes() throws IOException {
        return VideoTypeMapping.readAllVideoTypes();
    }

    private void bindListener(){
        this.uploadForm.getAddType().addActionListener(this);
        this.uploadForm.getConfirmButton().addActionListener(this);
        this.uploadForm.getReturnButton().addActionListener(this);
        this.addTypeModal.getConfirmButton().addActionListener(this);
        this.addTypeModal.getCancelButton().addActionListener(this);
    }

    public void showPanel() {
        this.upLoadVideo.showPanel();
    }

    private int generateId() throws FileNotFoundException {
        return VideoMapping.idIncrement() + 1;
    }

    private Video generateVideo() throws FileNotFoundException {
        int id = this.generateId();
        String name = this.uploadForm.getVideoNameTextField();
        int tag = this.uploadForm.getVideoTagComboBox();
        String src = this.uploadForm.getVideoSrcTextField();
        ArrayList<String> types = Util.toArrayList(this.uploadForm.getMultiComboBox());
        return new Video(id,name,src,tag,types,0,"");
    }

    private void onConfirm(){
        if (this.uploadForm.getVideoNameTextField().equals("")){
            Util.showDialog(MainFrame.getInstance(), "Your video name cannot be empty");
        } else {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.uploadForm.getAddType()){
            this.addTypeModal.setVisible(true);
        } else if(e.getSource() == this.uploadForm.getConfirmButton()){

        } else if(e.getSource() == this.uploadForm.getReturnButton()){

        } else if(e.getSource() == this.addTypeModal.getConfirmButton()){
            String arg = this.addTypeModal.getJTextField();
            if(!Util.isVideoTypeLegal(arg)){
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
}
