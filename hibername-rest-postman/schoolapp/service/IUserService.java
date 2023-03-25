package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dto.UserDTO;

import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface IUserService {
    User insertUser(UserDTO userDTO) throws EntityAlreadyExistsException, RuntimeException;
    User updateUser(UserDTO userDTO) throws EntityNotFoundException,   RuntimeException;
    void deleteUser(Long id) throws EntityNotFoundException;
    List<User> getUsersByUsername(String username) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
}
