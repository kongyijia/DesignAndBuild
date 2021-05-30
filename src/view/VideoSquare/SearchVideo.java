package view.VideoSquare;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.config;

public class SearchVideo extends JPanel{
	public static final int SEARCH_PANEL_HEIGHT = 50;
	private JTextField searchInputField1;//name
	private JTextField searchInputField2;//tag
    private JButton searchButton;
    private JButton resetButton;
	private JComboBox<String> comboBox;
    
    public JButton getSearch() {
		return searchButton;
	}
    
    public JButton getReset() {
		return resetButton;
	}
    public JTextField getSearchText1() {
		return searchInputField1;
	}
    public JTextField getSearchText2() {
		return searchInputField2;
	}
    public JComboBox<String> getTagBox() {
    	return comboBox;
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
	        searchInputField2 = new JTextField();
	        searchInputField2.setBounds(400, 10, 200, SEARCH_PANEL_HEIGHT - 20);
	        
	        searchButton = new JButton("Search");
	        searchButton.setBounds(config.PAGE_WIDTH - 100, 10, 80, SEARCH_PANEL_HEIGHT - 20);
	        searchButton.setBackground(Color.white);
	        
	        resetButton = new JButton("Reset");
	        resetButton.setBounds(config.PAGE_WIDTH - 200, 10, 80, SEARCH_PANEL_HEIGHT - 20);
	        resetButton.setBackground(Color.white);
	        
	       // searchComboBoxMap.put("tag", new PeopleSearchComponent("TAG", new String[]{"ALL","0", "1", "2","3","4","5","6","7","8","9","10","11","12"}, searchComboBoxMap.size()));
			JLabel labelType = new JLabel("TYPE" + " :");
	        labelType.setBounds(330, 10, 50, 30);
			JLabel labelTag = new JLabel("TAG" + " :");
	        labelTag.setBounds(630, 10, 50, 30);
			JLabel labelName = new JLabel("Name" + " :");
	        labelName.setBounds(30, 10, 50, 30);
	        
	        comboBox = new JComboBox<>(new String[]{"ALL", "1", "2","3","4","5","6","7","8","9","10","11","12"});
	        comboBox.setSelectedIndex(0);
	        comboBox.setBounds(700, 10, 90, 30);
	        comboBox.setBackground(Color.white);

	        this.add(searchInputField1);
	        this.add(searchInputField2);
	        this.add(searchButton);
	        this.add(resetButton);
	        this.add(labelType);
	        this.add(labelTag);
	        this.add(labelName);
	        this.add(comboBox);
	        
		}
	    
	   
	
}
