package util.Video;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class JavaFX_MediaPlayer extends Application
{
        public static String path;
        public static long runTime;
        public static long startTime;
        public static long endTime;
        @Override
        public void start(Stage primaryStage)
        {


        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());


        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class
        MediaView mediaView = new MediaView(mediaPlayer);

        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);

        //setting group and scene
        Pane root = new Pane();
        root.getChildren().add(mediaView);
        mediaView.fitHeightProperty().bind(root.heightProperty());
        mediaView.fitWidthProperty().bind(root.widthProperty());
        Scene scene = new Scene(root, 1200, 580);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing video");
        primaryStage.show();
        startTime = System.currentTimeMillis();

        }

        @Override
        public void stop() throws Exception
        {
                endTime =  System.currentTimeMillis();
                runTime = (endTime-startTime)/1000;
                super.stop();
        }

        public static long getRunTime()
        {
                return runTime;
        }

}
