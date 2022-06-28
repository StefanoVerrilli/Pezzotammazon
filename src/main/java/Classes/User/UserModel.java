package Classes.User;

public class UserModel {

    private String email;
    private String password;
    private String username;
    private Integer accessType;
    private Integer id;


    private UserModel(){}
    public static class Builder {
        private String mail;
        private String password;
        private String username;
        private Integer AccessType;
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

        public Builder setAccessType(Integer accessType) {
            AccessType = accessType;
            return this;
        }

        public UserModel build(){
            UserModel userModel = new UserModel();
            userModel.id = this.id;
            userModel.accessType = this.AccessType;
            userModel.email = this.mail;
            userModel.password = this.password;
            userModel.username = this.username;
            return userModel;
        }
    }

    public Integer getAccessType() {
        return accessType;
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
