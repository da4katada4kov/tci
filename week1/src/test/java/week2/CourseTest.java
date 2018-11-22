package week2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
    Course sut;

    Calendar cal;

    String name = "CourseName";
    Calendar earliestDate; // cal.add(Calendar.DAY_OF_MONTH, -1);
    Calendar middleDate; // = new Date(2018, 11, 11);
    Calendar latestDate; // = new Date(2018, 12, 12);

    @Before
    public void setUp() {
        cal = Calendar.getInstance();
        earliestDate = (Calendar) cal.clone();
        earliestDate.add(Calendar.DAY_OF_MONTH, 10);
        middleDate = (Calendar) earliestDate.clone();
        middleDate.add(Calendar.YEAR, 1);
        latestDate = (Calendar) middleDate.clone();
        latestDate.add(Calendar.YEAR, 2);
    }

    @Test
    public void test_constructor_shouldInitializeTheObject() throws CourseDateException {

        System.out.println("Before all");
        sut = new Course(name, earliestDate, latestDate);
        assertThat("The course was not initialized properly.", sut.getName(), equalTo(name));
        assertThat("The course was not initialized properly.", sut.getStartDate(), equalTo(earliestDate));
        assertThat("The course was not initialized properly.", sut.getEndDate(), equalTo(latestDate));
    }

    @Test(expected = CourseDateException.class)
    public void test_constructor_shouldThrowCourseDateExceptionWhenGivenInvalidStartDate() throws CourseDateException {
        sut = new Course(name, latestDate, middleDate);
    }
}