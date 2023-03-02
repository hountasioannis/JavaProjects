package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;

import gr.aueb.cf.schoolapp.dto.UserDTO;

import gr.aueb.cf.schoolapp.model.User;

import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static IUserDAO userDAO = new UserDAOImpl();
    private static IUserService userService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        userService = new UserServiceImpl(userDAO);
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws UserDAOException {
        createDummyUsers();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetUser() throws UserDAOException {
        UserDTO userDTO = new UserDTO(1, "anna", "gianoutsou");
        userService.insertUser(userDTO);

        List<User> users = userService.getUsersByUsername(userDTO.getUsername());
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getPassword(), userDTO.getPassword());
        assertEquals(users.get(0).getUsername(), userDTO.getUsername());
    }

    @Test
    public void updateUser() throws UserNotFoundException, UserDAOException {
        UserDTO userDTO = new UserDTO(1, "thanasis2", "andoutsos2");
        userService.updateUser(userDTO);

        List<User> users = userService.getUsersByUsername(userDTO.getUsername());

        assertEquals(users.get(0).getPassword(),userDTO.getPassword());
        assertEquals(users.get(0).getUsername(),userDTO.getUsername());
    }

    @Test
    public void updateInvalidUser() throws UserNotFoundException, UserDAOException {
        UserDTO userDTO = new UserDTO(30, "thanos", "andr");


        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(userDTO);
        });
    }

    @Test
    public void deleteUser() throws UserNotFoundException, UserDAOException {
        UserDTO userDTO = new UserDTO(1, "thanasis" , "androutsos");
        userService.deleteUser(userDTO.getId());

        List<User> users = userService.getUsersByUsername(userDTO.getUsername());
        assertEquals(0, users.size());
    }

    @Test
    public void deleteInvalidUser() throws UserNotFoundException, UserDAOException {
        UserDTO userDTO = new UserDTO(40, "thanos", "andr");


        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(userDTO.getId());
        });
    }

    public static void createDummyUsers() throws UserDAOException {
        User user = new User();
        user.setUsername("thanasis");
        user.setPassword("androutsos");
        userDAO.insert(user);


        user = new User();
        user.setUsername("alice");
        user.setPassword("c");
        userDAO.insert(user);

        user = new User();
        user.setUsername("john");
        user.setPassword("lenon");
        userDAO.insert(user);

    }

}