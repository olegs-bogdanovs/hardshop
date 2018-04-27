package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {
    private ParameterToParameterDto parameterConverter;
    private ItemToItemDto itemConverter;


    public CategoryToCategoryDto(ParameterToParameterDto parameterConverter, ItemToItemDto itemConverter) {
        this.parameterConverter = parameterConverter;
        this.itemConverter = itemConverter;
    }

    @Override
    public synchronized CategoryDto convert(Category source) {
        CategoryDto category = new CategoryDto();
        category.setId(source.getId());
        category.setName(source.getName());
        category.setDesc(source.getDesc());

        if(source.getParameters() != null && source.getParameters().size() > 0){
            source.getParameters().forEach(
                    parameter -> category.getParameters().add(parameterConverter.convert(parameter))
            );
        }

        if(source.getItems() != null && source.getItems().size() > 0){
            source.getItems().forEach(
                    item -> category.getItems().add(itemConverter.convert(item))
            );
        }

        return category;
    }
}
