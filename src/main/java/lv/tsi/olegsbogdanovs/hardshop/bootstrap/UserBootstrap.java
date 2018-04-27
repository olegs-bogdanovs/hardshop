package lv.tsi.olegsbogdanovs.hardshop.bootstrap;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.CategoryDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.ParameterDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.UserDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Parameter;
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
    private final ParameterDao parameterDao;
    private final CategoryDao categoryDao;
    private PasswordEncoder passwordEncoder;


    public UserBootstrap(UserDao userDao, PasswordEncoder passwordEncoder, ParameterDao parameterDao,
                         CategoryDao categoryDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.parameterDao = parameterDao;
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        // Add Users

        Set<User> userSet = new HashSet<>();

        User admin = new User();
        admin.setName("admin");
        admin.setSurname("admin");
        admin.setEmail("admin@admin.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        userDao.save(admin);

        User user = new User();
        user.setName("user");
        user.setSurname("user");
        user.setEmail("user@user.com");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRole(Role.CUSTOMER);
        userDao.save(user);

        User buh = new User();
        buh.setName("buh");
        buh.setSurname("buh");
        buh.setEmail("buh@buh.com");
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


        /// CATEGORY CPU
        Category cpu_cat = new Category();
        cpu_cat.setName("CPU");
        cpu_cat.setDesc("Central Processing Unit");

        Parameter cpu_cores = new Parameter();
        cpu_cores.setName("Cores");
        cpu_cores.setDesc("Number of Cores");
        cpu_cat.addParameter(cpu_cores);

        Parameter cpu_arch = new Parameter();
        cpu_arch.setName("Archetecture");
        cpu_arch.setDesc("Archetecrure of CPU");
        cpu_cat.addParameter(cpu_arch);

        categoryDao.save(cpu_cat);

        /// CATEGORY RAM
        Category ram_cat = new Category();
        ram_cat.setName("RAM");
        ram_cat.setDesc("Random-Access Memory");

        Parameter ram_type = new Parameter();
        ram_type.setName("Type");
        ram_type.setDesc("RAM Type");
        ram_cat.addParameter(ram_type);

        Parameter ram_size = new Parameter();
        ram_size.setName("Size");
        ram_size.setDesc("RAM Size");
        ram_cat.addParameter(ram_size);

        categoryDao.save(ram_cat);


    }
}
