package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.ItemParameterValue;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ItemParameterValueDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IPVToIPVDto implements Converter<ItemParameterValue, ItemParameterValueDto>{
    private final ParameterToParameterDto parameterConverter;

    public IPVToIPVDto(ParameterToParameterDto parameterConverter) {
        this.parameterConverter = parameterConverter;
    }

    @Override
    public ItemParameterValueDto convert(ItemParameterValue source) {
        ItemParameterValueDto dto = new ItemParameterValueDto();
        dto.setItemId(source.getItem().getId());
        dto.setId(source.getId());
        dto.setValue(source.getValue());
        dto.setParameter(parameterConverter.convert(source.getParameter()));
        return dto;
    }
}
