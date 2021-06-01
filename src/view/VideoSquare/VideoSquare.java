package view.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *  This is the view of video search where to show the videos.
 *
 *  @author Xinyu Zhou
 *  @version 1.0
 *  @since 2021/4/27
 */
public class VideoSquare extends JPanel{

	private final JPanel panel= new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(panel);
    private final SearchVideo searchVideoPanel=new SearchVideo();

    public SearchVideo getSearchVideoPanel() { return this.searchVideoPanel; }
    public JComboBox<String> getTagBox() {
    	return searchVideoPanel.getTagBox();
    }
    public JScrollPane getScrollPane() {
    	return scrollPane;
    }
	public JPanel getPanel() {
		return panel;
	}
	public JButton getSearchButton() {
		return searchVideoPanel.getSearch();
	}
	public JButton getResetButton() {
		return searchVideoPanel.getReset();
	}
	public JTextField getSearchText1() {
		return searchVideoPanel.getSearchText1();
	}
	public JComboBox<String> getTypeComboBox() {
		return searchVideoPanel.getTypeComboBox();
	}
	public VideoSquare() {
		this.setLayout(null);
	    this.setBounds(0, 0, 1200, 560);
	    
	    this.add(searchVideoPanel);
	    searchVideoPanel.setBounds(0, 0, 1200,50);
		initPanel();
		this.setVisible(true);
	}
	private void initPanel() {
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        scrollPane.setBounds(10,60, 1170, 450);    
		panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, 800));
		
		this.add(scrollPane);
		panel.revalidate();

	}
	 public void addListener(ActionListener actionListener) {
	        this.getSearchButton().addActionListener(actionListener);
	        this.getResetButton().addActionListener(actionListener);
	    }
	
}
