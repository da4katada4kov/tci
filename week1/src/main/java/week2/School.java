package week2;

import java.util.ArrayList;
import java.util.Date;

public class School {

    private String name;
    private Date openDate;
    private ArrayList<Course> courses;

    public School(String name, Date openDate) {
        if (name == null || openDate == null) throw new IllegalArgumentException();
        this.name = name;
        this.openDate = openDate;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) throws CourseException, DuplicateCourseException {
        if(course.getStartDate().before(this.openDate)) throw new CourseException();
        if (courses.stream().anyMatch((c -> c.getName().equals(course.getName())))) throw new DuplicateCourseException();
        courses.add(course);
    }

    public String getName() {
        return name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Course getCourseByName(String name) {
        return courses.stream().filter((c) -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
