import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnitParamsRunner.class)
public class BasicStatisticTest {

    static class TestParameters{
        public Double[] caseValues;
        public Double expectedResult;
        public TestParameters(Double[] params, Double expectedResult){
            this.caseValues = params;
            this.expectedResult =expectedResult;
        }
        @Override
        public String toString(){
            String tostr = "Expected: " + expectedResult + "\n values: { ";

            for (int i = 0; i < caseValues.length; i++) {
                tostr += caseValues[i];
                if (i!=caseValues.length-1) tostr+=", ";
            }

            return tostr + " }";
        }

    }

    private BasicStatistic sut;

    @Before
    public void setup_testCase(){
        sut = new BasicStatistic();
    }


    @Test
    public void test_numberOfDataItems_ShouldInitiallyBeZero(){
        int result = sut.numberOfDataItems();
        assertThat("The list was not initialized empty.", result, equalTo(0));
    }

    @Test
    public void test_numberOfDataItems_ShouldNotBeZeroAfterAdding(){
        sut.addDoubleToData(11.11);
        int result = sut.numberOfDataItems();
        assertThat("No items were actually added to the list.", result, not(0));
    }

    @Test
    public void test_clearData_ShouldResultInZeroElements(){
        sut.addDoubleToData(11.11);
        sut.addDoubleToData(22.22);
        sut.addDoubleToData(33.33);
        int numberOfItems = sut.numberOfDataItems();
        assertThat( numberOfItems, equalTo(3));

        sut.clearData();
        int result = sut.numberOfDataItems();
        assertThat("The results were not successfully cleared.", result, equalTo(0));
    }

    @Test
    public void test_sum_ShouldResultInZeroWhenNoElementsArePresent(){
        Double result = sut.sum();
        Double expected = 0.0;
        assertThat("The returned value was not the correct sum.", result, equalTo(expected));
    }

    @Test
    public void test_sum_ShouldBeCalculatedProperly(){
        sut.addDoubleToData(11.11);
        sut.addDoubleToData(22.22);
        Double result = sut.sum();
        Double expected = 33.33;
        assertThat("The returned value was not the correct sum.", result, equalTo(expected));
    }

    @Test(expected = NoDataItemsException.class)
    public void test_highestValue_ShouldThrowNoDataItemsException() throws NoDataItemsException {
        Double result = sut.highestValue();
    }

    @Test
    public void test_highestValue_ShouldReturnTheHighestValue() throws NoDataItemsException {
        Double highest = 777.77;
        sut.addDoubleToData(11.11);
        sut.addDoubleToData(22.22);
        sut.addDoubleToData(highest);
        sut.addDoubleToData(33.33);
        Double result = sut.highestValue();
        assertThat("The returned value was not the highest one.", result, equalTo(highest));
    }

    @Test(expected = NoDataItemsException.class)
    public void test_getMean_ShouldThrowNoDataItemsException() throws NoDataItemsException {
        Double result = sut.getMean();
    }

    private static final TestParameters[] getMeanCases() {
        return new TestParameters[] {
                new TestParameters(new Double[] {1.0, 2.0, 3.0}, 2.0),
                new TestParameters(new Double[] {1.1, 2.2, 3.3}, 2.2),
                new TestParameters(new Double[] {2.2, 3.3, 4.4}, 3.3),
                new TestParameters(new Double[] {1.1, 2.2, 3.3,4.4,5.5}, 3.3),
                new TestParameters(new Double[] {111.111, 222.222, 333.333}, 222.222),

        };
    }
    @Test
    @Parameters(method = "getMeanCases")
    public void test_getMean_ShouldReturnTheAverageValue(TestParameters parameters) throws NoDataItemsException {
        System.out.println(parameters);
        for (Double d :
                parameters.caseValues) {
            sut.addDoubleToData(d);
        }
        Double result = sut.getMean();
        assertThat("The returned value was not the correct average.", 0.00000001, greaterThan(Math.abs(result-parameters.expectedResult)));
    }

    @Test(expected = NoDataItemsException.class)
    public void test_getMedian_ShouldThrowNoDataItemsException() throws NoDataItemsException {
        Double result = sut.getMedian();
    }

    @Test
    public void test_getMedian_ShouldReturnTheMedian_OneElement() throws NoDataItemsException {
        Double median = 2.0;
        sut.addDoubleToData(2.0);
        Double result = sut.getMean();
        assertThat("The returned value was not the correct median.", result, equalTo(median));
    }

    @Test
    public void test_getMedian_ShouldReturnTheMedian_EvenElements() throws NoDataItemsException {
        Double median = 2.5;
        sut.addDoubleToData(2.0);
        sut.addDoubleToData(3.0);
        Double result = sut.getMean();
        assertThat("The returned value was not the correct median.", result, equalTo(median));
    }

    @Test
    public void test_getMedian_ShouldReturnTheMedian_UnsortedOddElements() throws NoDataItemsException {
        Double median = 3.33;
        sut.addDoubleToData(2.22);
        sut.addDoubleToData(4.44);
        sut.addDoubleToData(1.11);
        sut.addDoubleToData(5.55);
        sut.addDoubleToData(3.33);
        Double result = sut.getMean();
        assertThat("The double difference was more than the allowed.",0.00000001, greaterThan(Math.abs(median-result)));
    }

    @Test(expected = NoDataItemsException.class)
    public void test_getStandardDeviation_ShouldThrowNoDataItemsException() throws NoDataItemsException {
        Double result = sut.getStandardDeviation();
    }

    @Test
    public void test_getStandardDeviation_ShouldReturnTheMedian_UnsortedOddElements() throws NoDataItemsException {
        Double standardDeviation = 	1.569777054;
        sut.addDoubleToData(2.22);
        sut.addDoubleToData(4.44);
        sut.addDoubleToData(1.11);
        sut.addDoubleToData(5.55);
        sut.addDoubleToData(3.33);
        Double result = sut.getStandardDeviation();
        assertThat("The standard deviation was incorrect.",0.00000001, greaterThan(Math.abs(standardDeviation-result)));
    }
}