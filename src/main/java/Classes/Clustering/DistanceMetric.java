package Classes.Clustering;

import Classes.Pair;

import java.util.Map;

public interface DistanceMetric<T> {

    double calculate(Map<String,Double> f1, Map<String,Double> centroid);
}
