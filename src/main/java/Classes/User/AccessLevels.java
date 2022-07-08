package Classes.User;

/**
 * Offre un'interfaccia semplificata per identificare il codice numerico relativo all'utente, utile per mostrare pagine in maniera differente a seconda che l'utente sia un cliente o un amministratore
 @see Classes.ServletsRegulation.Context
 @see Classes.Command.Dispatcher
 */

public enum AccessLevels {
    /**
     * Cliente, può solo acquistare prodotti
     */
    User,
    /**
     * Amministratore, può modificare i prodotti e gestire vari aspetti del sito
     */
    Admin
}
