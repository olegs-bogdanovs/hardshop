package lv.tsi.olegsbogdanovs.hardshop.persistanse.dao;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, Long> {
}
