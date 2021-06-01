package view.VideoSquare;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.config;
/**
 *  This is the general view of "video and video management"
 *
 *  @author Xinyu Zhou
 *  @version 1.0
 *  @since 2021/4/27
 */
public class SearchVideo extends JPanel{
	public static final int SEARCH_PANEL_HEIGHT = 50;
	private JTextField searchInputField1;//name
    private JButton searchButton;
    private JButton resetButton;
	private JComboBox<String> comboBox;
	private JComboBox<String> typeComboBox;
    
    public JButton getSearch() {
		return searchButton;
	}

	public JComboBox<String> getTypeComboBox(){
    	return typeComboBox;
	}
    
    public JButton getReset() {
		return resetButton;
	}
    public JTextField getSearchText1() {
		return searchInputField1;
	}
    public JComboBox<String> getTagBox() {
    	return comboBox;
    }

    public void setTypeComboBox(ArrayList<String> args){
    	for(String i : args){
			this.typeComboBox.addItem(i);
		}
	}
    
	    public SearchVideo () {
	    	this.setLayout(null);
	        this.setBackground(Color.lightGray);
	        this.setBounds(0, 0, config.PAGE_WIDTH, SEARCH_PANEL_HEIGHT);
	        
	        init();
	    }

		private void init() {
			searchInputField1= new JTextField();
	        searchInputField1.setBounds(100, 10, 200, SEARCH_PANEL_HEIGHT - 20);
			typeComboBox = new JComboBox<>();
	        typeComboBox.setBounds(400, 10, 200, SEARCH_PANEL_HEIGHT - 20);
	        
	        searchButton = new JButton("Search");
	        searchButton.setBounds(config.PAGE_WIDTH - 100, 10, 80, SEARCH_PANEL_HEIGHT - 20);
	        searchButton.setBackground(Color.white);
	        
	        resetButton = new JButton("Reset");
	        resetButton.setBounds(config.PAGE_WIDTH - 200, 10, 80, SEARCH_PANEL_HEIGHT - 20);
	        resetButton.setBackground(Color.white);

			JLabel labelType = new JLabel("Type" + " :");
	        labelType.setBounds(330, 10, 50, 30);
			JLabel labelTag = new JLabel("Level" + " :");
	        labelTag.setBounds(630, 10, 50, 30);
			JLabel labelName = new JLabel("Name" + " :");
	        labelName.setBounds(30, 10, 50, 30);
	        
	        comboBox = new JComboBox<>(new String[]{"ALL", "1", "2","3","4","5","6","7","8","9","10","11","12"});
	        comboBox.setSelectedIndex(0);
	        comboBox.setBounds(700, 10, 90, 30);
	        comboBox.setBackground(Color.white);

	        this.add(searchInputField1);
	        this.add(typeComboBox);
	        this.add(searchButton);
	        this.add(resetButton);
	        this.add(labelType);
	        this.add(labelTag);
	        this.add(labelName);
	        this.add(comboBox);
	        
		}
	    
	   
	
}
