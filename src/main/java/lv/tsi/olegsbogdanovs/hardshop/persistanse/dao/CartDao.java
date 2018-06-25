package lv.tsi.olegsbogdanovs.hardshop.persistanse.dao;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Cart;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.CartStatus;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartDao extends CrudRepository<Cart, Long>{
    Optional<Cart> findCartByUserAndAndStatus(User user, CartStatus cartStatus);
    Iterable<Cart> findCartsByUserAndStatus(User user, CartStatus cartStatus);
    Iterable<Cart> findCartsByUser(User user);
    Iterable<Cart> findCartsByStatus(CartStatus cartStatus);
}
