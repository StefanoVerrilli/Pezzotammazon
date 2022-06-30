package Classes.Clustering;

public abstract class Clustering {

    protected DistanceMetric distanceMethod;

    public Clustering(DistanceMetric metric){
        this.distanceMethod = metric;
    }
}
