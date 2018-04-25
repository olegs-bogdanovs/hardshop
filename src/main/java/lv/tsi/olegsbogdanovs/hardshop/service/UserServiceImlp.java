package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.UserDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Role;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.validation.EmailExistsException;
import lv.tsi.olegsbogdanovs.hardshop.web.controller.AdminController;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImlp implements UserService{

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImlp.class);


    public UserServiceImlp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User registerNewUser(UserDto userDto) throws EmailExistsException {
        if (emailExist(userDto.getEmail()))
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());
        User user = new User();
        user.setName(userDto.getFirstName());
        user.setSurname(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        return userDao.save(user);
    }

    @Override
    public List<User> getNotAdminUsers() {
        List<User> users = new ArrayList<>();
        userDao.findAllByRoleNot(Role.ADMIN).iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long id) {
        logger.info(id.toString());
        User user = userDao.findOne(id);
        return user;
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userDao.delete(id);
    }

    private boolean emailExist(String email){
        Optional<User> userOptional = userDao.findUserByEmail(email);
        return userOptional.isPresent();
    }
}
