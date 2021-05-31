package model.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import model.Course;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Map {@link Course} data to JSON database.
 * This "JSON database" is a self-defined database and it should only be used in this software.
 * <p>
 * Offering {@code add}, {@code delete} and {@code find} methods for course data.
 * In this class, we use {@link com.alibaba.fastjson.JSON} to manage our {@link Course} POJO.
 *
 * @author Yubo Wu
 * @version 1.2
 * @see JSON
 * @see Course
 * @since 15 May 2021
 */
public class CourseMapping {
    /**
     * Course JSON database path
     */
    public static final String DATA_PATH = "data/course.json";
    /**
     * Operation success status code
     */
    public static final int SUCCESS = 0;
    /**
     * Duplicate Course ID
     */
    public static final int DUPLICATE_ID = 1;
    /**
     * Cannot found Course in database
     */
    public static final int COURSE_NOT_FOUND = 2;


    /**
     * Add {@link Course} data to JSON database.
     *
     * @param course instance of {@link Course}
     * @return status code: {@link CourseMapping#SUCCESS} or {@link CourseMapping#DUPLICATE_ID}.
     * @throws IOException when IO issue occur
     */
    public static int add(Course course) throws IOException {
        ArrayList<Course> courses = readAllCourse();
        for (Course c : courses) {
            if (c.getId() == course.getId())
                return DUPLICATE_ID;
        }
        courses.add(course);
        writeAll(courses);
        return SUCCESS;
    }

    /**
     * Delete {@link Course} data from JSON database.
     *
     * @param id Course ID
     * @return {@link CourseMapping#SUCCESS} or {@link CourseMapping#COURSE_NOT_FOUND}.
     * @throws IOException when IO issue occur
     */
    public static int delete(int id) throws IOException {
        int index = -1;
        ArrayList<Course> courses = readAllCourse();
        for (Course course : courses) {
            if (course.getId() == id)
                index = courses.indexOf(course);
        }
        if (index == -1)
            return COURSE_NOT_FOUND;
        courses.remove(index);
        writeAll(courses);
        return SUCCESS;
    }

    /**
     * Find {@link Course} from JSON database, and return the results to a {@link ArrayList}.
     * The parameter are {@link HashMap}, you need to put all {@code AND} conditions in hashmap.
     * This {@code find} method can't handle other conditions except {@code AND}, like {@code OR} or {@code BETWEEN} or others.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList<Course>} contained results.
     * @throws FileNotFoundException when {@link CourseMapping#DATA_PATH} not found
     */
    public static ArrayList<Course> find(HashMap<String, String> map) throws FileNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            String s = reader.readString();
            JSONObject object = JSON.parseObject(s);
            int tmp = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String v = object.getString(entry.getKey());
                if (v == null)
                    continue;
                if (v.equals(entry.getValue()))
                    tmp++;
            }
            if (tmp == map.size())
                courses.add(JSON.parseObject(s, Course.class));
        }
        reader.endArray();
        reader.close();
        return courses;
    }

    /**
     * Read {@link CourseMapping#DATA_PATH} to the program and convert it to POJO using fastJSON.
     *
     * @return all data in {@link CourseMapping#DATA_PATH}
     * @throws FileNotFoundException when {@link CourseMapping#DATA_PATH} not found
     */
    public static ArrayList<Course> readAllCourse() throws FileNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            Course course = reader.readObject(Course.class);
            courses.add(course);
        }
        reader.endArray();
        reader.close();
        return courses;
    }

    /**
     * Write all {@code Course} in {@link ArrayList<Course>} to the file using fastJSON.
     * This method will overwrite the {@link CourseMapping#DATA_PATH} file, so this method is set to be {@code private} to protect the data.
     *
     * @param courses the {@link Course} data that you want to write
     * @throws IOException when IO issue occur
     */
    private static void writeAll(ArrayList<Course> courses) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter(DATA_PATH));
        writer.config(SerializerFeature.DisableCircularReferenceDetect, true);
        writer.config(SerializerFeature.PrettyFormat, true);
        writer.startArray();
        for (Course c : courses)
            writer.writeValue(c);
        writer.endArray();
        writer.close();
    }
}
