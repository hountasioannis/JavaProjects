package gr.aueb.cf.ch5.app;

import gr.aueb.cf.ch5.model.User;
import gr.aueb.cf.ch5.model.UserCredentials;

/**
 * creates two instances of User and UserCredentials with overloaded constructors and print them
 */
public class Main {

    public static void main(String[] args) {
        User user1 = new User(5L, "John", "Chountas");
        UserCredentials user2 = new UserCredentials(7L, "aueb", "@");

        System.out.println("id: " + user1.getId() + ", " + "firstname: " + user1.getFirstname() + ", " + "lastname: " + user1.getLastname());
        System.out.println("id: " + user2.getId() + ", " + "username: " + user2.getUsername() + ", " + "password: " + user2.getPassword());
    }
}
