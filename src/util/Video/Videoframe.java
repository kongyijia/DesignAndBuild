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


    public CallbackMediaPlayerComponent MediaPlayerComponent;


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
        //Parent panel is contentpane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        this.setBounds(100, 100, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MediaPlayerComponent = new CallbackMediaPlayerComponent();

        JPanel controlsPane = new JPanel();
        pauseButton = new JButton("Pause");
        controlsPane.add(pauseButton);
        rewindButton = new JButton("Rewind");
        controlsPane.add(rewindButton);
        skipButton = new JButton("Skip");
        controlsPane.add(skipButton);


        contentPane.add(MediaPlayerComponent, BorderLayout.CENTER);
        contentPane.add(controlsPane, BorderLayout.SOUTH);

        this.setContentPane(contentPane);
        this.setVisible(true);
        MediaPlayerComponent.mediaPlayer().media().play("data/video/1.mp4");
    }
}
