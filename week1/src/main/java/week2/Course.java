package week2;

import java.util.Calendar;

public class Course {

    private String name;
    private Calendar startDate;
    private Calendar endDate;


    public Course(String name, Calendar startDate, Calendar endDate) throws CourseDateException {
        this.name = name;
        if (startDate.after(endDate)) throw new CourseDateException();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }
}
