package model.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import model.Video;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Map {@link Video} data to JSON database.
 * This "JSON database" is a self-defined database and it should only be used in this software.
 * <br>
 * Offering {@code add}, {@code delete}, {@code modify} and {@code find} methods for video data.
 * In this class, we use {@link com.alibaba.fastjson.JSON} to manage our {@link Video} POJO.
 *
 * @author Yubo Wu
 * @version 1.2
 * @see JSON
 * @see Video
 * @since 20 May 2021
 */
public class VideoMapping {
    /**
     * JSON file path root
     */
    public static final String DATA_PATH = "data/video.json";
    /**
     * Video source path root
     */
    public static final String VIDEO_PATH = "data/video/";
    /**
     * Cover source path root
     */
    public static final String COVER_PATH = "data/image/";
    /**
     * Default cover name
     */
    public static final String DEFAULT_COVER = "defaultCover.png";
    /**
     * Operation success status code
     */
    public static final int SUCCESS = 0;
    /**
     * Duplicate Video ID
     */
    public static final int DUPLICATE_ID = 1;
    /**
     * Cannot found Video data in database
     */
    public static final int VIDEO_DATA_NOT_FOUND = 2;
    /**
     * Cannot found Original Video file
     */
    public static final int ORIGINAL_VIDEO_FILE_NOT_FOUND = 3;
    /**
     * The type of the Video file is not MP4
     */
    public static final int NOT_MP4 = 4;
    /**
     * Something went wrong while deleting Video file
     */
    public static final int FILE_DELETE_ERROR = 5;
    /**
     * Cannot found the Video file in {@link VideoMapping#VIDEO_PATH}
     */
    public static final int VIDEO_FILE_NOT_FOUND = 6;
    /**
     * The chosen file is not an image
     */
    public static final int NOT_IMAGE = 7;

    /**
     * Add Video to database.
     * <br>
     * It can copy the original video file to {@link VideoMapping#VIDEO_PATH} and copy the cover image to {@link VideoMapping#COVER_PATH}.
     * <br>
     * After copying the files, it will generate {@code video} automatically and stores it in database.
     *
     * @param video             instance of {@link Video}
     * @param videoOriginalPath the original path of the video file
     * @param coverSrcPath      the path of the cover image
     * @return {@link VideoMapping#ORIGINAL_VIDEO_FILE_NOT_FOUND}
     * , {@link VideoMapping#DUPLICATE_ID}, {@link VideoMapping#NOT_MP4}, {@link VideoMapping#SUCCESS}
     * @throws IOException      when IO issue occur
     * @throws EncoderException an error occurred while parsing the MP4 file
     */
    private static int addVideo(Video video, String videoOriginalPath, String coverSrcPath) throws IOException, EncoderException {
        File videoFile = new File(videoOriginalPath);
        // check file if exists?
        if (!videoFile.isFile())
            return ORIGINAL_VIDEO_FILE_NOT_FOUND;

        // check if ID duplicate?
        ArrayList<Video> videoArrayList = VideoMapping.readAllVideos();
        for (Video v : videoArrayList) {
            if (v.getId() == video.getId())
                return DUPLICATE_ID;
        }

        // check if MP4?
        String suffix = videoFile.getName().substring(videoFile.getName().lastIndexOf("."));
        if (!suffix.equals(".mp4"))
            return NOT_MP4;

        // first copy, then add data to JSON database
        String newPath = VIDEO_PATH + video.getId() + suffix;
        File toFile = new File(newPath);
        Files.copy(videoFile.toPath(), toFile.toPath());

        video.setSrc(newPath);
        video.setCoverSrc(coverSrcPath);
        Encoder encoder = new Encoder();
        MultimediaInfo info = encoder.getInfo(toFile);
        long duration = info.getDuration() / 1000;
        video.setTime(duration);
        videoArrayList.add(video);
        writeAll(videoArrayList);
        return SUCCESS;
    }

    /**
     * It will add video with the default cover image, {@link VideoMapping#DEFAULT_COVER}.
     *
     * @param video             the instance of {@link Video}
     * @param videoOriginalPath the original path of the video file
     * @return {@link VideoMapping#ORIGINAL_VIDEO_FILE_NOT_FOUND}
     * , {@link VideoMapping#DUPLICATE_ID}, {@link VideoMapping#NOT_MP4}, {@link VideoMapping#SUCCESS}
     * @throws EncoderException an error occurred while parsing the MP4 file
     * @throws IOException      when IO issue occur
     * @see VideoMapping#addVideo(Video, String, String)
     */
    public static int add(Video video, String videoOriginalPath) throws EncoderException, IOException {
        return addVideo(video, videoOriginalPath, COVER_PATH + DEFAULT_COVER);
    }

    /**
     * Add video with a custom cover image.
     *
     * @param video             the instance of {@link Video}
     * @param videoOriginalPath the original path of the video file
     * @param coverOriginalPath the original path of the cover image
     * @return {@link VideoMapping#ORIGINAL_VIDEO_FILE_NOT_FOUND}
     * , {@link VideoMapping#DUPLICATE_ID}, {@link VideoMapping#NOT_IMAGE}
     * , {@link VideoMapping#NOT_MP4}, {@link VideoMapping#SUCCESS}
     * @throws IOException      when IO issue occur
     * @throws EncoderException an error occurred while parsing the MP4 file
     * @see VideoMapping#addVideo(Video, String, String)
     */
    public static int add(Video video, String videoOriginalPath, String coverOriginalPath) throws IOException, EncoderException {
        File file = new File(coverOriginalPath);
        // check if image?
        String mimeType = Files.probeContentType(file.toPath());
        String type = mimeType.split("/")[0];
        if (!type.equals("image"))
            return NOT_IMAGE;

        // copy image to data/image
        String avatarNewId = UUID.randomUUID().toString();
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        String newPath = COVER_PATH + avatarNewId + suffix;
        File newFile = new File(newPath);
        Files.copy(file.toPath(), newFile.toPath());

        // add video
        return addVideo(video, videoOriginalPath, newPath);
    }

    /**
     * Delete video data (video data in database and also the video file).
     *
     * @param id the video ID
     * @return {@link VideoMapping#VIDEO_DATA_NOT_FOUND}, {@link VideoMapping#VIDEO_FILE_NOT_FOUND}
     * , {@link VideoMapping#FILE_DELETE_ERROR}, {@link VideoMapping#SUCCESS}
     * @throws IOException when IO issue occur
     */
    public static int delete(int id) throws IOException {
        ArrayList<Video> videos = VideoMapping.readAllVideos();
        int index = -1;
        for (Video v : videos) {
            if (v.getId() == id) {
                index = videos.indexOf(v);
                break;
            }
        }
        if (index == -1)
            return VIDEO_DATA_NOT_FOUND;

        // delete file
        File file = new File(videos.get(index).getSrc());
        if (!file.exists())
            return VIDEO_FILE_NOT_FOUND;
        if (!file.delete())
            return FILE_DELETE_ERROR;
        videos.remove(index);
        writeAll(videos);
        return SUCCESS;
    }

    /**
     * Find {@link Video} from JSON database, and return the results to a {@link ArrayList}.
     * The parameter are {@link HashMap}, you need to put all {@code AND} conditions in hashmap.
     * This {@code find} method can't handle other conditions except {@code AND}, like {@code OR} or {@code BETWEEN} or others.
     *
     * @param conditionMap the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList} contained results.
     * @throws FileNotFoundException when {@link VideoMapping#DATA_PATH} not found
     */
    public static ArrayList<Video> find(HashMap<String, String> conditionMap) throws FileNotFoundException {
        ArrayList<Video> results = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            String s = reader.readString();
            JSONObject object = JSON.parseObject(s);
            int tmp = 0;
            for (Map.Entry<String, String> entry : conditionMap.entrySet()) {
                String v = object.getString(entry.getKey());
                if (v == null)
                    continue;
                if (entry.getKey().equals("types")) {
                    if (v.contains(entry.getValue())) {
                        tmp++;
                        continue;
                    }
                }
                if (v.equals(entry.getValue()))
                    tmp++;
            }
            if (tmp == conditionMap.size())
                results.add(JSON.parseObject(s, Video.class));
        }
        return results;
    }

    public static ArrayList<Video> findVideosByIdList(ArrayList<Integer> idList) throws FileNotFoundException {
        ArrayList<Video> results = new ArrayList<>();
        ArrayList<Video> videos = VideoMapping.readAllVideos();
        for (Video v : videos) {
            if (idList.contains(v.getId()))
                results.add(v);
        }
        return results;
    }

    /**
     * Update {@link Video} data in JSON database.
     * The method need {@code ID} to locate where the data is so do not set a new value to the {@code ID}.
     * <br>
     * NOTE: You can not use this method to modify {@code coverSrc}, {@code src} and {@code time} in Video.
     *
     * @param video instance of {@link Video}
     * @return {@link VideoMapping#VIDEO_DATA_NOT_FOUND} or {@link VideoMapping#SUCCESS}
     * @throws IOException when IO issue occur
     */
    public static int modify(Video video) throws IOException {
        int index = -1;
        ArrayList<Video> videos = VideoMapping.readAllVideos();
        for (Video v : videos) {
            if (v.getId() == video.getId())
                index = videos.indexOf(v);
        }
        if (index == -1)
            return VIDEO_DATA_NOT_FOUND;
        videos.remove(index);
        videos.add(index, video);
        writeAll(videos);
        return SUCCESS;
    }

    /**
     * Read {@link VideoMapping#DATA_PATH} to the program and convert it to POJO using fastJSON.
     *
     * @return all data in {@link VideoMapping#DATA_PATH}
     * @throws FileNotFoundException when {@link VideoMapping#DATA_PATH} not found
     */
    public static ArrayList<Video> readAllVideos() throws FileNotFoundException {
        ArrayList<Video> videos = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            Video video = reader.readObject(Video.class);
            videos.add(video);
        }
        reader.endArray();
        reader.close();
        return videos;
    }

    /**
     * Write all {@code videos} in {@link ArrayList} to the file using fastJSON.
     * This method will overwrite the {@link VideoMapping#DATA_PATH} file, so this method is set to be {@code private} to protect the data.
     *
     * @param videos the {@link Video} data that you want to write
     * @throws IOException when IO issue occur
     */
    private static void writeAll(ArrayList<Video> videos) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter(DATA_PATH));
        writer.config(SerializerFeature.DisableCircularReferenceDetect, true);
        writer.config(SerializerFeature.PrettyFormat, true);
        writer.startArray();
        for (Video v : videos) {
            writer.writeValue(v);
        }
        writer.endArray();
        writer.close();
    }
}
