package lv.tsi.olegsbogdanovs.hardshop.persistanse.dao;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ItemDao extends CrudRepository<Item, Long> {
    Set<Item> findItemsByCategoryId(Long id);
//    Set<Item> findItemsByCategory(Category category);
}
