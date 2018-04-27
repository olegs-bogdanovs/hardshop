package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ItemDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoToItem implements Converter<ItemDto, Item> {
    private final IPVDtoToIPV ipvconverter;

    public ItemDtoToItem(IPVDtoToIPV ipvconverter) {
        this.ipvconverter = ipvconverter;
    }

    @Override
    public Item convert(ItemDto source) {
        Item item = new Item();
        item.setId(source.getId());
        item.setName(source.getName());
        item.setQuantity(source.getQuantity());
        item.setDesc(source.getDesc());
        if (source.getValues() != null && source.getValues().size() > 0){
            source.getValues().forEach(value -> item.addItemParameterValue(ipvconverter.convert(value)));
        }
        return item;
    }
}
