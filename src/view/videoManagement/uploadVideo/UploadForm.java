package view.videoManagement.uploadVideo;

import view.basicComponents.JLabelPro;
import view.basicComponents.MultiComboBox;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static view.videoManagement.Const.VM_TAG_EXP;


public class UploadForm extends JPanel {

    private final JLabelPro title = new JLabelPro("Settings", 30);
    private final JLabelPro videoNameLabel = new JLabelPro("Video Name: ", 20,0);
    private final JLabelPro videoTagLabel = new JLabelPro("Video Level: ", 20,0);
    private final JLabelPro tagExplain = new JLabelPro(VM_TAG_EXP, 10,0);
    private final JLabelPro videoTypeLabel = new JLabelPro("Video Type: ", 20, 0);
    private final JLabelPro videoSrcLabel = new JLabelPro("Directory: ",20,0);

    private final Integer[] tagArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    private final JTextField videoNameTextField = new JTextField();
    private final JComboBox<Integer> videoTagComboBox = new JComboBox<>(tagArray);
    private MultiComboBox multiComboBox;

    private final JButton addType = new JButton("Add New Type");

    private final JTextField videoSrcTextField = new JTextField();
    private final JButton showFileChooserButton = new JButton("Choose File");
    private final JFileChooser jFileChooser = new JFileChooser();
    
    private final JButton confirmButton = new JButton("Upload");
    private final JButton returnButton = new JButton("Go Back");

    public UploadForm(ArrayList<String> args){
        super();
        this.initMultiComboBox(args);
        this.setJFileChooser();
        this.initPanel();
        this.initLayout();
    }

    public void setVideoTextField(String videoName){
        this.videoNameTextField.setText(videoName);
    }

    public void setVideoTagComboBox(int tag) {
        this.videoTagComboBox.setSelectedIndex(tag);
    }

    public void setMultiComboBox(String[] args){
        this.multiComboBox.setSelectValues(args);
    }

    public void setVideoSrcTextField(String src){
        this.videoSrcTextField.setText(src);
    }

    public void setJFileChooser(){
        this.jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.jFileChooser.setMultiSelectionEnabled(false);
        this.jFileChooser.setFileFilter(new FileNameExtensionFilter("video(*.mp4)","mp4"));
    }

    public String getVideoNameTextField() {
        return this.videoNameTextField.getText();
    }

    public int getVideoTagComboBox() {
        return this.videoTagComboBox.getSelectedIndex() + 1;
    }

    public Object[] getMultiComboBox() {
        return this.multiComboBox.getSelectedValues();
    }

    public MultiComboBox returnMultiComboBox(){
        return this.multiComboBox;
    }

    public JButton getAddType() {
        return this.addType;
    }

    public String getVideoSrcTextField() {
        return this.videoSrcTextField.getText();
    }

    public JButton getShowFileChooserButton(){
        return this.showFileChooserButton;
    }

    public JFileChooser getJFileChooser(){
        return this.jFileChooser;
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    public JButton getReturnButton() {
        return this.returnButton;
    }

    public void initMultiComboBox(ArrayList<String> args){
        String[] types;
        types = args.toArray(new String[0]);
        this.multiComboBox = new MultiComboBox(types);
    }

    public void initPanel(){
        this.setBackground(new Color(248, 245, 245, 255));
        this.setBounds(0,100,800,460);
        this.setLayout(null);
    }

    public void initLayout(){
        
        this.title.setBounds(20,0, 200,50);
        this.videoNameLabel.setBounds(40,50,140, 30);
        this.videoTagLabel.setBounds(40, 100,140,30);
        this.tagExplain.setBounds(400,100,300,30);
        this.videoTypeLabel.setBounds(40,150,140,30);
        this.videoSrcLabel.setBounds(40,200,140,30);
        this.videoNameTextField.setBounds(180,50, 200, 30);
        this.videoTagComboBox.setBounds(180,100,200,30);
        this.multiComboBox.setBounds(180,150,200,30);
        this.addType.setBounds(400,150,150,30);
        this.videoSrcTextField.setBounds(180,200,300,30);
        this.showFileChooserButton.setBounds(500,200,150,30);
        this.confirmButton.setBounds(400,250,150,50);
        this.returnButton.setBounds(610,250,150,50);

        this.add(this.title);
        this.add(this.videoNameLabel);
        this.add(this.videoTagLabel);
        this.add(this.tagExplain);
        this.add(this.videoTypeLabel);
        this.add(this.videoSrcLabel);
        this.add(this.videoNameTextField);
        this.add(this.videoTagComboBox);
        this.add(this.multiComboBox);
        this.add(this.addType);
        this.add(this.videoSrcTextField);
        this.add(this.showFileChooserButton);
        this.add(this.confirmButton);
        this.add(this.returnButton);

        this.repaint();

    }

}
