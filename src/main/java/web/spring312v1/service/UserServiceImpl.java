package web.spring312v1.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.spring312v1.DAO.UserDao;
import web.spring312v1.model.Role;
import web.spring312v1.model.User;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPasswordReal()));
        userDao.createNewUser(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        user.setPasswordReal(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPasswordReal()));
        userDao.editUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        return userDao.getUserByLogin(login).get();
    }

}
