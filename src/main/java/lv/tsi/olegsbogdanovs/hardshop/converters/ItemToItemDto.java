package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ItemDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemDto implements Converter<Item, ItemDto> {
    private final IPVToIPVDto ipvconverter;

    public ItemToItemDto(IPVToIPVDto ipvconverter) {
        this.ipvconverter = ipvconverter;
    }

    @Override
    public ItemDto convert(Item source) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(source.getId());
        itemDto.setName(source.getName());
        itemDto.setQuantity(source.getQuantity());
        itemDto.setDesc(source.getDesc());
        itemDto.setPrice(source.getPrice());
        if (source.getCategory() != null) {
            itemDto.setCategoryId(source.getCategory().getId());
        }

        if (source.getItemParameterValues() != null && source.getItemParameterValues().size() > 0){
            source.getItemParameterValues().forEach(
                    value -> itemDto.getValues().add(ipvconverter.convert(value))
            );
        }
        return itemDto;
    }
}
