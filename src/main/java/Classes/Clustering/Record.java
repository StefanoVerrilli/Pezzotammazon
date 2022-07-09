package Classes.Clustering;


import Classes.User.UserModel;

import java.util.Map;

/**
 * Classe che racchiude le informazioni di ciascun utente e le lega alle caratteristiche calcolate tramite clustering.
 */

public class Record {

    /**
     * Modello contenente i dati dell'utente.
     * @see UserModel
     */
    private final UserModel target;

    /**
     * Informazioni sull'utente, utilizzato per il clustering.
     */
    private final Map<String,Integer> features;


    public Record(UserModel target,Map<String,Integer> features){
    this.target = target;
    this.features = features;}

    public UserModel getTarget() {
        return target;
    }

    public Map<String, Integer> getFeatures() {
        return features;
    }
}