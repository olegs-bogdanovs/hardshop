package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.ParameterDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Parameter;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ParameterDto;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {
    private ParameterDao parameterDao;

    public ParameterService(ParameterDao parameterDao) {
        this.parameterDao = parameterDao;
    }

    public void createUpdateParameterFromDto(ParameterDto parameterDto){

    }


}
