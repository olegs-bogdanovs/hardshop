package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Parameter;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ParameterDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParameterToParameterDto implements Converter<Parameter, ParameterDto> {
    @Override
    public ParameterDto convert(Parameter source) {
        ParameterDto parameterDto = new ParameterDto();
        parameterDto.setId(source.getId());
        parameterDto.setName(source.getName());
        parameterDto.setDesc(source.getDesc());
        return parameterDto;
    }
}
