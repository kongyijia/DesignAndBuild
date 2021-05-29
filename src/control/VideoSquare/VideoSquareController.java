package control.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import control.MainFrame;
import util.config;
import view.VideoSquare.*;
import model.mapping.*;
import model.*;

public class VideoSquareController extends Controller implements ActionListener {
	private final VideoSquare videoSquare;
	public static final int GAP= 10;
	public static final int P_HEIGHT = 250;
	public static final int P_WIDTH= 275;
	private Client client= MainFrame.getInstance().getClient();
	public Video currentVideo;
	
	public VideoSquareController() {
		super(config.VIDEOSQUARE_PANEL_NAME, new VideoSquare());
		videoSquare = (VideoSquare)this.panel;
		this.onSearch();
		this.bind();
	}

	public VideoSquareController(String name, JPanel videoSquare){
		super(name, videoSquare);
		this.videoSquare = (VideoSquare) this.panel;
	}

	public void setCurrentVideo(Video currentVideo) {
		this.currentVideo = currentVideo;
	}

	public Video getCurrentVideo() {
		return currentVideo;
	}

	public void bind(){
		this.videoSquare.addListener(this);
	}

	public void onSearch(){
		ArrayList<Video> vs=new ArrayList<>();
		try {
			vs=generateMap(videoSquare.getSearchText1().getText(),videoSquare.getSearchText2().getText(),(String) videoSquare.getTagBox().getSelectedItem());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		forSearch(vs);
	}

	public void onReset(){
		this.videoSquare.getSearchText1().setText("");
		this.videoSquare.getSearchText2().setText("");
		this.videoSquare.getTagBox().setSelectedIndex(0);
	}

	public ArrayList<Video> generateMap(String name,String type,String tag) throws FileNotFoundException {
		HashMap<String, String> map = new HashMap<>();
		ArrayList<Video> vs;
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
		if(MainFrame.getInstance().getClient().getRole() == 1){
			map.put("author", ""+MainFrame.getInstance().getClient().getId());
		}
		
		vs = VideoMapping.find(map);
		return vs;
	}
	
	public void forSearch(ArrayList<Video> vs){
		this.refresh();
		videoSquare.getPanel().setPreferredSize(new Dimension(videoSquare.getScrollPane().getWidth() - 50, (vs.size()/4+1)*(2*GAP+ P_HEIGHT +50)));
		for(int i=0;i<vs.size();i++) {
			videoSquare.getPanel().add(generateButton(i+1,vs.get(i)));
			videoSquare.getPanel().revalidate();
		}
	}

	public void addButtonListener(int num, Video video, JButton button){
		button.addActionListener(e ->{
			//TODO： link to the videos
			if(client.getRole()==2) {
				User user=(User) client;
				if(user.getLevel()<video.getTag())
					JOptionPane.showMessageDialog(null,"Level is not satisfied!!"+num);
				else
					JOptionPane.showMessageDialog(null,"Developing!"+num);
			} else {
				this.setCurrentVideo(video);
				MainFrame.getInstance().goTo(config.VIDEO_MODIFY);
			}
		});
		if(!video.getCoverSrc().isEmpty()) {
			ImageIcon picIcon = new ImageIcon(video.getCoverSrc());
			picIcon.setImage(picIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
			button.setIcon(picIcon);
		}
	}

	public JPanel generateButton(int num, Video video) {
		int row;
		int column;
		
		if(num%4 == 0) {
			row = num/4;
			column = 4;
		}else {
			row = num/4+1;
			column = num%4;
		}
		JPanel buttonPanel = new JPanel();	
		buttonPanel.setBounds(GAP+(column-1)* (P_WIDTH+GAP), GAP+(row-1)*(P_HEIGHT +50), P_WIDTH, P_HEIGHT +50);
		buttonPanel.setBackground(new Color(255, 255, 255));  
		
		JLabel videoName = new JLabel("Name: "+video.getName()+" Tag: "+video.getTag());
		videoName.setFont(new Font(null, Font.PLAIN, 18));
		JButton button = new JButton();	
		button.setBounds(0, 0, P_WIDTH, P_HEIGHT);
		this.addButtonListener(num,video,button);
		buttonPanel.add(button);
		buttonPanel.add(videoName);
		
		return buttonPanel;
	}

	public void refresh(){
		videoSquare.getPanel().removeAll();
		videoSquare.getPanel().revalidate();
		this.videoSquare.updateUI();
	}
	
	@Override
	public void update() {
		this.client = MainFrame.getInstance().getClient();
		this.onReset();
		this.onSearch();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == videoSquare.getSearchButton()) {
			this.onSearch();
		} else if(e.getSource() == videoSquare.getResetButton()) {
			this.onReset();
		}
	}
}
