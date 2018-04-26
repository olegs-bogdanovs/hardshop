package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ParameterDto;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class StorageController extends WebMvcConfigurerAdapter {

    public StorageController(){
    }

    @GetMapping("/storage")
    public String getAdminView(Model model){
        return "storage/storage";
    }

    @GetMapping("/storage/parameter/create")
    public String createParameter(Model model){
        model.addAttribute("parameter", new ParameterDto());
        return "storage/parameter_create";
    }

//    @PostMapping("/admin/user/create")
//    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult){
//        User registeredUser = new User();
//        if (!bindingResult.hasErrors()){
//            registeredUser = createUserAccount(userDto, bindingResult);
//        }
//        if (registeredUser == null){
//            bindingResult.rejectValue("email", "message.emailRegError");
//        }
//        if (bindingResult.hasErrors()) {
//            return "admin/user_add";
//        } else {
//            return "redirect:/admin";
//        }
//    }

}
