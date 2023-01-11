package gr.aueb.cf.ch1.homework2.model;

/**
 *User POJO class. Java Bean.
 */
public final class User {
    private Long id;
    private String firstname;
    private String lastname;

    //default constructor
    public User() {
    }

    //overloaded constructor
    public User(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String convertToString() {
        return "id: " + id + ", " + "Firstname: " + firstname + ", " + "Lastname: " + lastname;
    }
}


