package control.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import util.config;
import view.VideoSquare.*;
import model.mapping.*;
import model.*;

public class VideoSquareController extends Controller{
	private VideoSquare videoSquare;
	public static final int GAP= 10;
	public static final int P_HIGHT= 250;
	public static final int P_WIDTH= 275;
	private static ImageIcon picIcon = new ImageIcon("assets/pictures/video.jfif");
	
	public VideoSquareController() throws FileNotFoundException {
		super(config.VIDEOSQUARE_PANEL_NAME, new VideoSquare());
		videoSquare = (VideoSquare)this.panel;
		
		videoSquare.addListener(e ->{
			if (e.getSource() == videoSquare.getSearchButton()) {
				update();
				 ArrayList<Video> vs=new ArrayList<Video>();
				try {
					vs=generateMap(videoSquare.getSearchText1().getText(),videoSquare.getSearchText2().getText(),(String) videoSquare.getTagBox().getSelectedItem());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				forSearch(vs);//
			}
			else if(e.getSource() == videoSquare.getResetButton()) {
                update();
			}
		});
		//user = MainFrame.getInstance().getClient();
	}	
	public ArrayList<Video> generateMap(String name,String type,String tag) throws FileNotFoundException {
		HashMap<String, String> map = new HashMap<>();
		ArrayList<Video> vs=new ArrayList<Video>();
		if(!name.isEmpty()) {
			map.put("name",name);
			System.out.println("name"+name);
		}			
		if(!type.isEmpty()) {
			map.put("types",type);
			System.out.println(" type"+type);
		}		
		if(!tag.equals("ALL")) {
			map.put("tag", tag);
			System.out.println(" tag"+tag);
		}		
		
		vs = VideoMapping.find(map);	
		System.out.print(vs);
		return vs;
	}
	
	public void forSearch(ArrayList<Video> vs){
		update();			
		videoSquare.getPanel().setPreferredSize(new Dimension(videoSquare.getScrollpane().getWidth() - 50, (vs.size()/4+1)*(2*GAP+P_HIGHT+50)));
		for(int i=0;i<vs.size();i++) {
			videoSquare.getPanel().add(genrateButton(i+1,vs.get(i).getName(),vs.get(i).getTag(),vs.get(i).getCoverSrc()));
			videoSquare.getPanel().revalidate();
		}	
				
	}
	public JPanel genrateButton(int num,String name,int tag,String path) {
		int row = 0;
		int column = 0;
		
		if(num%4 == 0) {
			row = num/4;
			column = 4;
		}else {
			row = num/4+1;
			column = num%4;
		}
		JPanel buttonPanel = new JPanel();	
		buttonPanel.setBounds(GAP+(column-1)* (P_WIDTH+GAP), GAP+(row-1)*(P_HIGHT+50), P_WIDTH, P_HIGHT+50);
		buttonPanel.setBackground(new Color(255, 255, 255));  
		
		JLabel videoName = new JLabel("Name: "+name+" Tag: "+tag);
		videoName.setFont(new Font(null, 0, 18));
		JButton button = new JButton();	
		button.setBounds(0, 0, P_WIDTH, P_HIGHT);
		button.addActionListener(e ->{
			//TODO： link to the videos
			 JOptionPane.showMessageDialog(null,"Developing!"+num);
			 //历史记录
			});
		if(!path.isEmpty())
		picIcon=new ImageIcon(path);
		picIcon.setImage(picIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
		button.setIcon(picIcon);
		buttonPanel.add(button);
		buttonPanel.add(videoName);
		
		return buttonPanel;
	}
	
	@Override
	public void update() {
		videoSquare.getPanel().removeAll();	
		videoSquare.getPanel().revalidate();
		videoSquare.getPanel().setVisible(false);
		videoSquare.getPanel().setVisible(true);
	}

}
