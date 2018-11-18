import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnitParamsRunner.class)
public class BasicStatisticTest {

    static class TestParameters {
        public Double[] caseValues;
        public Double expectedResult;

        public TestParameters(Double[] params, Double expectedResult) {
            this.caseValues = params;
            this.expectedResult = expectedResult;
        }

        @Override
        public String toString() {
            String tostr = "\tExpected:\n\t\tresult: " + expectedResult + "\n\t\tvalues: { ";

            for (int i = 0; i < caseValues.length; i++) {
                tostr += caseValues[i];
                if (i != caseValues.length - 1) tostr += ", ";
            }

            return tostr + " }";
        }

    }

    private BasicStatistic sut;
    @Rule
    public TestName name = new TestName();

    private void addTestCaseDoublesToSUT(Double[] doubles) {
        for (Double d :
                doubles) {
            sut.addDoubleToData(d);
        }
    }

    @Before
    public void setup_testCase() {
        sut = new BasicStatistic();
        System.out.println("Executing: " + name.getMethodName());
    }


    @Test
    public void test_numberOfDataItems_ShouldInitiallyBeZero() {
        int result = sut.numberOfDataItems();
        assertThat("The list was not initialized empty.", result, equalTo(0));
    }

    @Test
    public void test_numberOfDataItems_ShouldNotBeZeroAfterAdding() {
        sut.addDoubleToData(11.11);
        int result = sut.numberOfDataItems();
        assertThat("No items were actually added to the list.", result, not(0));
    }

    @Test
    public void test_clearData_ShouldResultInZeroElements() {
        sut.addDoubleToData(11.11);
        sut.addDoubleToData(22.22);
        sut.addDoubleToData(33.33);
        int numberOfItems = sut.numberOfDataItems();
        assertThat(numberOfItems, equalTo(3));

        sut.clearData();
        int result = sut.numberOfDataItems();
        assertThat("The results were not successfully cleared.", result, equalTo(0));
    }

    @Test
    public void test_sum_ShouldResultInZeroWhenNoElementsArePresent() {
        Double result = sut.sum();
        Double expected = 0.0;
        assertThat("The returned value was not the correct sum.", result, equalTo(expected));
    }

    @Test
    public void test_sum_ShouldBeCalculatedProperly() {
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

    /**
     * Gets a list of test parameters and expected values for the
     * test_getMean_ShouldReturnTheAverageValue test.
     *
     * @return An array of test parameters.
     */
    private static final TestParameters[] getMeanCases() {
        return new TestParameters[]{
                new TestParameters(new Double[]{1.0, 2.0, 3.0}, 2.0),
                new TestParameters(new Double[]{1.1, 2.2, 3.3}, 2.2),
                new TestParameters(new Double[]{2.2, 3.3, 4.4}, 3.3),
                new TestParameters(new Double[]{1.1, 2.2, 3.3, 4.4, 5.5}, 3.3),
                new TestParameters(new Double[]{111.111, 222.222, 333.333}, 222.222),

        };
    }

    @Test
    @Parameters(method = "getMeanCases")
    public void test_getMean_ShouldReturnTheAverageValue(TestParameters parameters) throws NoDataItemsException {
        System.out.println(parameters);
        addTestCaseDoublesToSUT(parameters.caseValues);
        Double result = sut.getMean();
        assertThat("The returned value was not the correct average.", 0.00000001, greaterThan(Math.abs(result - parameters.expectedResult)));
    }

    @Test(expected = NoDataItemsException.class)
    public void test_getMedian_ShouldThrowNoDataItemsException() throws NoDataItemsException {
        Double result = sut.getMedian();
    }

    /**
     * Gets a list of test parameters and expected values for the
     * test_getMedian_ShouldReturnTheMedian test.
     *
     * @return An array of test parameters.
     */
    private static final TestParameters[] getMedianCases() {
        return new TestParameters[]{
                new TestParameters(new Double[]{2.0}, 2.0),
                new TestParameters(new Double[]{2.0, 3.0}, 2.5),
                new TestParameters(new Double[]{2.22, 4.44, 1.11, 5.55, 3.33}, 3.33), // Unsorted
                new TestParameters(new Double[]{1.1, 2.2, 3.3, 4.4, 5.5}, 3.3),
                new TestParameters(new Double[]{111.111, 222.222, 333.333}, 222.222),

        };
    }

    @Test
    @Parameters(method = "getMedianCases")
    public void test_getMedian_ShouldReturnTheMedian(TestParameters parameters) throws NoDataItemsException {
        System.out.println(parameters);
        addTestCaseDoublesToSUT(parameters.caseValues);
        Double result = sut.getMean();
        assertThat("The returned value was not the correct median.", 0.00000001, greaterThan(Math.abs(result - parameters.expectedResult)));
    }

    @Test(expected = NoDataItemsException.class)
    public void test_getStandardDeviation_ShouldThrowNoDataItemsException() throws NoDataItemsException {
        Double result = sut.getStandardDeviation();
    }

    /**
     * Gets a list of test parameters and expected values for the
     * test_getMedian_ShouldReturnTheMedian test.
     *
     * @return An array of test parameters.
     */
    private static final TestParameters[] getStandardDeviation() {
        return new TestParameters[]{
                new TestParameters(new Double[]{2.0}, 0.0),
                new TestParameters(new Double[]{2.0, 3.0}, 0.5),
                new TestParameters(new Double[]{2.22, 4.44, 1.11, 5.55, 3.33}, 1.569777054),
                new TestParameters(new Double[]{1.1, 2.2, 3.3, 4.4, 5.5}, 1.555634919),
                new TestParameters(new Double[]{111.111, 222.222, 333.333}, 90.7217516),

        };
    }

    @Test
    @Parameters(method = "getStandardDeviation")
    public void test_getStandardDeviation_ShouldReturnTheStandardDeviation(TestParameters parameters) throws NoDataItemsException {
        System.out.println(parameters);
        addTestCaseDoublesToSUT(parameters.caseValues);
        Double result = sut.getStandardDeviation();
        assertThat("The standard deviation was incorrect.", 0.00000001, greaterThan(Math.abs(parameters.expectedResult - result)));
    }
}