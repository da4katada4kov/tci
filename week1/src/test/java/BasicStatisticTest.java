import org.junit.*;

public class BasicStatisticTest {

    private BasicStatistic sut;

    @Test
    public void test_numberOfDataItems_ShouldInitiallyBeZero(){
        sut = new BasicStatistic();
        int result = sut.numberOfDataItems();
        Assert.assertEquals(0,result);
    }

    @Test
    public void test_numberOfDataItems_ShouldNotBeZeroAfterAdding(){
        sut = new BasicStatistic();
        sut.addDoubleToData(11.11);
        int result = sut.numberOfDataItems();
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void test_clearData_ShouldResultInZeroElements(){
        sut = new BasicStatistic();
        sut.addDoubleToData(11.11);
        sut.addDoubleToData(22.22);
        sut.addDoubleToData(33.33);
        int numberOfItems = sut.numberOfDataItems();
        Assert.assertEquals(3,numberOfItems);

        sut.clearData();
        int result = sut.numberOfDataItems();
        Assert.assertEquals("The results were not successfully cleared.",0,result);
    }
}