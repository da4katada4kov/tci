package week2;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CourseTest {

    Course sut;
    String name = "CourseName";
    Date earliestDate = new Date(2018,10,10);
    Date middleDate = new Date(2018,11,11);
    Date latestDate = new Date(2018,12,12);

    @Test
    public void test_constructor_shouldInitializeTheObject() throws CourseDateException {

        sut = new Course(name,earliestDate,latestDate);
        assertThat("The course was not initialized properly.", sut.getName(), equalTo(name));
        assertThat("The course was not initialized properly.", sut.getStartDate(), equalTo(earliestDate));
        assertThat("The course was not initialized properly.", sut.getEndDate(), equalTo(latestDate));
    }

    @Test(expected = CourseDateException.class)
    public void test_constructor_shouldThrowCourseDateExceptionWhenGivenInvalidStartDate() throws CourseDateException {

        sut = new Course(name,latestDate,middleDate);
    }
}