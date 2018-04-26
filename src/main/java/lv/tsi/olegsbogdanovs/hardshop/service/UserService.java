package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.validation.EmailExistsException;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;

import java.util.List;

public interface UserService {
    User registerNewUser(UserDto userDto) throws EmailExistsException;
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getNotAdminUsers();
    List<User> getAllUsers();
    void removeUser(Long id);
    void updateUser(User user);
}
