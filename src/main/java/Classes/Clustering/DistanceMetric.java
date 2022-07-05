package Classes.Clustering;

import Classes.Pair;

import java.util.Map;

public interface DistanceMetric {

    double calculate(Map<String,Integer> f1, Map<String,Double> centroid);
}
