package view.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VideoSquare extends JPanel{

	private static JButton search = new JButton ("Search");
	private static JTextField searchText = new JTextField(20);
	private static JPanel panel= new JPanel();
    private static JScrollPane scrollpane = new JScrollPane(panel);
    
    public JScrollPane getScrollpane() {
    	return scrollpane;
    }
	public JPanel getPanel() {
		return panel;
	}
	public JButton getSearchButton() {
		return search;
	}
	public JTextField getSearchText() {
		return searchText;
	}
	public VideoSquare() {
		this.setLayout(null);
	    this.setBounds(0, 0, 1200, 560);
	    
		this.add(searchText);
		this.add(search);
		searchText.setBounds(400,20,300,30);
		search.setBounds(720,15,80,30);
		
		initPanel();
		this.setVisible(true);
	}
	private void initPanel() {
		panel.setBounds(0, 80, 1200, 400);
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        scrollpane.setBounds(10,80, 1170, 400);
        
		panel.setPreferredSize(new Dimension(scrollpane.getWidth() - 50, 800));
		this.add(scrollpane);
		panel.revalidate();

	}
	
	public void addListener(ActionListener listener) {
		search.addActionListener(listener);
	}
}
