import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class HashMapExercize {

    HashMap sut;

    @Rule
    public TestName name = new TestName();
    @Before
    public void setup_testCase() {
        sut = new HashMap();
        System.out.println(name.getMethodName());
    }


    @Test
    public void test_put_ShouldBeGettable() {
        String key = "key";
        String value = "value";
        sut.put(key,value);
        assertThat(sut.get(key), equalTo(value));
    }

    @Test
    public void test_null_CouldBeAKey() {
        String key = null;
        String value = "value";
        sut.put(key,value);
        assertThat(sut.isEmpty(), equalTo(false));
        assertThat(sut.size(), equalTo(1));
        assertThat(sut.get(key), equalTo(value));
    }

    @Test
    public void test_put_ShouldReplaceTheOldValue() {
        String key = "key";
        String value = "value";
        String newValue = "newValue";
        sut.put(key,value);
        assertThat(sut.get(key), equalTo(value));
        sut.put(key,newValue);
        assertThat(sut.get(key), not(value));
        assertThat(sut.get(key), equalTo(newValue));
    }

    @Test
    public void test_clear_ShouldRemoveAllOfTheContent() {
        String key = "key";
        String key2 = "key2";
        String value = "value";
        String newValue = "newValue";
        sut.put(key,value);
        sut.put(key2,newValue);
        assertThat(sut.size(), equalTo(2));

        sut.clear();
        assertThat(sut.isEmpty(), equalTo(true));
        assertThat(sut.size(), equalTo(0));
    }
}
