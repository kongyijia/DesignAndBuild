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

public class VideoMapping {
    public static final String DATA_PATH = "data/video.json";
    public static final String VIDEO_PATH = "data/video/";
    public static final String COVER_PATH = "data/image/";
    public static final String DEFAULT_COVER = "defaultCover.png";
    public static final int SUCCESS = 0;
    public static final int DUPLICATE_ID = 1;
    public static final int VIDEO_DATA_NOT_FOUND = 2;
    public static final int ORIGINAL_VIDEO_FILE_NOT_FOUND = 3;
    public static final int NOT_MP4 = 4;
    public static final int FILE_DELETE_ERROR = 5;
    public static final int VIDEO_FILE_NOT_FOUND = 6;
    public static final int NOT_IMAGE = 7;

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

    public static int add(Video video, String videoOriginalPath) throws EncoderException, IOException {
        return addVideo(video, videoOriginalPath, COVER_PATH + DEFAULT_COVER);
    }

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
        videos.add(video);
        writeAll(videos);
        return SUCCESS;
    }

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

    private static void writeAll(ArrayList<Video> video) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter(DATA_PATH));
        writer.config(SerializerFeature.DisableCircularReferenceDetect, true);
        writer.config(SerializerFeature.PrettyFormat, true);
        writer.startArray();
        for (Video v : video) {
            writer.writeValue(v);
        }
        writer.endArray();
        writer.close();
    }
}
