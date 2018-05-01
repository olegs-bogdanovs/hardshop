package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class ShopController extends WebMvcConfigurerAdapter {

    @GetMapping("/shop")
    public String getShopIndex(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "shop/index";
    }

}
