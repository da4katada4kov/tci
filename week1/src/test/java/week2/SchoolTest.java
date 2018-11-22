package week2;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SchoolTest {
    School sut;
    String name = "SchoolName";
    Date openDate = new Date(2018,10,10);

    @Test
    public void test_School_ShouldBeInitializedProperly(){
        sut = new School(name, openDate);
        assertThat(sut.getCourses().size(), equalTo(0));
        assertThat(sut.getName(), equalTo(name));
        assertThat(sut.getOpenDate(), equalTo(openDate));
    }
}