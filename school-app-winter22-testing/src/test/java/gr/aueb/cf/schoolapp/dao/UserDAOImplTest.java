package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;

import gr.aueb.cf.schoolapp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private static IUserDAO userDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {

        userDAO = new UserDAOImpl();
        DBHelper.eraseData();
    }

    @BeforeEach
    void setUp() throws UserDAOException {
        createDummyUsers();
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBHelper.eraseData();
    }


    @Test
    void persistAndGetTeacher() throws UserDAOException {
        User user = new User();
        user.setUsername("bob");
        user.setPassword("Dylan");
        userDAO.insert(user);

        List<User> users = userDAO.getByUsername("bob");
        assertEquals(1, users.size());

    }

    @Test
    void update() throws UserDAOException {
        User user = new User();
        user.setId(2);
        user.setUsername("anna2");
        user.setPassword("giannoutsou2");
        userDAO.update(user);

        List<User> users = userDAO.getByUsername(user.getUsername());
        assertEquals(users.get(0).getUsername(), "anna2");

    }

    @Test
    void delete() throws UserDAOException {
        int id = 1;
        userDAO.delete(id);

        User user = userDAO.getById(1);

        assertNull(user);
    }

    @Test
    void getByUsername() throws UserDAOException {
        List<User> users = userDAO.getByUsername("than");
        assertEquals(1,users.size());

    }

    @Test
    void getById() throws UserDAOException {
        int id = 4 ;
        User user = userDAO.getById(id);
        assertEquals("john", user.getUsername());
    }

    public static void createDummyUsers() throws UserDAOException {
        User user = new User();
        user.setUsername("thanasis");
        user.setPassword("androutsos");
        userDAO.insert(user);

        user = new User();
        user.setUsername("anna");
        user.setPassword("giannoutsou");
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