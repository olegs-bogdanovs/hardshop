package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDto, Category>{
    private ParameterDtoToParameter parameterConverter;
    private ItemDtoToItem itemConverter;


    public CategoryDtoToCategory(ParameterDtoToParameter parameterConverter, ItemDtoToItem itemConverter) {
        this.parameterConverter = parameterConverter;
        this.itemConverter = itemConverter;
    }

    @Override
    public synchronized Category convert(CategoryDto source) {
        Category category = new Category();
        category.setId(source.getId());
        category.setName(source.getName());
        category.setDesc(source.getDesc());

        if (source.getParameters() != null && source.getParameters().size() > 0){
            source.getParameters().forEach(parameterDto ->
                    category.addParameter(parameterConverter.convert(parameterDto)));
        }

        if (source.getItems() != null && source.getItems().size() >0){
            source.getItems().forEach(itemDto ->
                    category.addItem(itemConverter.convert(itemDto)));
        }
        return category;
    }
}
