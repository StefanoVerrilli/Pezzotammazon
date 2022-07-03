package Classes.Clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class KMeans extends Clustering{

    public KMeans(DistanceMetric metric) {
        super(metric);
    }

    private static final Random random = new Random();

    public Map<centroid, List<Record>> fit(List<Record> records,
    int k,
    int maxIterations){
        List<centroid> centroids = randomCentroids(records,k);
        Map<centroid,List<Record>> clusters = new HashMap<>();
        Map<centroid,List<Record>> lastState = new HashMap<>();

        for(int i=0;i<maxIterations;i++){
            boolean isLastIteration = i == maxIterations -1;
            for(Record record : records){
                centroid centroid = nearestCentroid(record,centroids,super.distanceMethod);
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
    Set<String > attributes = records.stream()
                            .flatMap(e -> e.getFeatures().keySet().stream())
                            .collect(Collectors.toSet());
    for(int i=0;i<k;i++){
        Map<String,Double> coordinates = new HashMap<>();
        for(String attribute : attributes){
            double max = maxs.get(attribute);
            double min = mins.get(attribute);
            coordinates.put(attribute,random.nextDouble()*(max - min ) + min);
        }
        centroids.add(new centroid(coordinates));
    }
    return centroids;
    }


    private static centroid nearestCentroid(Record record,List<centroid> centroids,DistanceMetric distance){
    double minimumDistance = Double.MAX_VALUE;
    centroid nearest = null;
    for(centroid centroid : centroids){
        double currentDistance = distance.calculate(record.getFeatures(),centroid.getCoords());
        if(currentDistance< minimumDistance){
            minimumDistance = currentDistance;
            nearest = centroid;
        }
    }
    return nearest;
    }

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