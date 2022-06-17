package Classes;

public class User {

    private String email;
    private String password;
    private String username;
    private int accessType;

    private int id;

    public User(User passedUser){
        this.accessType = passedUser.getAccessType();
        this.password = passedUser.getPassword();
        this.email = passedUser.getEmail();
        this.username = passedUser.getUsername();
        this.id = passedUser.getId();
    }

    public User() {

    }

    public int getAccessType() {
        return accessType;
    }

    public void setAccessType(int accessType) {
        this.accessType = accessType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
