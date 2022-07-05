package Classes.Clustering;

import java.util.Map;

public class EuclideanDistance implements DistanceMetric{
    @Override
    public double calculate(Map<String, Integer> f1, Map<String, Double> centroid) {
        double sum = 0;
        for(String key : f1.keySet()){
            Integer v1 = f1.get(key);
            Double v2 = centroid.get(key);
            if(v1 != null && v2 != null){
                sum += Math.pow(v1 - v2,2);
            }
        }
        return Math.sqrt(sum);
    }

}
