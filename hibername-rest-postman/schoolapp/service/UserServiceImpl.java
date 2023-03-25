package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.IUserDAO;

import gr.aueb.cf.schoolapp.dto.UserDTO;

import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import gr.aueb.cf.schoolapp.service.util.LoggerUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

import java.util.List;

@Provider
@Named("userServiceImpl")
public class UserServiceImpl implements IUserService {
    @Inject
    private IUserDAO userDAO;
    @Override
    public User insertUser(UserDTO userDTO) throws EntityAlreadyExistsException, RuntimeException {
        User user = null;

        try {
            JPAHelper.beginTransaction();
            user = map(userDTO);
            if (userDTO.getId() == null) {
                user = userDAO.insert(user);
            } else {
                throw new EntityAlreadyExistsException(User.class, user.getId());
            }
            JPAHelper.commitTransaction();
        } catch (EntityAlreadyExistsException | RuntimeException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("insert user " +" rollback " + " entity already exists");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return user;
    }

    @Override
    public User updateUser(UserDTO userDTO) throws EntityNotFoundException,RuntimeException {
        User userToUpdate = null;
        try {
            JPAHelper.beginTransaction();
            userToUpdate = map(userDTO);
            if (userDAO.getById(userToUpdate.getId()) == null) {
                throw new EntityNotFoundException(User.class, userToUpdate.getId());
            }
            userDAO.update(userToUpdate);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException | RuntimeException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("update user " +" rollback " + " entity not found");
            throw e;

        } finally {
            JPAHelper.closeEntityManager();
        }
        return userToUpdate;
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();
            if (userDAO.getById(id) == null) {
                throw new EntityNotFoundException(User.class, id);
            }

            userDAO.delete(id);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("delete user " +" rollback ");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<User> getUsersByUsername(String username) throws EntityNotFoundException {
        List<User> users;
        try {
            JPAHelper.beginTransaction();
            users = userDAO.getByUsername(username);
            if (users.size() == 0) {
                throw new EntityNotFoundException(List.class, 0L);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("get user " +" rollback " + " user not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return users;
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user = null;
        try {
            JPAHelper.beginTransaction();
            user = userDAO.getById(id);
            if (user == null) {
                throw new EntityNotFoundException(User.class, id);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("get user by id " +" rollback " + " user not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return user;
    }

    private User map(UserDTO dt) {
        User user = new User();
        user.setId(dt.getId());
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(dt.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setUsername(dt.getUsername());
        return user;
    }
}
