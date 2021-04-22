package control.VideoFrame;

import model.Video;


public class VideoTest
{
    static final Object lock = new Object();
    public static void main(String[] args)
    {
            VideoFrameController test = new VideoFrameController("data/video/1.mp4");
            Thread VIDEO = new Thread(test);
            VIDEO.start();
            synchronized (lock)
            {
                try
                {
                    lock.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Progress: " + test.getVideoProgress());
                System.out.println("Learningtime :" + test.getLearningTime());
            }
    }
}

