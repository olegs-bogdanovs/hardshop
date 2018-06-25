package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Role;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.service.UserService;
import lv.tsi.olegsbogdanovs.hardshop.validation.EmailExistsException;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class AuthController extends WebMvcConfigurerAdapter {
    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @GetMapping("/")
    public String getIndex(){
        return "redirect:/shop/category/0";
    }

    @GetMapping("/user/registration")
    public String getUserRegistrationFormView(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult) {
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
