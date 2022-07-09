package Classes.Clustering;

import java.util.List;
import java.util.Map;

/**
 * Classe che si occupa dell'incapsulamento dell'operazione di clustering. Implementa il pattern Bridge con {@link DistanceMetric}
 */

public abstract class Clustering {

    /**
     * Variabile contenente il tipo di metrica utilizzata per calcolare le distanze.
     */
    protected DistanceMetric distanceMethod;

    /**
     * Costruttore.
     * @param metric Tipo di metrica utilizzata nel clustering per il calcolo delle distanze.
     */
    public Clustering(DistanceMetric metric){
        this.distanceMethod = metric;
    }

    /**
     * Calcola la distanza utilizzando il tipo di metrica impostato tramite il costruttore della classe.
     * @param f1 Mappa stringa-intero contenente il numero del quale si vuole calcolare la distanza.
     * @param centroid Mappa stringa-numero contenente le coordinate del centroide.
     * @return
     */
    public double Distance(Map<String, Integer> f1, Map<String, Double> centroid){
        return  this.distanceMethod.calculate(f1,centroid);
    }

    /**
     * Metodo astratto (da implementare) che permette di partizionare una lista di utenti, sulla base dei loro gusti di acquisto,
     * in macro-categorie.
     * @see Record
     * @param records Lista di utenti con le caratteristiche calcolate.
     * @param k Numero massimo per la generazione casuale dei centroidi.
     * @param maxIterations Numero massimo di iterazioni da eseguire.
     * @return
     */
    public abstract Map<centroid, List<Record>> fit(List<Record> records,
            int k,
            int maxIterations);
}
