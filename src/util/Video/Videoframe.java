package util.Video;

import com.sun.jna.platform.win32.OpenGL32;
import model.Video;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface;

import javax.swing.*;
import java.awt.*;

public class Videoframe extends JFrame
{
    private final JButton pauseButton;
    private final JButton skipButton;
    private final JButton rewindButton;

    private final JLabel endTime = new JLabel();
    private final JLabel currentTime = new JLabel();

    private final JPanel contentPane;
    private final JPanel controlContentPane;
    private final JPanel controlsPane;
    private final JProgressBar progress;  //进度条
    private JPanel progressPanel;   //进度条容器

    public CallbackMediaPlayerComponent MediaPlayerComponent;


    public JLabel getEndTime()
    {
        return endTime;
    }

    public JLabel getCurrentTime()
    {
        return currentTime;
    }

    public JProgressBar getProgress()
    {
        return progress;
    }

    public JButton getPauseButton()
    {
        return pauseButton;
    }

    public JButton getRewindButton()
    {
        return rewindButton;
    }

    public JButton getSkipButton()
    {
        return skipButton;
    }

    public Videoframe()
    {
        MediaPlayerComponent = new CallbackMediaPlayerComponent();
        //Parent panel is ContentPane
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        this.setBounds(100, 100, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controlContentPane = new JPanel();
        controlContentPane.setLayout(new BorderLayout());

        progressPanel = new JPanel();
        progressPanel.setLayout(new BorderLayout());


        progress = new JProgressBar();

        progressPanel.add(progress, BorderLayout.CENTER);
        progressPanel.add(currentTime, BorderLayout.WEST);
        progressPanel.add(endTime, BorderLayout.EAST);

        controlsPane = new JPanel();
        pauseButton = new JButton("Pause");
        controlsPane.add(pauseButton);
        rewindButton = new JButton("Rewind");
        controlsPane.add(rewindButton);
        skipButton = new JButton("Skip");
        controlsPane.add(skipButton);

        controlContentPane.add(progressPanel, BorderLayout.NORTH);
        controlContentPane.add(controlsPane, BorderLayout.SOUTH);

        contentPane.add(MediaPlayerComponent, BorderLayout.CENTER);
        contentPane.add(controlContentPane, BorderLayout.SOUTH);

        this.setContentPane(contentPane);
        this.setVisible(true);
        MediaPlayerComponent.mediaPlayer().media().play("data/video/1.mp4");
    }
}
