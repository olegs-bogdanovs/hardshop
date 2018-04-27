package lv.tsi.olegsbogdanovs.hardshop.persistanse.dao;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.ItemParameterValue;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Parameter;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ItemParameterValueDao extends CrudRepository<ItemParameterValue, Long>{
    ItemParameterValue findByParameterAndItem(Parameter parameter, Item item);
}
