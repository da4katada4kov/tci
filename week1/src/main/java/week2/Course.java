package week2;

import java.util.Date;

public class Course {

    private String name;
    private Date startDate;
    private Date endDate;


    public Course(String name, Date startDate, Date endDate) throws CourseDateException {
        this.name = name;
        if (startDate.after(endDate)) throw new CourseDateException();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }
}
