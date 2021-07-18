package web.spring312v1.service;

import web.spring312v1.model.Role;
import web.spring312v1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createNewUser(User user);
    void editUser(User user);
    void deleteUserById(Long id);
    List<User> getAllUsers();
    Optional<User> getUserByLogin(String login);
    Optional<Role> getRoleByName(String name);
}
