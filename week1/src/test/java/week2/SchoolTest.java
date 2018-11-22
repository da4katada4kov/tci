package week2;

import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SchoolTest {
    School sut;
    // Names
    String name = "Name1";
    String courseName = "Name2";
    // Calendar objects used by the fixture.
    Calendar cal;   
    Calendar earliestDate;
    Calendar openDate;
    Calendar validStartDate;
    Calendar validEndDate;

    Course valid;
    Course valid2;
    Course invalidCourse;

    @Before
    public void setup() throws CourseDateException {
        cal = Calendar.getInstance();
        earliestDate = (Calendar) cal.clone();

        openDate = (Calendar) cal.clone();
        openDate.add(Calendar.DAY_OF_MONTH,1);

        validStartDate = (Calendar) openDate.clone();
        validStartDate.add(Calendar.DAY_OF_MONTH, 2);

        validEndDate = (Calendar) validStartDate.clone();
        validEndDate.add(Calendar.DAY_OF_MONTH, 10);

        sut = new School(name, openDate);
        valid = new Course(courseName,validStartDate,validEndDate);
        invalidCourse = new Course(courseName,earliestDate, validEndDate);
        valid2 = new Course(name,validStartDate,validEndDate);
    }


    @Test
    public void test_School_shouldBeInitializedProperly(){
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
        sut.addCourse(valid);
        assertThat(sut.getCourses().size(), equalTo(1));
    }

    @Test(expected = CourseException.class)
    public void test_add_courseWithStartDateBeforeSchoolOpenDateShouldThrowCourseException() throws CourseDateException, CourseException, DuplicateCourseException {
        sut.addCourse(invalidCourse);
    }

    @Test(expected = DuplicateCourseException.class)
    public void test_add_courseWithDuplicateNameShouldThrowDuplicateCourseException() throws CourseDateException, CourseException, DuplicateCourseException {
        sut.addCourse(valid);
        sut.addCourse(valid);
    }

    @Test
    public void test_getCourseByName_shouldReturnNullWhenNoCourseIsFound() throws CourseDateException, CourseException, DuplicateCourseException {
        assertThat(sut.getOpenDate(), equalTo(openDate));
        assertThat(sut.getCourseByName("no such"), equalTo(null));
    }

    @Test
    public void test_getCourseByName_shouldReturnAFoundCourseByName() throws CourseDateException, CourseException, DuplicateCourseException {
        
        sut.addCourse(valid);

        Course result = sut.getCourseByName(courseName);

        assertThat(result.getName(), equalTo(courseName));
        assertThat(result.getStartDate(), equalTo(validStartDate));
        assertThat(result.getEndDate(), equalTo(validEndDate));
        assertThat(sut.getNamesOfCourses().size(), equalTo(1));
    }

    @Test
    public void test_getNamesOfCourses_shouldReturnAListOfAllCourseNames() throws CourseDateException, CourseException, DuplicateCourseException {
        sut.addCourse(valid);
        sut.addCourse(valid2);

        ArrayList<String> result = sut.getNamesOfCourses();

        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo(courseName));
        assertThat(result.get(1), equalTo(name));
    }

    @Test
    public void test_getCourses_shouldReturnAListOfAllCourses() throws CourseDateException, CourseException, DuplicateCourseException {

        sut.addCourse(valid);
        sut.addCourse(valid2);

        ArrayList<Course> result = sut.getCourses();
        
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo(valid));
        assertThat(result.get(1), equalTo(valid2));
    }
}