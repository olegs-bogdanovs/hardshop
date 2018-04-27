package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.ItemParameterValue;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ItemParameterValueDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IPVDtoToIPV implements Converter<ItemParameterValueDto, ItemParameterValue> {
    private final ParameterDtoToParameter parameterConverter;

    public IPVDtoToIPV(ParameterDtoToParameter parameterConverter) {
        this.parameterConverter = parameterConverter;
    }

    @Override
    public ItemParameterValue convert(ItemParameterValueDto source) {
        ItemParameterValue ipv = new ItemParameterValue();
        ipv.setId(source.getId());
        ipv.setValue(source.getValue());
        ipv.setParameter(parameterConverter.convert(source.getParameter()));
        return ipv;
    }
}
