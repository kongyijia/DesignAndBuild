package control.VideoSquare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Controller;
import control.FX_Video.VideoPlayerLauncher;
import control.MainFrame;
import util.Util;
import util.config;
import view.VideoSquare.*;
import model.mapping.*;
import model.*;
import view.courseBook.TimeBookPanel;
import view.function.FunctionPanel;

/**
 *  This class is used to control view display and data interaction of {@link VideoSquare}
 *
 *  @author Xinyu Zhou, Zai Song
 *  @version 2.0
 *  @since 2021/4/27
 */
public class VideoSquareController extends Controller implements ActionListener {
    private final VideoSquare videoSquare;
    public static final int GAP = 10;
    public static final int P_HEIGHT = 250;
    public static final int P_WIDTH = 275;
    private Client client = MainFrame.getInstance().getClient();
    public Video currentVideo;

    public void setComboBoxArray() {
        try {
            ArrayList<String> arrayList = VideoTypeMapping.readAllVideoTypes();
            arrayList.add(0, "");
            this.videoSquare.getSearchVideoPanel().setTypeComboBox(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public VideoSquareController() {
        super(config.VIDEOSQUARE_PANEL_NAME, new VideoSquare());
        videoSquare = (VideoSquare) this.panel;
        this.setComboBoxArray();
        this.onSearch();
        this.bind();
    }

    public VideoSquareController(String name, JPanel videoSquare) {
        super(name, videoSquare);
        this.videoSquare = (VideoSquare) this.panel;
        this.setComboBoxArray();
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }

    public void bind() {
        this.videoSquare.addListener(this);
    }
    /**
     *  call the generateMap and ake the return results as parameters for forSearch function.
     */
    public void onSearch() {
        ArrayList<Video> vs = new ArrayList<>();
        try {
            vs = generateMap(videoSquare.getSearchText1().getText(), (String) videoSquare.getTypeComboBox().getSelectedItem(), (String) videoSquare.getTagBox().getSelectedItem());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        forSearch(vs);
    }
    /**
     *  reset the search information, delete previous input information.
     */
    public void onReset() {
        this.videoSquare.getSearchText1().setText("");
        this.videoSquare.getTypeComboBox().setSelectedItem("");
        this.videoSquare.getTagBox().setSelectedIndex(0);
    }
    /**
     * find a list of videos satisfies the input conditions.
     *
     * @param name the name of the video
     * @param type the type of the video
     * @param tag the tag of the video
     * @return generateMap a list of videos satisfies the input conditions
     * @throws FileNotFoundException when {@link VideoMapping#DATA_PATH} not found
     */
    public ArrayList<Video> generateMap(String name, String type, String tag) throws FileNotFoundException {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<Video> vs;
        if (!name.isEmpty()) {
            map.put("name", name);
        }
        if (!type.isEmpty()) {
            map.put("types", type);
        }
        if (!tag.equals("ALL")) {
            map.put("tag", tag);
        }
        if (MainFrame.getInstance().getClient().getRole() == 1) {
            map.put("author", "" + MainFrame.getInstance().getClient().getId());
        }

        vs = VideoMapping.find(map);
        return vs;
    }
    /**
     *  set the perfect size of the JPanel according to the amount of videos need to show and add the searched videos to the JPanel.
     *
     * @param  vs a list of videos which satisfy the search conditions.
     */
    public void forSearch(ArrayList<Video> vs) {
        this.refresh();
        videoSquare.getPanel().setPreferredSize(new Dimension(videoSquare.getScrollPane().getWidth() - 50, (vs.size() / 4 + 1) * (2 * GAP + P_HEIGHT + 50)));
        for (int i = 0; i < vs.size(); i++) {
            videoSquare.getPanel().add(generateButton(i + 1, vs.get(i)));
            videoSquare.getPanel().revalidate();
        }
    }
    /**
     * add button action listener to the video button.
     *
     * @param video one of the searched video
     * @param button the button in the {@link VideoSquare} page
     */
    public void addButtonListener(Video video, JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    if (client.getRole() == 2) {
                        User user = (User) client;
                        if (user.getLevel() < video.getTag() &&
                                (user.getVip().equals("Plain") || user.getVip().equals("Course"))
                        )
                            JOptionPane.showMessageDialog(null, "Level is not satisfied!!");
                        else {
                            VideoPlayerLauncher a = new VideoPlayerLauncher();
                            client = MainFrame.getInstance().getClient();
                            try {
                                int learningTime = (int) a.creatplayer(video.getSrc());
                                int total = ((User) client).getLearningTime() + learningTime;
                                ((User) client).setLearningTime(total);
                                int newLevel = total / 100 + 1;
                                if (newLevel > ((User) client).getLevel()) {
                                    ((User) client).setLevel(Math.min(newLevel, 12));
                                }
                                ClientMapping.modify(client);
                                ClientMapping.modifyRecordHistory(client.getId(), new Client.RecordHistory(video.getId(), learningTime, 0, new Date()));
                                MainFrame.getInstance().setClient(client.getId());
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    } else {
                        setCurrentVideo(video);
                        MainFrame.getInstance().goTo(config.VIDEO_MODIFY);
                    }
                }
            }
        });
        if (!video.getCoverSrc().isEmpty()) {
            ImageIcon picIcon = new ImageIcon(video.getCoverSrc());
            picIcon.setImage(picIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
            button.setIcon(picIcon);
        }
    }
    /**
     * Update all components.
     *
     * @param num The serial number of the specific video match the search conditions.
     * @param video on of the video which satisfies the search condition.
     * @return JPanel which is used to show the search result.
     */
    public JPanel generateButton(int num, Video video) {
        int row;
        int column;

        if (num % 4 == 0) {
            row = num / 4;
            column = 4;
        } else {
            row = num / 4 + 1;
            column = num % 4;
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(GAP + (column - 1) * (P_WIDTH + GAP), GAP + (row - 1) * (P_HEIGHT + 50), P_WIDTH, P_HEIGHT + 50);
        buttonPanel.setBackground(new Color(255, 255, 255));

        JLabel videoName = new JLabel("Name: " + video.getName() + " " + " Level: " + video.getTag());
        videoName.setFont(new Font(null, Font.PLAIN, 13));
        JButton button = new JButton();
        button.setBounds(0, 0, P_WIDTH, P_HEIGHT);
        this.addButtonListener(video, button);
        buttonPanel.add(button);
        buttonPanel.add(videoName);

        return buttonPanel;
    }
    /**
     * This method is used to refresh the {@link VideoSquare} page
     */
    public void refresh() {
        videoSquare.getPanel().removeAll();
        videoSquare.getPanel().revalidate();
        this.videoSquare.updateUI();
    }
    /**
     * Update all components in the {@link VideoSquare} page
     */
    @Override
    public void update() {
        this.client = MainFrame.getInstance().getClient();
        this.onReset();
        this.onSearch();
    }
    /**
     *  add actionListener to the button search and reset.
     *
     * @param e the actionEvent of the buttons in  {@link VideoSquare} page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == videoSquare.getSearchButton()) {
            this.onSearch();
        } else if (e.getSource() == videoSquare.getResetButton()) {
            this.onReset();
        }
    }
}
