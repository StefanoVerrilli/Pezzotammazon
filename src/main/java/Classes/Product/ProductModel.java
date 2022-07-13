package Classes.Product;

import Classes.Product.ProductCategory.ProductCategoryModel;

/**
 * Modello che rappresenta un prodotto presente sul database.
 */

public class ProductModel {


    private int ID;
    private String name;
    private String desc;
    private int amount;
    private float cost;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCategory(ProductCategoryModel category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private ProductCategoryModel category;

    private String image;

    /**
     * Permette di creare un prodotto tramite {@link Builder}.
     * @param builder {@link Builder} con il quale costruire il {@link ProductModel}.
     */
    private ProductModel(Builder builder){
        this.name = builder.name;
        this.amount = builder.amount;
        this.cost = builder.cost;
        this.desc = builder.desc;
        this.ID = builder.id;
        this.image = builder.image;
        this.category = builder.category;
    }

    /**
     * Classe interna per la gestione della costruzione di {@link ProductModel}. Utilizza la versione
     * di Builder di Joshua Bloch.
     * @see <a href="https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java">Exploring Joshua Bloch’s Builder design pattern in Java</a>
     */
    public static class Builder{

        private final String name;
        private String desc ="";
        private Integer amount= 0;
        private Float cost = 0.0f;
        private String image;
        private Integer id = 0;
        private ProductCategoryModel category;
        public Builder(String name){
        this.name = name;
        }


        /**
         * Imposta la descrizione.
         * @param desc Descrizione del prodotto.
         * @return Ritorna ricorsivamente la classe {@link Builder}, per poter chiamare altri metodi successivamente setter.
         */
        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        /**
         * Imposta la quantità.
         * @param amount Quantità in stock del prodotto.
         * @return Ritorna ricorsivamente la classe {@link Builder}, per poter chiamare altri metodi successivamente setter.
         */
        public Builder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Imposta il prezzo.
         * @param cost Prezzo del prodotto.
         * @return Ritorna ricorsivamente la classe {@link Builder}, per poter chiamare altri metodi successivamente setter.
         */
        public Builder setCost(float cost) {
            this.cost = cost;
            return this;
        }

        /**
         * Imposta la categoria del prodotto.
         * @param Category {@link ProductCategoryModel} della categoria del prodotto.
         * @see ProductCategoryModel
         * @return Ritorna ricorsivamente la classe {@link Builder}, per poter chiamare altri metodi successivamente setter.
         */
        public Builder setCategory(ProductCategoryModel Category){
            this.category = Category;
            return this;
        }

        /**
         * Imposta l'immagine del prodotto.
         * @param image Stringa che rappresenta l'immagine in base64.
         * @return Ritorna ricorsivamente la classe {@link Builder}, per poter chiamare altri metodi successivamente setter.
         */
        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        /**
         * Imposta l'identificativo del prodotto.
         * @param id Identificativo univoco del prodotto.
         * @return Ritorna ricorsivamente la classe {@link Builder}, per poter chiamare altri metodi successivamente setter.
         */
        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        /**
         * Costruisce concretamente il prodotto.
         * @return Istanza {@link ProductModel} del modello creato utilizzando {@link Builder} come costruttore.
         */
        public ProductModel build(){
            return new ProductModel(this);
        }
    }

    /**
     * Permette di ottenere la categoria del prodotto.
     * @return Oggetto di tipo {@link ProductCategoryModel} contenente le informazioni sulla categoria.
     */
    public ProductCategoryModel getCategory() {
        return category;
    }


    public float getCost() {
        return cost;
    }


    public int getAmount() { return amount; }


    public String getDesc() {
        return desc;
    }


    public String getName() {
        return name;
    }


    public int getID() {
        return ID;
    }

    /**
     * Consente di ottenere l'immagine del prodotto.
     * @return Stringa che rappresenta la codificazione del prodotto in base64.
     */
    public String getImage() {
        return image;
    }


    /**
     * Verifica se il costo è un intero.
     * @param number Prezzo del prodotto (o genericamente un numero).
     * @return True se è un intero, False altrimenti.
     */
    public boolean decimalIsZero(float number) {return (number % 1) == 0;}
}
