package Classes.Clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementazione del clustering con K-Means, estendendo {@link Clustering}. Utilizza il pattern Bridge con {@link Clustering}, {@link DistanceMetric}.
 * @see Clustering
 */

public class KMeans extends Clustering{

    /**
     * Costruttore.
     * @param metric Tipo di metrica utilizzata per il calcolo delle distanze.
     */
    public KMeans(DistanceMetric metric) {
        super(metric);
    }

    /**
     * Implementa il metodo astratto definito in {@link Clustering}.
     * @param records Lista di utenti con le caratteristiche calcolate.
     * @param k Numero massimo per la generazione casuale dei centroidi.
     * @param maxIterations Numero massimo di iterazioni da eseguire.
     * @return
     */
    public Map<centroid, List<Record>> fit(List<Record> records,
    int k,
    int maxIterations){
        List<centroid> centroids = randomCentroids(records,k);
        Map<centroid,List<Record>> clusters = new HashMap<>();
        Map<centroid,List<Record>> lastState = new HashMap<>();

        for(int i=0;i<maxIterations;i++){
            boolean isLastIteration = i == maxIterations -1;
            for(Record record : records){
                centroid centroid = nearestCentroid(record,centroids);
                assignToCluster(clusters,record,centroid);
            }


            boolean ShouldTerminate  = isLastIteration || clusters.equals(lastState);
            lastState = clusters;
            if(ShouldTerminate)
                break;

            centroids = relocateCentroids(clusters);
            clusters = new HashMap<>();
        }
        return lastState;
    }

    /**
     * Genera centroidi casuali.
     * @param records Lista degli utenti con le caratteristiche calcolate.
     * @see Record
     * @param k Numero massimo per la generazione casuale.
     * @return Lista di centroidi.
     * @see centroid
     */
    private static List<centroid> randomCentroids(List<Record> records,int k){
        List<centroid> centroids = new ArrayList<>();
        Map<String ,Double> maxs = new HashMap<>();
        Map<String ,Double> mins = new HashMap<>();

        for(Record record : records){
            record.getFeatures().forEach((key,value) -> {
                maxs.compute(key,(k1,max) -> max == null || value > max ? value : max);

                mins.compute(key,(k1,min) -> min == null || value < min ? value : min);
            });
        }
    List<String > attributes = records.stream()
                            .flatMap(e -> e.getFeatures().keySet().stream())
                            .collect(Collectors.toList());
    for(int i=0;i<k;i++){
        Map<String,Double> coordinates = new HashMap<>();
        attributes.forEach(e -> coordinates.put(e,0.0));
        for(String attribute : attributes){
            double max = maxs.get(attribute);
            double min = mins.get(attribute);
            double value = attribute.equals(attributes.get(i)) ? max : min;
            coordinates.put(attribute,value);
        }
        centroids.add(new centroid(coordinates));
    }
    return centroids;
    }


    /**
     * Individua il centroide più vicino.
     * @param record Lista degli utenti con le caratteristiche calcolate.
     * @see Record
     * @param centroids Lista di centroidi.
     * @see centroid
     * @return Centroide più vicino
     */
    private centroid nearestCentroid(Record record,List<centroid> centroids){
    double minimumDistance = Double.MAX_VALUE;
    centroid nearest = null;
    for(centroid centroid : centroids){
        double currentDistance = super.Distance(record.getFeatures(),centroid.getCoords());
        if(currentDistance< minimumDistance){
            minimumDistance = currentDistance;
            nearest = centroid;
        }
    }
    return nearest;
    }

    /**
     * Assegna un record (classe contenente informazioni sull'utente) al cluster.
     * @param clusters Mappa centroide-lista di Record che contiene le informazioni sui legami di ogni utente con un cluster specifico.
     * @param record Record dell'utente.
     * @param centroid Centroide.
     * @see Record
     */
    private static void assignToCluster(Map<centroid,List<Record>> clusters,
    Record record,
    centroid centroid){
        clusters.compute(centroid, (key,list) ->{
            if(list == null){
                list = new ArrayList<>();
                }
            list.add(record);
            return list;
        });
    }

    private static centroid Average(centroid centroid,List<Record> records){
        if(records == null || records.isEmpty())
            return centroid;
        Map<String,Double> average = centroid.getCoords();
        records.stream().flatMap(e -> e.getFeatures().keySet().stream())
                .forEach(k -> average.put(k,0.0));
        for(Record record :records){
            record.getFeatures().forEach(
                    (k,v) -> average.compute(k,(k1,currentValue) -> v + currentValue)
            );
            }
            average.forEach((k,v) -> average.put(k,v/records.size()));
            return new centroid(average);
    }

    private static List<centroid> relocateCentroids(Map<centroid,List<Record>> clusters){
        return clusters.entrySet().stream().map(e -> Average(e.getKey(),e.getValue())).collect(Collectors.toList());
    }

}