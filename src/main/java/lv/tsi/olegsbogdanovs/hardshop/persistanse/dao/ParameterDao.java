package lv.tsi.olegsbogdanovs.hardshop.persistanse.dao;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Parameter;
import org.springframework.data.repository.CrudRepository;

public interface ParameterDao extends CrudRepository<Parameter, Long> {
}
