package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.CartDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Cart;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.CartStatus;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private CartDao cartDao;
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);


    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Transactional
    public Cart addItemToCart(User user, Item item){
        Cart cart = getCurrentCart(user);
        cart.getItems().add(item);
        return cartDao.save(cart);
    }

    @Transactional
    public Cart payForCurrentCart(User user){
        Optional<Cart> cart = cartDao.findCartByUserAndAndStatus(user, CartStatus.CURRENT);
        cart.get().setStatus(CartStatus.PAID);
        return cartDao.save(cart.get());
    }

    public List<Cart> getCartsByStatus(User user, CartStatus cartStatus){
        List<Cart> carts = new ArrayList<>();
        cartDao.findCartsByUserAndStatus(user, cartStatus).forEach(carts::add);
        return carts;
    }

    public List<Cart> getUserPaidCarts(User user){
        List<Cart> carts = new ArrayList<>();
        return getCartsByStatus(user, CartStatus.PAID);
    }

    public List<Cart> getAllPaidCarts(){
        List<Cart> cart = new ArrayList<>();
        cartDao.findCartsByStatus(CartStatus.PAID).forEach(cart::add);
        return cart;
    }

    @Transactional
    public Cart getCurrentCart(User user){
        Optional<Cart> cart = cartDao.findCartByUserAndAndStatus(user, CartStatus.CURRENT);
        if(cart.isPresent()){
            return cart.get();
        } else {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setStatus(CartStatus.CURRENT);
            return cartDao.save(newCart);
        }
    }
}
