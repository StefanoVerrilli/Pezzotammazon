package Classes.ServletsRegulation;

/**
 * Contiene lo stato attuale dell'utente.
 */

public class Context {

    /**
     * Tipologia di stato.
     */
    private State state;

    /**
     * Crea l'oggetto con uno stato iniziale.
     * @param state Stato iniziale in cui si trova l'oggetto.
     */
    public Context(State state){
        this.state = state;
    }

    /**
     * Modifica lo stato dell'oggetto.
     * @param state
     */
    public void changeState(State state){
        this.state = state;
    }

    /**
     * Richiama la funzione per lo stato contenuto nella classe.
     * @see State
     */
    public void LoadLinks(){
        this.state.LoadLink();
    }
}
