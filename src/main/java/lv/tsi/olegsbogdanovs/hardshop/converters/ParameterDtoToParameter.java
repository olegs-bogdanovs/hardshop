package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Parameter;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ParameterDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParameterDtoToParameter implements Converter<ParameterDto, Parameter> {
    @Override
    public Parameter convert(ParameterDto source) {
        Parameter parameter = new Parameter();
        parameter.setId(source.getId());
        parameter.setName(source.getName());
        parameter.setDesc(source.getDesc());
        return parameter;
    }
}
