package Classes.Clustering;

import java.util.List;
import java.util.Map;

public abstract class Clustering {

    protected DistanceMetric distanceMethod;

    public Clustering(DistanceMetric metric){
        this.distanceMethod = metric;
    }

    public double Distance(Map<String, Integer> f1, Map<String, Double> centroid){
        return  this.distanceMethod.calculate(f1,centroid);
    }

    public abstract Map<centroid, List<Record>> fit(List<Record> records,
            int k,
            int maxIterations);
}
