package gr.aueb.cf.schoolapp.dao;


import gr.aueb.cf.schoolapp.model.User;


import java.sql.SQLException;

import java.util.List;

public interface IUserDAO {
    User insert(User user) throws RuntimeException;
    User update(User user) throws RuntimeException;
    void delete(Long id);
    List<User> getByUsername(String username);
    User getById(Long id);
}
