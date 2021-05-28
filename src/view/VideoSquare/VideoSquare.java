package view.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import view.staffManagement.PersonPanel;

public class VideoSquare extends JPanel{

	private static JButton search = new JButton ("Search");
	private static JTextField searchText = new JTextField(20);
	private static JPanel panel= new JPanel();
    private static JScrollPane scrollpane = new JScrollPane(panel);
    private static SearchVideo searchVideoPanel=new SearchVideo();
    
    public JComboBox<String> getTagBox() {
    	return searchVideoPanel.getTagBox();
    }
    public JScrollPane getScrollpane() {
    	return scrollpane;
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
	public JTextField getSearchText2() {
		return searchVideoPanel.getSearchText2();
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
        scrollpane.setBounds(10,60, 1170, 450);    
		panel.setPreferredSize(new Dimension(scrollpane.getWidth() - 50, 800));
		
		this.add(scrollpane);
		panel.revalidate();

	}
	 public void addListener(ActionListener actionListener) {
	        this.getSearchButton().addActionListener(actionListener);
	        this.getResetButton().addActionListener(actionListener);
	    }
	
}
