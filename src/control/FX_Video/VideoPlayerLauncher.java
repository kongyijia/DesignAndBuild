package control.FX_Video;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import util.Video.JavaFX_MediaPlayer;

import static util.config.lock;

public class VideoPlayerLauncher
{
    private static volatile boolean javaFxLaunched = false;

    public long getTime() {
        synchronized (lock)
        {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return JavaFX_MediaPlayer.getRunTime();
        }
    }

    public long creatplayer(String path){
        JavaFX_MediaPlayer.path = path;

        if (!javaFxLaunched) {
            // First time
            Platform.setImplicitExit(false);
            new Thread(()->Application.launch(util.Video.JavaFX_MediaPlayer.class)).start();
            javaFxLaunched = true;
        } else { // Next times
            Platform.runLater(()->{
                try {
                    Application application = util.Video.JavaFX_MediaPlayer.class.newInstance();
                    Stage primaryStage = new Stage();
                    application.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        return this.getTime();
    }
}