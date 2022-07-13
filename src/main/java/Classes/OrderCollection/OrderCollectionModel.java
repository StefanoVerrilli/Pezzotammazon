package Classes.OrderCollection;


import java.sql.Date;

/**
 * Modello di un'entrata nello storico degli ordini.
 */

public class OrderCollectionModel {

    private int User_ID;

    private int CollectionID;

    /**
     * Istante in cui l'ordine è stato sottomesso.
     */
    private Date Timestamp;


    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public int getCollectionID() {
        return CollectionID;
    }

    public void setCollectionID(int collectionID) {
        CollectionID = collectionID;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date date) {
        this.Timestamp = date;
    }


}
