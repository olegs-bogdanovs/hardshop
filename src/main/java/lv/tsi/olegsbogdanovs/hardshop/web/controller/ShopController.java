package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import lv.tsi.olegsbogdanovs.hardshop.exceptions.NotFoundException;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Cart;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.CartStatus;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.service.CartService;
import lv.tsi.olegsbogdanovs.hardshop.service.CategoryService;
import lv.tsi.olegsbogdanovs.hardshop.service.ItemService;
import lv.tsi.olegsbogdanovs.hardshop.service.UserService;
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
    final private UserService userService;
    final private CartService cartService;

    public ShopController(ItemService itemService, CategoryService categoryService, UserService userService, CartService cartService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/shop/category/{categoryId}")
    public String getItemListView(@PathVariable Long categoryId, Model model){
        model.addAttribute("categories", categoryService.getCategories());
        if (categoryId == 0){
            model.addAttribute("items", itemService.getItems());
        } else {
            model.addAttribute("items", itemService.getItemsByCategoryId(categoryId));
        }
        return "shop/index";
    }

    @GetMapping("/shop/item/{itemId}")
    public String getItemView(@PathVariable Long itemId, Model model){
        model.addAttribute("item", itemService.findDtoById(itemId));
        return "shop/item";
    }

    @PostMapping("/shop/cart/buy/{itemId}")
    public String addItemToCart(@PathVariable Long itemId){
        User user = userService.getUserByEmail(getAuthUser().getEmail());
        Item item = itemService.getItemById(itemId);
        if (user == null || item == null){
            throw new NotFoundException("User or Item Not Found");
        }
        cartService.addItemToCart(user, item);
        return "redirect:/shop/category/0";
    }

    @GetMapping("/shop/cart/show")
    public String getCurrentCart(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("cart", cartService.getCurrentCart(getAuthUser()));
        return "shop/show_cart";
    }

    @GetMapping("/shop/cart/history")
    public String getCartHistoryView(Model model){
        model.addAttribute("carts", cartService.getUserPaidCarts(getAuthUser()));
        return "shop/show_history";
    }

    @PostMapping("/shop/cart/pay")
    public String payForCart(){
        cartService.payForCurrentCart(getAuthUser());
        return "redirect:/shop/category/0";
    }

    private User getAuthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userService.getUserByEmail(userName);
    }

}
