package Classes.Clustering;

import java.util.Map;

public interface DistanceMetric {

    double calculate(Map<String,Integer> f1, Map<String,Double> centroid);
}
