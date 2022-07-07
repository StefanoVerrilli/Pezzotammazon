package Classes.User;

import Classes.Product.ProductModel;

public class UserModel {

    private String email;
    private String password;
    private String username;
    private AccessLevels AccessType;
    private Integer id;


    private UserModel(Builder builder){
        this.id = builder.id;
        this.AccessType = builder.access;
        this.email = builder.mail;
        this.password = builder.password;
        this.username = builder.username;
        }
    public static class Builder {
        private String mail;
        private String password;
        private String username;
        private AccessLevels access;
        private Integer id;

        public Builder(String Username){
            this.username = Username;
        }

        public Builder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setID(int ID){
            this.id = ID;
            return this;
        }

        public Builder setAccessType(int level) {
            access = AccessLevels.values()[level];
            return this;
        }

        public UserModel build(){
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
