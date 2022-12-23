package gr.aueb.cf.ch5.model;

/**
 * UserCredentials POJO class. Java Bean.
 */
public final class UserCredentials {
    private Long id;
    private String username;
    private String password;

    //default constructor
    public UserCredentials() {
    }

    //overloaded constructor
    public UserCredentials(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
