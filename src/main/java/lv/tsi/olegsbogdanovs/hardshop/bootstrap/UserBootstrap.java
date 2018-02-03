package lv.tsi.olegsbogdanovs.hardshop.bootstrap;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.UserDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Role;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private final UserDao userDao;
    private PasswordEncoder passwordEncoder;


    public UserBootstrap(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Set<User> userSet = new HashSet<>();

        User admin = new User();
        admin.setName("admin");
        admin.setSurname("admin");
        admin.setEmail("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        userDao.save(admin);

        User user = new User();
        user.setName("user");
        user.setSurname("user");
        user.setEmail("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRole(Role.CUSTOMER);
        userDao.save(user);

        User buh = new User();
        buh.setName("buh");
        buh.setSurname("buh");
        buh.setEmail("buh");
        buh.setPassword(passwordEncoder.encode("buh"));
        buh.setRole(Role.ACCOUNTANT);
        userDao.save(buh);

        User worker = new User();
        worker.setName("worker");
        worker.setSurname("worker");
        worker.setEmail("worker");
        worker.setPassword(passwordEncoder.encode("worker"));
        worker.setRole(Role.WORKER);
        userDao.save(worker);
    }
}
