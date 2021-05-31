package model;

import model.mapping.ClientMapping;
import java.util.HashMap;

/**
 * Course information model class. The information consist of {@link User}, {@link Coach} and {@link Course}.
 *
 * @author Jufeng Sun
 * @version 1.0
 * @since 18 May 2021
 */

public class CourseInfo{
    private Coach coach;
    private User user;
    private Course course;

    public CourseInfo(Course course, User user, Coach coach) {
        this.course = course;
        this.user = user;
        this.coach = coach;
    }

    public CourseInfo() {
    }

    /**
     * This method is used to integrate the {@link Coach} and {@link User} information corresponding to the {@link Course}
     * and convert it to {@link CourseInfo}
     *
     * @param course Courses that need to be displayed
     * @return course information
     */
    public static CourseInfo generateCourseInfo(Course course){
        try{
            HashMap<String,String> searchUser = new HashMap<>();
            searchUser.put("id", Integer.toString(course.getUserId()));
            User user = ClientMapping.findUser(searchUser).get(0);

            HashMap<String,String> searchCoach = new HashMap<>();
            searchCoach.put("id", Integer.toString(course.getCoachId()));
            Coach coach = ClientMapping.findCoach(searchCoach).get(0);
            return new CourseInfo(course, user, coach);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
