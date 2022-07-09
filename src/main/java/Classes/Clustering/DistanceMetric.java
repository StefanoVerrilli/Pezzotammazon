package Classes.Clustering;

import java.util.Map;

/**
 * Interfaccia astratta che definisce i metodi che le interfacce concrete devono implementare per
 * eseguire il calcolo della distanza nell'algoritmo di clustering. Implementa il pattern Bridge con {@link Clustering}.
 * @see Clustering
 * @see EuclideanDistance
 */

public interface DistanceMetric {

    double calculate(Map<String,Integer> f1, Map<String,Double> centroid);
}
