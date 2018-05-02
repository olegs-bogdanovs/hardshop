package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import lv.tsi.olegsbogdanovs.hardshop.service.CategoryService;
import lv.tsi.olegsbogdanovs.hardshop.service.ItemService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class ShopController extends WebMvcConfigurerAdapter {
    final private ItemService itemService;
    final private CategoryService categoryService;

    public ShopController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/shop/category/{categoryId}")
    public String getShopIndex(@PathVariable Long categoryId, Model model){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("categories", categoryService.getCategories());
        if (categoryId == 0){
            model.addAttribute("items", itemService.getItems());
        } else {
            model.addAttribute("items", itemService.getItemsByCategoryId(categoryId));
        }
        return "shop/index";
    }

    @GetMapping("/shop/item/{itemId}")
    public String getItem(@PathVariable Long itemId, Model model){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("item", itemService.findDtoById(itemId));
        return "shop/item";
    }

    @PostMapping("/shop/cart/buy/{itemId}")
    public String buyItem(@PathVariable Long itemId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Need to create Cart Service
        return "";
    }

}
