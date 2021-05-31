package model;

import java.util.ArrayList;

/**
 * Video model class.
 *
 * @author Yubo Wu
 * @version 1.2
 * @since 12 May 2021
 */
public class Video {
    /**
     * Video ID
     */
    private int id;
    /**
     * Video author. Which is the {@code ID} in {@link Coach} or {@link Administrator}.
     */
    private int author;
    /**
     * Video name/title
     */
    private String name;
    /**
     * Video source path
     */
    private String src;     // video path
    /**
     * Video tag
     */
    private int tag;        // tag
    /**
     * Video types. See in {@link model.mapping.VideoTypeMapping}
     */
    private ArrayList<String> types; // all type
    /**
     * Video duration (with seconds)
     */
    private long time;       // with seconds
    /**
     * Video cover source path
     */
    private String coverSrc; // video cover source path

    /**
     * Constructor of {@link Video}. With some default values.
     *
     * @param id     {@link Video#id}
     * @param author {@link Video#author}
     * @param name   {@link Video#name}
     * @param tag    {@link Video#tag}
     * @param types  {@link Video#types}
     */
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

    /**
     * Constructor of {@link Video}. Mainly used in fastJSON.
     *
     * @param id       {@link Video#id}
     * @param author   {@link Video#author}
     * @param name     {@link Video#name}
     * @param src      {@link Video#src}
     * @param tag      {@link Video#tag}
     * @param types    {@link Video#types}
     * @param time     {@link Video#time}
     * @param coverSrc {@link Video#coverSrc}
     */
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

    /**
     * Constructor of {@link Video}. With some default values.
     *
     * @param id     {@link Video#id}
     * @param author {@link Video#author}
     * @param name   {@link Video#name}
     */
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

    /**
     * Constructor of {@link Video}. With some default values.
     */
    public Video() {
    }

    /**
     * @return {@link Video#coverSrc}
     */
    public String getCoverSrc() {
        return coverSrc;
    }

    /**
     * @return {@link Video#author}
     */
    public int getAuthor() {
        return author;
    }

    /**
     * @param author {@link Video#author}
     */
    public void setAuthor(int author) {
        this.author = author;
    }

    /**
     * @param coverSrc {@link Video#coverSrc}
     */
    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    /**
     * @return {@link Video#src}
     */
    public String getSrc() {
        return src;
    }

    /**
     * @param src {@link Video#src}
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * @return {@link Video#id}
     */
    public int getId() {
        return id;
    }

    /**
     * @param id {@link Video#id}
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return {@link Video#name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link Video#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link Video#tag}
     */
    public int getTag() {
        return tag;
    }

    /**
     * @param tag {@link Video#tag}
     */
    public void setTag(int tag) {
        this.tag = tag;
    }

    /**
     * @return {@link Video#types}
     */
    public ArrayList<String> getTypes() {
        return types;
    }

    /**
     * @param types {@link Video#types}
     */
    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    /**
     * @return {@link Video#time}
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time {@link Video#time}
     */
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
