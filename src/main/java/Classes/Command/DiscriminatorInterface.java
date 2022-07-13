package Classes.Command;

import Classes.User.AccessLevels;

/**
 * Interfaccia per il discriminatore cliente/amministratore.
 * @see AccessLevels
 */

public interface DiscriminatorInterface {
    String UserPages();
    String AdminPages();
}
