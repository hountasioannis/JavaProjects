package gr.aueb.cf.ch1.homework2.app;

import gr.aueb.cf.ch1.homework2.model.User;
import gr.aueb.cf.ch1.homework2.model.UserCredentials;

/**
 * Creates two instances of User and UserCredentials with overloaded constructors and print them.
 */
public class Main {

    public static void main(String[] args) {
        User user1 = new User(5L, "John", "Chountas");
        UserCredentials userCr = new UserCredentials(5L, "aueb", "@");

        System.out.println("id: " + user1.getId() + ", " + "firstname: " + user1.getFirstname() + ", " + "lastname: " + user1.getLastname());
        System.out.println("id: " + userCr.getId() + ", " + "username: " + userCr.getUsername() + ", " + "password: " + userCr.getPassword());
    }
}