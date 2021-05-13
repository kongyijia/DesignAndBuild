package control.FX_Video;

import javafx.application.Application;
import util.Video.JavaFX_MediaPlayer;

public class VideoPlayerLauncher
{
    public static void main(String[] args) {
        VideoPlayerLauncher a = new VideoPlayerLauncher();
        a.creatplayer("data/video/1.mp4");
    }

    public void creatplayer(String path)
    {
        JavaFX_MediaPlayer.path = path;
        Application.launch(util.Video.JavaFX_MediaPlayer.class);
        long testruntime = JavaFX_MediaPlayer.getRunTime();
        System.out.println(testruntime);
    }
}