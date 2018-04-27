package lv.tsi.olegsbogdanovs.hardshop.service;

import lv.tsi.olegsbogdanovs.hardshop.converters.CategoryDtoToCategory;
import lv.tsi.olegsbogdanovs.hardshop.converters.CategoryToCategoryDto;
import lv.tsi.olegsbogdanovs.hardshop.exceptions.NotFoundException;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.dao.CategoryDao;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryToCategoryDto categoryToCategoryDto;
    private final CategoryDtoToCategory categoryDtoToCategory;
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);


    public CategoryService(CategoryDao categoryDao, CategoryToCategoryDto categoryToCategoryDto,
                           CategoryDtoToCategory categoryDtoToCategory) {
        this.categoryDao = categoryDao;
        this.categoryToCategoryDto = categoryToCategoryDto;
        this.categoryDtoToCategory = categoryDtoToCategory;
    }

    @Transactional
    public CategoryDto saveCategoryDto(CategoryDto categoryDto){
        Category detachedCategory = categoryDtoToCategory.convert(categoryDto);
        logger.info("Category {}", detachedCategory.toString());
        Category savedCategory = categoryDao.save(detachedCategory);
        return categoryToCategoryDto.convert(savedCategory);
    }

    public CategoryDto findDtoById(Long id){
        Category category = categoryDao.findOne(id);
        if (category == null){
            throw new NotFoundException("Category Not Found. For ID value: " + id.toString());
        }
        return categoryToCategoryDto.convert(category);
    }

    public Set<Category> getCategories(){
        Set<Category> categories = new HashSet<>();
        categoryDao.findAll().forEach(categories::add);
        return categories;
    }
}
