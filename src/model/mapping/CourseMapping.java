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

public class CourseMapping {
    public static final String DATA_PATH = "data/course.json";
    public static final int SUCCESS = 0;
    public static final int DUPLICATE_ID = 1;
    public static final int COURSE_NOT_FOUND = 2;


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
