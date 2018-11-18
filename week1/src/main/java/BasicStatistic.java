import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * very simple implementation of the BasicStatisticInterface
 */
public class BasicStatistic implements BasicStatisticInterface {

    private List<Double> values;

    public BasicStatistic(){
        this.values = new ArrayList<Double>();
    }

    @Override
    public void addDoubleToData(Double valueToAdd){
        this.values.add(valueToAdd);
    }

    @Override
    public void clearData(){
        this.values.clear();
    }
	
    @Override
    public int numberOfDataItems(){
        return values.size();
    }

    @Override
    public Double sum(){
        double rv = 0.0;
        return rv;
    }

    @Override
    public Double highestValue() throws NoDataItemsException {
        double rv = 0.0;
        return rv;
    }

    @Override
    public Double getMean() throws NoDataItemsException {
        double rv = 0.0;
        return rv;
    }

    @Override
    public double getMedian() throws NoDataItemsException {
        double rv = 0.0;
        return rv;
    }

    @Override
    public double getStandardDeviation() throws NoDataItemsException {
        double rv = 0.0;
        return rv;
    }
}
