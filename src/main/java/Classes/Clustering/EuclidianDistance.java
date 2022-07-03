package Classes.Clustering;

import java.util.Map;

public class EuclidianDistance implements DistanceMetric<Record>{
    @Override
    public double calculate(Map<String, Double> f1, Map<String, Double> centroid) {
        double sum = 0;
        for(String key : f1.keySet()){
            Double v1 = f1.get(key);
            Double v2 = centroid.get(key);
            if(v1 != null && v2 != null){
                sum += Math.pow(v1 - v2,2);
            }
        }
        return Math.sqrt(sum);
    }

}
