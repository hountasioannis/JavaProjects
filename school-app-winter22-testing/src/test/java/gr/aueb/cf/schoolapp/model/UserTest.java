package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void gettersSetters() {
        User user = new User();
        user.setId(1);
        assertEquals(user.getId(), 1);

        user.setUsername("thanasis");
        assertEquals(user.getUsername(),"thanasis");

        user.setPassword("androtsos");
        assertEquals(user.getPassword(),"androtsos");
    }

    @Test
    void overloadedConstructorAndToString() {
        User user = new User(2, "anna", "giannoutsou");
        assertEquals(user.getId(),2);
        assertEquals(user.getUsername(),"anna");
        assertEquals(user.getPassword(),"giannoutsou");
        String expected = "2, anna, giannoutsou";
        assertEquals(expected,user.toString());
    }

}