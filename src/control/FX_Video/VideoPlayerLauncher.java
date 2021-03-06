package control.FX_Video;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import util.Video.JavaFX_MediaPlayer;
import static util.config.lock;

/**
 * <p>
 *     Class {@code VideoPlayerLauncher} is a class which used to launch the class {@code JavaFX_MediaPlayer}.
 *
 * <p>
 *         Call {@code Createplayer} function to start a MediaPlayer. <br>
 *         Call {@code getTime} function to get study time. as follow :
 *     <pre>
 *     public long getTime() {
 *         synchronized (lock)
 *         {
 *             try {
 *                 lock.wait();
 *             } catch (InterruptedException e) {
 *                 e.printStackTrace();
 *             }
 *             return JavaFX_MediaPlayer.getRunTime();
 *         }
 *     }
 *     </pre>
 *
 * @author Zhanao Zhang
 * @since  2021/5/31 17:41
 * @version V1.0
 */
public class VideoPlayerLauncher
{
    private static volatile boolean javaFxLaunched = false;

/**
 *
 * Get watching time.
 * @return long study time
 * @author Zhanao Zhang
 */
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

    /**
     *
     * Launch the JavaFX application.
     * @see JavaFX_MediaPlayer
     * @param path Video path
     * @return long study time
     */


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