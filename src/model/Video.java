package model;

import java.util.ArrayList;

public class Video {

    private int id;
    private int author;
    private String name;
    private String src;     // video path
    private int tag;        // tag
    private ArrayList<String> types; // all type
    private long time;       // 单位：seconds
    private String coverSrc; // video cover source path

    public Video(int id, int author, String name, int tag, ArrayList<String> types) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.tag = tag;
        this.types = types;
        // default
        this.src = "";
        this.coverSrc = "";
        this.time = 0;
    }

    public Video(int id, int author, String name, String src, int tag, ArrayList<String> types, long time, String coverSrc) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.src = src;
        this.tag = tag;
        this.types = types;
        this.time = time;
        this.coverSrc = coverSrc;
    }

    public Video(int id, int author, String name) {
        this.id = id;
        this.author = author;
        this.name = name;
        // default
        this.tag = 0;
        this.src = "";
        this.time = 0;
        this.types = new ArrayList<>();
        this.coverSrc = "";
    }

    public Video() {
    }

    public String getCoverSrc() {
        return coverSrc;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", src='" + src + '\'' +
                ", tag=" + tag +
                ", types=" + types +
                ", time=" + time +
                ", coverSrc='" + coverSrc + '\'' +
                '}';
    }
}
