import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        return values.stream()
                .mapToDouble(f -> f.doubleValue())
                .sum();
    }

    @Override
    public Double highestValue() throws NoDataItemsException {
       return values.stream()
               .mapToDouble(f -> f.doubleValue())
               .max()
               .orElseThrow(NoDataItemsException::new);
    }

    @Override
    public Double getMean() throws NoDataItemsException {
        return values.stream()
                .mapToDouble(f -> f.doubleValue())
                .average()
                .orElseThrow(NoDataItemsException::new);
    }

    @Override
    public double getMedian() throws NoDataItemsException {
        if (values.size()==0) throw new NoDataItemsException();
        List<Double> temp = values;
        Collections.sort(temp);
        Double median;
        int middle = ((temp.size()) / 2);
        if(temp.size() % 2 == 0){
            Double medianA = temp.get(middle);
            Double medianB = temp.get(middle-1);
            median = (medianA + medianB) / 2;
        } else{
            median = temp.get(middle+1);
        }
        return median;
    }

    @Override
    public double getStandardDeviation() throws NoDataItemsException {
        if (values.size()==0) throw new NoDataItemsException();
        double sum = sum();
        double standardDeviation = 0.0;
        int length = numberOfDataItems();
        double mean = getMean();
        for(double num: values) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
    }
}
