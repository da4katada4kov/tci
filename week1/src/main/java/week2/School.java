package week2;

import java.util.ArrayList;
import java.util.Date;

public class School {

    private String name;
    private Date openDate;
    private ArrayList<Course> courses;

    public School(String name, Date openDate){
        this.name = name;
        this.openDate = openDate;
        this.courses = new ArrayList<>();
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
}