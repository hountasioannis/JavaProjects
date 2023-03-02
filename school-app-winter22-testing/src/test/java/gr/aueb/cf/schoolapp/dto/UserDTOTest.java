package gr.aueb.cf.schoolapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {
    @Test
    void gettersSetters() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        assertEquals(userDTO.getId(), 1);

        userDTO.setUsername("thanasis");
        assertEquals(userDTO.getUsername(), "thanasis");

        userDTO.setPassword("androtsos");
        assertEquals(userDTO.getPassword(), "androtsos");
    }

    @Test
    void overloadedConstructor() {
        UserDTO userDTO = new UserDTO(2, "anna", "giannoutsou");
        assertEquals(userDTO.getId(), 2);
        assertEquals(userDTO.getUsername(), "anna");
        assertEquals(userDTO.getPassword(), "giannoutsou");
    }

}