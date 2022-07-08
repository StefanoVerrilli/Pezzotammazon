package Classes;

/**
 * Implementazione in Java del tipo Pair presente in C++
 * @param <L> Tipo chiave
 * @param <R> Tipo valore
 * @author Stefano Verrilli
 */

public class Pair<L,R> {
    private L Key;
    private R Value;

    /**
     * Costruisce il pair
     * @param Key Chiave
     * @param Value Valore
     */
    public Pair(L Key,R Value){

        /**
         * Verifica che la chiave passata non sia null
         */
        assert Key != null;

        /**
         * Verifica che il valore passato non sia null
         */
        assert Value != null;

        this.Key = Key;
        this.Value = Value;
    }

    /**
     * Permette di ottenere la chiave del Pair
     * @return Chiave del Pair
     */
    public L getKey(){return Key;}

    /**
     * Permette di ottenere il valore del Pair
     * @return Valore del Pair
     */
    public R getValue(){return Value;}

    /**
     * Imposta il valore del Pair
     * @param Value Valore del Pair
     */
    public void setValue(R Value){this.Value = Value;}

    /**
     * Imposta la chiave del Pair
     * @param key Chiave del Pair
     */
    public void setKey(L key){this.Key = key;}
}
