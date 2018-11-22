package Data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToBeTestedTest {

    ToBeTested cut;
    @Before
    public void setUp() throws Exception {
        cut = new ToBeTested("fn","ln");
    }

    @Test
    public void test_ToBeTestedTest_ShouldAssignNames(){
        Assert.assertNotNull(cut.getFirstName());
    }

    @Test
    public void test_ToBeTestedTest_FullNameShouldBeCorrect(){
        Assert.assertEquals(cut.getFullName(), cut.getFirstName()+" "+ cut.getLastName());
    }
}