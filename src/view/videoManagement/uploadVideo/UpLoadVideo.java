package view.videoManagement.uploadVideo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class UpLoadVideo extends JPanel {

    protected final Instruction instruction = new Instruction();
    protected UploadForm uploadForm;

    public UpLoadVideo(ArrayList<String> videoTypes){
        super();
        this.initUploadForm(videoTypes);
    }

    public UploadForm getUploadForm() {
        return uploadForm;
    }

    private void initUploadForm(ArrayList<String> videoTypes) {
        this.uploadForm = new UploadForm(videoTypes);
    }

    private void initPanel(){
        this.setBounds(0,0,800,560);
        this.setBackground(new Color(0,0,0));
        this.setLayout(null);
    }

    private void initLayout(){

        this.add(this.instruction);
        this.add(this.uploadForm);

        this.repaint();
    }

    public void showPanel(){
        this.initPanel();
        this.initLayout();
    }

}
