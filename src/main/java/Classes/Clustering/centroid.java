package Classes.Clustering;

import java.util.Map;

/**
 * Classe rappresentante un centroide.
 * @see Clustering
 */

public class centroid {

    /**
     * Coordinate del centroide.
     */
    private final Map<String,Double> coords;

    /**
     * Costruttore
     * @param coords Mappa stringa-numero contente le coordinate del centroide.
     */
    public centroid(Map<String, Double> coords) {
        this.coords = coords;
    }

    public Map<String, Double> getCoords() {
        return coords;
    }
}
