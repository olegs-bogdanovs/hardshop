package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.converters.ItemDtoToItem;
import lv.tsi.olegsbogdanovs.hardshop.converters.ItemToItemDto;
import lv.tsi.olegsbogdanovs.hardshop.exceptions.NotFoundException;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.ItemDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.ItemParameterValueDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Item;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.ItemParameterValue;
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
    private final CategoryService categoryService;
    private final ItemDao itemDao;
    private final ItemToItemDto itemToItemDto;
    private final ItemDtoToItem itemDtoToItem;
    private final ItemParameterValueDao itemParameterValueDao;
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(CategoryService categoryService, ItemDao itemDao, ItemToItemDto itemToItemDto, ItemDtoToItem itemDtoToItem, ItemParameterValueDao itemParameterValueDao) {
        this.categoryService = categoryService;
        this.itemDao = itemDao;
        this.itemToItemDto = itemToItemDto;
        this.itemDtoToItem = itemDtoToItem;
        this.itemParameterValueDao = itemParameterValueDao;
    }

    @Transactional
    public ItemDto saveItemDto(ItemDto itemDto){
        Item detachedItem = itemDtoToItem.convert(itemDto);
        if (itemDto.getCategoryId() != null){
            detachedItem.setCategory(categoryService.findById(itemDto.getCategoryId()));
        }
        Item savedItem = itemDao.save(detachedItem);

        if (savedItem.getItemParameterValues().size() == 0){
            savedItem.getCategory().getParameters().forEach(
                    parameter -> {
                        ItemParameterValue itemParameterValue = new ItemParameterValue();
                        itemParameterValue.setItem(savedItem);
                        itemParameterValue.setParameter(parameter);
                        savedItem.addItemParameterValue(itemParameterValue);
                    }
            );
        }
        return itemToItemDto.convert(savedItem);
    }

    @Transactional
    public ItemDto findDtoById(Long id){
        Item item = itemDao.findOne(id);
        if (item == null){
            throw new NotFoundException("Category Not Found. For ID value: " + id.toString());
        }

        if (item.getItemParameterValues().size() != item.getCategory().getParameters().size()){
            item.getCategory().getParameters().forEach(
                    parameter -> {
                        ItemParameterValue itemParameterValue = itemParameterValueDao.findByParameterAndItem(parameter, item);
                        if (itemParameterValue == null){
                            itemParameterValue = new ItemParameterValue();
                            itemParameterValue.setItem(item);
                            itemParameterValue.setParameter(parameter);
                            item.addItemParameterValue(itemParameterValueDao.save(itemParameterValue));
                        }
                    }
            );
        }
        return itemToItemDto.convert(item);
    }

    public Set<Item> getItems(){
        Set<Item> items = new HashSet<>();
        itemDao.findAll().forEach(items::add);
        return items;
    }

    public Set<Item> getItemsByCategoryId(Long id){
        Set<Item> items = new HashSet<>();
        itemDao.findItemsByCategoryId(id).forEach(items::add);
        return items;
    }

}
