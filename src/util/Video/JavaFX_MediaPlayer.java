package util.Video;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static util.config.lock;

public class JavaFX_MediaPlayer extends Application
{
        public static  String path;


        private static Scene scene;
        private static BorderPane pane;
        private static HBox paneCtl;
        private static Media media;
        private static MediaView mediaView;
        private static MediaPlayer mediaPlayer;
        private static long studyTime = 0;
        private static Timer timer = new Timer();
        private static TimerTask countingTask;

        private Double endTime;
        private Double currentTime;



        @Override
        public void start(Stage primaryStage)
        {
                        //Instantiating Media class
                        media = new Media(new File(path).toURI().toString());
                        studyTime = 0;
                        initMediaPlayer();
                        initPaneCtl();
                        initPane();
                        initScene();


                        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                                public void handle(WindowEvent event) {
                                        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
                                                mediaPlayer.stop();
                                        synchronized (lock) {
//                                                System.out.println("notify");
                                                lock.notify();
                                        }
                                }
                        });

                        primaryStage.setScene(scene);
                        primaryStage.setTitle("Playing video");
                        primaryStage.show();
        }


        @Override
        public void stop() throws Exception
        {
                timer.cancel();
                super.stop();
        }

        private void initMediaPlayer()
        {

                //Instantiating MediaPlayer class
                mediaPlayer = new MediaPlayer(media);

                //Instantiating MediaView class
                mediaView = new MediaView(mediaPlayer);

                //by setting this property to true, the Video will be played
                mediaPlayer.setAutoPlay(true);

                mediaPlayer.setOnReady(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                endTime = mediaPlayer.getStopTime().toSeconds();
                        }
                });

                mediaPlayer.setOnPlaying(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                startTimer();
                        }
                });
                mediaPlayer.setOnPaused(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                pauseTimer();
                        }
                });
                mediaPlayer.setOnEndOfMedia(new Runnable()
                {
                        @Override
                        public void run()
                        {
                                pauseTimer();
                        }
                });
                mediaPlayer.setOnStopped(new Runnable()
                {
                        @Override
                        public void run() {
                                pauseTimer();
                        }
                });

        }

        private void initPane()
        {
                //setting group and scene
                pane = new BorderPane();
                mediaView.fitWidthProperty().bind(pane.widthProperty());
                mediaView.fitHeightProperty().bind(pane.heightProperty().subtract(30));
                pane.setCenter(mediaView);
                pane.setBottom(paneCtl);

        }

        private  void initScene()
        {
                scene = new Scene(pane, 1200, 580);
                scene.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.SPACE)
                        {
                                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
                                        mediaPlayer.pause();
                                if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)
                                        mediaPlayer.play();
                        }
                });
        }

        private String Seconds2Str(Double seconds)
        {
                int count = seconds.intValue();
                int Hours = count / 3600;
                count = count % 3600;
                int Minutes = count /60;
                count = count % 60;
                return Hours +":"+ Minutes +":"+ count;
        }



        public static void startTimer()
        {
//                System.out.println("start Timer" + studyTime);
                countingTask = new TimerTask()
                {
                        @Override
                        public void run() { studyTime ++; }
                };
                timer.schedule(countingTask, 0,1000);

        }

        public static void pauseTimer()
        {
//                System.out.println("Pause Timer" + studyTime);
                if (countingTask != null)
                {
                        countingTask.cancel();
                        countingTask = null;
                }
        }

        public static long getRunTime()
        {
                return studyTime;
        }














        private void initPaneCtl() // 屎山啊
        {
                Label lbCurrentTime = new Label();
                Slider slTime = new Slider(); // 时间轴
                slTime.setPrefWidth(200);
                Button btnPlay = new Button("Pause");
                btnPlay.setOnAction(e->{
                        if (btnPlay.getText().equals("Play")){
                                btnPlay.setText("Pause");
                                mediaPlayer.play();
                        }
                        else{
                                btnPlay.setText("Play");
                                mediaPlayer.pause();
                        }
                });


                Slider slVolume = new Slider(); // 音量
                slVolume.setPrefWidth(150);
                slVolume.setValue(50);
                slVolume.setShowTickLabels(true);
                slVolume.setShowTickMarks(true);


                mediaPlayer.currentTimeProperty().addListener(ov->{
                        currentTime = mediaPlayer.getCurrentTime().toSeconds();
                        lbCurrentTime.setText(Seconds2Str(currentTime)+"/"+Seconds2Str(endTime));
                        slTime.setValue(currentTime/endTime*100);
                });
                slTime.valueProperty().addListener(ov->{
                        if (slTime.isValueChanging()){
                                mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(slTime.getValue()/100));
                        }
                });
                mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100)); // 音量调节



                paneCtl = new HBox(13);
//                paneCtl.setBackground(new Background(new BackgroundFill(Color.DARKBLUE,null,null)));
                paneCtl.setAlignment(Pos.CENTER);
                paneCtl.getChildren().addAll(lbCurrentTime,slTime,btnPlay,new Label("Volume"),slVolume);

        }


}


