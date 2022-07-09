package Classes.Clustering;

import java.util.Map;

/**
 * Implementazione di metrica utilizzando l'interfaccia {@link DistanceMetric}. Calcolo della distanza euclidea.
 * @see DistanceMetric
 */

public class EuclideanDistance implements DistanceMetric{

    /**
     * Calcola la distanza euclidea tra un intero di una mappa passata in input e il centroide.
     * @param f1 Mappa stringa-intero di cui si vuole calcolare la distanza dal centroide.
     * @param centroid  Mappa stringa-numero contenente le coordinate del centroide.
     * @return Distanza euclidea calcolata.
     */
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
