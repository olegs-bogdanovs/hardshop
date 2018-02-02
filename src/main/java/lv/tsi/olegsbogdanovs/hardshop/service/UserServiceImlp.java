package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.UserDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Role;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.validation.EmailExistsException;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImlp implements UserService{

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

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
        user.setRole(Role.CUSTOMER);
        return userDao.save(user);
    }

    private boolean emailExist(String email){
        Optional<User> userOptional = userDao.findUserByEmail(email);
        return userOptional.isPresent();
    }
}
