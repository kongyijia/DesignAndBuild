package control.VideoFrame;

import model.Video;
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
import java.awt.event.*;
import java.util.List;

import static control.VideoFrame.VideoTest.lock;
import static java.lang.Math.toIntExact;

public class VideoFrameController implements Runnable
{
    private String path;
    Videoframe frame;
    int skipping_time = 0;
    int progress = 0;

    public VideoFrameController(String path)  {this.path = path;}

    @Override
    public void run()
    {
        MakeVideoFrameController(this.path);
    }

    public void MakeVideoFrameController(String Path)
    {
        NativeDiscovery discovery;
        if (!isWindows())
        {

            OsxNativeDiscoveryStrategy osxNativeDiscoveryStrategy = new OsxNativeDiscoveryStrategy();
            osxNativeDiscoveryStrategy.onSetPluginPath("bin/darwin/plugins");
            osxNativeDiscoveryStrategy.onFound("bin/darwin");
            NativeDiscoveryStrategy[] nativeDiscoveryStrategies = {
                    new LinuxNativeDiscoveryStrategy(),
                    new WindowsNativeDiscoveryStrategy(),
                    osxNativeDiscoveryStrategy
            };
            discovery = new NativeDiscovery(nativeDiscoveryStrategies);
        }
        else
        {
            cleanCache();
            discovery = new NativeDiscovery();
        }
        MediaPlayerFactory factory = new MediaPlayerFactory(discovery);
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                frame = new Videoframe(Path);
                buildListener();
                buildLogo();
                videoSurface();
                processBar();
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
                progress = toIntExact(frame.MediaPlayerComponent.mediaPlayer().status().length());
//                System.out.println(progress);
//                int result = JOptionPane.showConfirmDialog(null, "REPEAT？", "REPEAT", JOptionPane.YES_NO_CANCEL_OPTION);
//                System.out.println(result);
//                if(result == 0) // repeat
//                    frame.MediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
//                else
//                    frame.MediaPlayerComponent.mediaPlayer().controls().setRepeat(false);
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
                skipping_time -= 10000;
            }
        });

        frame.getSkipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(10000);
                skipping_time += 10000;
            }
        });

        //Process Bar
        frame.getProgress().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int x=e.getX();
                float after_position = (float)x/frame.getProgress().getWidth();
                long video_length = frame.MediaPlayerComponent.mediaPlayer().status().length();
                skipping_time += Math.round((after_position - frame.MediaPlayerComponent.mediaPlayer().status().position()) * video_length);
                System.out.println(skipping_time);
                frame.MediaPlayerComponent.mediaPlayer().controls().setPosition((after_position));
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (frame.MediaPlayerComponent.mediaPlayer().status().isPlaying())
                {
                    float finish_position = frame.MediaPlayerComponent.mediaPlayer().status().position();
                    long video_length = frame.MediaPlayerComponent.mediaPlayer().status().length();
                    progress = Math.round(finish_position * video_length);
                }
                frame.MediaPlayerComponent.mediaPlayer().release();
                synchronized(lock)
                {
                    lock.notify();
                }
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
                {
                    frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(10000);
                    skipping_time += 10000;

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) // left key
                {
                    frame.MediaPlayerComponent.mediaPlayer().controls().skipTime(-10000);
                    skipping_time -= 10000;
                }
                if (e.getKeyCode() == KeyEvent.VK_F1) // F1 change FullScreen
                    frame.MediaPlayerComponent.mediaPlayer().fullScreen().set(!frame.MediaPlayerComponent.mediaPlayer().fullScreen().isFullScreen());
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    frame.MediaPlayerComponent.mediaPlayer().audio().setVolume(CurrentVolume+20);
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    frame.MediaPlayerComponent.mediaPlayer().audio().setVolume(CurrentVolume-20);
            }
        });
    }

    private void processBar()
    {
        try
        {
            new SwingWorker<String, Integer>()
            {
                @Override
                protected String doInBackground() throws Exception
                {
                    // TODO Auto-generated method stub
                    while (true)
                    {
                        // 获取视频播放进度并且按百分比显示
                        frame.MediaPlayerComponent.videoSurfaceComponent().requestFocusInWindow();
                        float percent = frame.MediaPlayerComponent.mediaPlayer().status().position();
                        publish((int) (percent * 100));
                        Thread.sleep(100);
                    }
                }

                protected void process(List<Integer> chunks)
                {
                    for (int v : chunks)
                    {
                        frame.getEndTime().setText(getTime(frame.MediaPlayerComponent.mediaPlayer().status().length()));
                        frame.getCurrentTime().setText(getTime(v * frame.MediaPlayerComponent.mediaPlayer().status().length() / 100));
                        frame.getProgress().setValue(v);
                    }
                }
            }.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getTime(Long time)
    {
        int dialSeconds = toIntExact(time/1000);
        int dialhous = dialSeconds / 3600;
        int dialMinutes = dialSeconds / 60 % 60;
        dialSeconds %= 60;
        String showMinutes = "";
        String showSeconds;
        String showText;
        if (dialSeconds < 10) {
            showSeconds = "0" + dialSeconds;
        } else {
            showSeconds = Integer.toString(dialSeconds);
        }
        if (dialMinutes < 10) {
            showMinutes = "0" + dialMinutes;
        } else {
            showMinutes = Integer.toString(dialMinutes);
        }
        if (dialhous > 0) {
            showText = dialhous + ":" + showMinutes + ":" + showSeconds;
        } else {
            showText = showMinutes + ":" + showSeconds;
        }
        return showText;
    }

    protected void cleanCache()
    {
        try {
            String[] cmd = { "bin/vlc-cache-gen.exe" + "-f" + "bin/win32-x86-64/plugins" };
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

    public int getVideoProgress()
    {
        return progress;
    }

    public int getLeaningTime()
    {
        int ans = progress - skipping_time;
        if (ans < 0)
            return 0;
        return ans;
    }

}

