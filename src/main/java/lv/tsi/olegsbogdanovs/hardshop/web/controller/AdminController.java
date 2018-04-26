package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Role;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.service.UserService;
import lv.tsi.olegsbogdanovs.hardshop.validation.EmailExistsException;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class AdminController extends WebMvcConfigurerAdapter {
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    public AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAdminView(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin/user_list";
    }

    @GetMapping("/admin/user/create")
    public String createAnyUserView(Model model){
        model.addAttribute("user", new UserDto());
        return "admin/user_add";
    }

    @GetMapping("/admin/user/{userId}/modify")
    public String modifyAnyUserView(Model model, @PathVariable String userId){
        User user = userService.getUserById(Long.parseLong(userId));
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail(user.getEmail());
        userUpdateDto.setFirstName(user.getName());
        userUpdateDto.setLastName(user.getSurname());
        userUpdateDto.setRole(user.getRole());
        userUpdateDto.setId(user.getId());
        model.addAttribute("user", userUpdateDto);
        return "admin/user_update";
    }

    @PostMapping("/admin/user/{userId}/modify")
    public String modifyUserAccount(@ModelAttribute("user") @Valid UserUpdateDto userUpdateDto,
                                   @PathVariable String userId,
                                   BindingResult bindingResult){
        logger.info (userUpdateDto == null? "NULL": userUpdateDto.toString());
        User user = userService.getUserById(Long.parseLong(userId));
        if (!bindingResult.hasErrors() && user != null){
            user.setName(userUpdateDto.getFirstName());
            user.setSurname(userUpdateDto.getLastName());
            user.setRole(userUpdateDto.getRole());
            userService.updateUser(user);
        }
        if (bindingResult.hasErrors()) {
            return "admin/user_update";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/user/create")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult){
        User registeredUser = new User();
        if (!bindingResult.hasErrors()){
            registeredUser = createUserAccount(userDto, bindingResult);
        }
        if (registeredUser == null){
            bindingResult.rejectValue("email", "message.emailRegError");
        }
        if (bindingResult.hasErrors()) {
            return "admin/user_add";
        } else {
            return "redirect:/admin";
        }
    }


    @PostMapping("/admin/user/{userId}/delete")
    public String deleteUser(@PathVariable String userId, WebRequest request){
        userService.removeUser(Long.valueOf(userId));
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/user/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerCustomerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult) {
        User registeredUser = new User();
        userDto.setRole(Role.CUSTOMER);
        if (!bindingResult.hasErrors()){
            registeredUser = createUserAccount(userDto, bindingResult);
        }
        if (registeredUser == null){
            bindingResult.rejectValue("email", "message.emailRegError");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            return "redirect:/";
        }
    }

    private User createUserAccount(UserDto userDto, BindingResult bindingResult){
        User registered = null;
        try {
            registered = userService.registerNewUser(userDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
