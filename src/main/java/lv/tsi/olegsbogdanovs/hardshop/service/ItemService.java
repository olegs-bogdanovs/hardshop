package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.converters.ItemDtoToItem;
import lv.tsi.olegsbogdanovs.hardshop.converters.ItemToItemDto;
import lv.tsi.olegsbogdanovs.hardshop.exceptions.NotFoundException;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.ItemDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.CategoryDto;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class ItemService {
    private final ItemDao itemDao;
    private final ItemToItemDto itemToItemDto;
    private final ItemDtoToItem itemDtoToItem;
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemDao itemDao, ItemToItemDto itemToItemDto, ItemDtoToItem itemDtoToItem) {
        this.itemDao = itemDao;
        this.itemToItemDto = itemToItemDto;
        this.itemDtoToItem = itemDtoToItem;
    }

    @Transactional
    public ItemDto saveItemDto(ItemDto itemDto){
        Item detachedItem = itemDtoToItem.convert(itemDto);
        Item savedItem = itemDao.save(detachedItem);
        return itemToItemDto.convert(savedItem);
    }

    public ItemDto findDtoById(Long id){
        Item category = itemDao.findOne(id);
        if (category == null){
            throw new NotFoundException("Category Not Found. For ID value: " + id.toString());
        }
        return itemToItemDto.convert(category);
    }

    public Set<Item> getItems(){
        Set<Item> items = new HashSet<>();
        itemDao.findAll().forEach(items::add);
        return items;
    }

}
