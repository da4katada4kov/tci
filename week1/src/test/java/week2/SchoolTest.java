package week2;

import org.junit.*;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SchoolTest {
    School sut;
    String name = "SchoolName";
    Date openDate = new Date(2018,10,10);

    String courseName = "CourseName";
    Date earliestDate = new Date(2018,9,9);
    Date validStartDate = new Date(2018,11,11);
    Date validEndDate = new Date(2018,12,12);

    @Test
    public void test_School_shouldBeInitializedProperly(){
        sut = new School(name, openDate);
        assertThat(sut.getCourses().size(), equalTo(0));
        assertThat(sut.getName(), equalTo(name));
        assertThat(sut.getOpenDate(), equalTo(openDate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_School_nullAsNameShouldThrowIllegalArgumentException(){
        sut = new School(null, openDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_School_nullAsOpenDateShouldThrowIllegalArgumentException(){
        sut = new School(name, null);
    }

    @Test
    public void test_add_courseShouldInsertACourseToTheSchool() throws CourseDateException, CourseException, DuplicateCourseException {
        sut = new School(name, openDate);
        assertThat(sut.getOpenDate(), equalTo(openDate));
        Course valid = new Course(courseName,validStartDate,validEndDate);

        sut.addCourse(valid);
        assertThat(sut.getCourses().size(), equalTo(1));
    }

    @Test(expected = CourseException.class)
    public void test_add_courseWithStartDateBeforeSchoolOpenDateShouldThrowCourseException() throws CourseDateException, CourseException, DuplicateCourseException {
        Course invalidCourse = new Course(courseName,earliestDate, validEndDate);

        sut = new School(name, openDate);
        sut.addCourse(invalidCourse);
    }

    @Test(expected = DuplicateCourseException.class)
    public void test_add_courseWithDuplicateNameShouldThrowDuplicateCourseException() throws CourseDateException, CourseException, DuplicateCourseException {
        Course valid = new Course(courseName,validStartDate,validEndDate);

        sut = new School(name, openDate);
        sut.addCourse(valid);
        sut.addCourse(valid);
    }
}