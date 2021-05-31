package view.videoManagement.videoManagement;

import util.config;
import view.VideoSquare.VideoSquare;

import javax.swing.*;

import java.awt.*;

import static view.VideoSquare.SearchVideo.SEARCH_PANEL_HEIGHT;

/**
 * UI for video management
 *
 * @author Zai Song
 * @version 1.0
 * @since 23 April 2021
 */
public class VideoManagement extends VideoSquare {

    private final JButton uploadButton = new JButton("Add New");

    public VideoManagement(){
        super();
        this.addUploadButton();
    }

    public void addUploadButton(){
        this.uploadButton.setBounds(config.PAGE_WIDTH - 350, 10, 130, SEARCH_PANEL_HEIGHT - 20);
        this.uploadButton.setBackground(Color.white);
        this.getSearchVideoPanel().add(this.uploadButton);
        this.getSearchVideoPanel().repaint();
    }

    public JButton getUploadButton() {
        return uploadButton;
    }
}
