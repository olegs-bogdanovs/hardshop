package lv.tsi.olegsbogdanovs.hardshop.converters;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {
    private ParameterToParameterDto parameterConverter;


    public CategoryToCategoryDto(ParameterToParameterDto parameterConverter) {
        this.parameterConverter = parameterConverter;
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

        return category;
    }
}
