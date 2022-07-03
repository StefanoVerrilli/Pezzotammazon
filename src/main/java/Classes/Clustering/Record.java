package Classes.Clustering;


import Classes.User.UserModel;

import java.util.Map;

public class Record {
    private final UserModel target;
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