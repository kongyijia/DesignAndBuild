package control.VideoFrame;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.factory.discovery.strategy.LinuxNativeDiscoveryStrategy;
import uk.co.caprica.vlcj.factory.discovery.strategy.NativeDiscoveryStrategy;
import uk.co.caprica.vlcj.factory.discovery.strategy.OsxNativeDiscoveryStrategy;
import uk.co.caprica.vlcj.factory.discovery.strategy.WindowsNativeDiscoveryStrategy;
import uk.co.caprica.vlcj.player.base.LogoPosition;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;
import util.Video.Videoframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VideoFrameController
{
    Videoframe frame;
    public VideoFrameController()
    {
        OsxNativeDiscoveryStrategy osxNativeDiscoveryStrategy = new OsxNativeDiscoveryStrategy();
        osxNativeDiscoveryStrategy.onSetPluginPath("bin/darwin/plugins");
        osxNativeDiscoveryStrategy.onFound("bin/darwin");
        NativeDiscoveryStrategy[] nativeDiscoveryStrategies = {
                new LinuxNativeDiscoveryStrategy(),
                new WindowsNativeDiscoveryStrategy(),
                osxNativeDiscoveryStrategy
        };
        NativeDiscovery discovery = new NativeDiscovery(nativeDiscoveryStrategies);
        MediaPlayerFactory factory = new MediaPlayerFactory(discovery);
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                frame = new Videoframe();
                buildListener();
                buildLogo();
                videoSurface();
                frame.MediaPlayerComponent.mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(frame));
            }
        });
    }

    private void buildListener()
    {
        frame.MediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter()
        {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                // TODO finish
                int result = JOptionPane.showConfirmDialog(null, "REPEAT？", "REPEAT", JOptionPane.YES_NO_CANCEL_OPTION);
                System.out.println(result);
                if(result == 0) // repeat
                    frame.MediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
                else
                    frame.MediaPlayerComponent.mediaPlayer().controls().setRepeat(false);
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(frame, "Failed to play media", "Error", JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            }
        });


        frame.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.MediaPlayerComponent.mediaPlayer().controls().pause();
            }
        });

        frame.getRewindButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(-10000);
            }
        });

        frame.getSkipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(10000);
            }
        });
    }

    private void buildLogo()
    {
        //TODO change everycoach's profile
        frame.MediaPlayerComponent.mediaPlayer().logo().setFile("/Users/izreal/Desktop/大三/下/软件工程/SoftWareProject/DesignAndBuild/data/image/defaultCover.png");
        frame.MediaPlayerComponent.mediaPlayer().logo().setPosition(LogoPosition.TOP_LEFT);
        frame.MediaPlayerComponent.mediaPlayer().logo().setOpacity(0.3f);
        frame.MediaPlayerComponent.mediaPlayer().logo().enable(true);
    }

    private void videoSurface()
    {
        Component videoSurface = frame.MediaPlayerComponent.videoSurfaceComponent();
        frame.MediaPlayerComponent.videoSurfaceComponent().requestFocusInWindow();
        videoSurface.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                int CurrentVolume = frame.MediaPlayerComponent.mediaPlayer().audio().volume();
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    frame.MediaPlayerComponent.mediaPlayer().controls().pause();
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) // right key
                    frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(10000);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) // left key
                    frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(-10000);
                if (e.getKeyCode() == KeyEvent.VK_F1) // F1 change FullScreen
                    frame.MediaPlayerComponent.mediaPlayer().fullScreen().set(!frame.MediaPlayerComponent.mediaPlayer().fullScreen().isFullScreen());
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    frame.MediaPlayerComponent.mediaPlayer().audio().setVolume(CurrentVolume+20);
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    frame.MediaPlayerComponent.mediaPlayer().audio().setVolume(CurrentVolume-20);

            }
        });
    }
}
