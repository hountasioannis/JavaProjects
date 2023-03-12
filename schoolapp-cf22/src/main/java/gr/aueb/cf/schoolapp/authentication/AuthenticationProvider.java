package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;

public class AuthenticationProvider {

    private static final IUserDAO userDAO = new UserDAOImpl();

    private AuthenticationProvider() {}

    public static User authenticate(UserDTO userDTO) throws UserDAOException {
        String password = System.getenv("TS_ADMIN_PASSWORD");

        if (!userDAO.isUserValid(userDTO.getUsername(), userDTO.getPassword())) {
            if (userDTO.getUsername().equals("admin@gmail.com") && (userDTO.getPassword().equals(password))){
                return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
            } else {
                return null;
            }
        } else {
            return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
        }

    }
}
