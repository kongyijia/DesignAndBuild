package control.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;
import util.config;
import view.VideoSquare.*;

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
                forSearch(videoSquare.getSearchText().getText());
			}
		});
		//user = MainFrame.getInstance().getClient();
	}		
	public void forSearch(String searchContext) {
		update();
		//System.out.println(searchContext);	
		
		int num = Integer.parseInt(searchContext);
		videoSquare.getPanel().setPreferredSize(new Dimension(videoSquare.getScrollpane().getWidth() - 50, (num/4+1)*(2*GAP+P_HIGHT+50)));
		for(int i=0;i<num;i++) {
			videoSquare.getPanel().add(genrateButton(i+1));
			videoSquare.getPanel().revalidate();
		}	
		
	}
	public JPanel genrateButton(int num) {
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
		
		JLabel videoName = new JLabel("this is the name of the vedio");
		videoName.setFont(new Font(null, 0, 18));
		JButton button = new JButton();	
		button.setBounds(0, 0, P_WIDTH, P_HIGHT);

		picIcon.setImage(picIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
		button.setIcon(picIcon);
		buttonPanel.add(button);
		buttonPanel.add(videoName);
			
		return buttonPanel;
	}
	@Override
	public void update() {
		videoSquare.getPanel().removeAll();	
	}

}
