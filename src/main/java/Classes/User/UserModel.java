package Classes.User;

/**
 * Definisce un modello che rappresenta l'utente a cui si sta facendo riferimento
 */

public class UserModel {

    /**
     * Email dell'utente
     */
    private String email;

    /**
     * Password dell'utente
     */
    private String password;

    /**
     * Nome dell'utente
     */
    private String username;

    /**
     * Tipologia, se admin o utente semplice (cliente)
     * @see AccessLevels
     */
    private AccessLevels AccessType;

    /**
     * Identificativo univoco dell'utente
     */
    private Integer id;


    /**
     * Builder che permette la creazione semplificata del modello dell'utente
     * @param builder Istanza della classe Builder, con la quale costruire l'oggetto UserModel
     * @see Builder
     */
    private UserModel(Builder builder){
        this.id = builder.id;
        this.AccessType = builder.access;
        this.email = builder.mail;
        this.password = builder.password;
        this.username = builder.username;
        }

    /**
     * Classe che permette la costruzione del modello
     */
    public static class Builder {
        private String mail;
        private String password;
        private String username;
        private AccessLevels access;
        private Integer id;

        /**
         * Imposta il nome utente, classe principale necessaria a creare l'oggetto Builder
         * @param Username Nome dell'utente
         */
        public Builder(String Username){
            this.username = Username;
        }

        /**
         * Imposta l'email dell'utente
         * @param mail Email dell'utente
         * @return Oggetto Builder che si sta costruendo
         */
        public Builder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        /**
         * Imposta la password dell'utente
          * @param password Password dell'utente
         * @return Oggetto Builder che si sta costruendo
         */
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Imposta l'identificativo univoco dell'utente
          * @param ID Identificativo univoco dell'utente
         * @return Oggetto Builder che si sta costruendo
         */
        public Builder setID(int ID){
            this.id = ID;
            return this;
        }

        /**
         * Imposta il tipo di accesso dell'utente
         * @param level Tipo di accesso dell'untente
         * @return Oggetto Builder che si sta costruendo
         * @see AccessLevels
         */
        public Builder setAccessType(int level) {
            access = AccessLevels.values()[level];
            return this;
        }

        /**
         * Costruisce l'utente
         * @return Oggetto di classe UserModel costruito
         */
        public UserModel build(){
            /**
             * Costruisce l'oggetto di classe UserModel a partire dal Builder
             */
            return new UserModel(this);
        }
    }

    public AccessLevels getAccessType() {
        return AccessType;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }


    public int getId() {
        return id;
    }

}
