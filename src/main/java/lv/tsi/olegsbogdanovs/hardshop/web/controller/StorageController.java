package lv.tsi.olegsbogdanovs.hardshop.web.controller;

import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.Category;
import lv.tsi.olegsbogdanovs.hardshop.persistanse.domain.User;
import lv.tsi.olegsbogdanovs.hardshop.service.CategoryService;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.CategoryDto;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.ParameterDto;
import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.lang.reflect.Parameter;

@Controller
public class StorageController extends WebMvcConfigurerAdapter {
    private CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);


    public StorageController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/storage")
    public String getAdminView(Model model){
        return "storage/storage";
    }

    @GetMapping("/storage/category/create")
    public String categoryCreate(Model model){
        model.addAttribute("category", new CategoryDto());
        return "storage/category_create";
    }

    @PostMapping("/storage/category")
    public String categorySaveUpdate(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "storage/category_create";
        }
        CategoryDto savedCategoryDto = categoryService.saveCategoryDto(categoryDto);
        return "redirect:/storage/category/" + savedCategoryDto.getId() + "/show";
    }

    @GetMapping("/storage/category/{id}/show")
    public String categoryShow(Model model, @PathVariable Long id){
        model.addAttribute("category", categoryService.findDtoById(id));
        return "storage/category_show";
    }

    @GetMapping("/storage/category/list")
    public String categoryShow(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "storage/category_list";
    }

    @GetMapping("/storage/parameter/{categoryId}/create")
    public String createParameter(Model model, @PathVariable Long categoryId){
        CategoryDto categoryDto = categoryService.findDtoById(categoryId);
        ParameterDto parameterDto = new ParameterDto();
        parameterDto.setCategoryId(categoryDto.getId());
        model.addAttribute("parameter", parameterDto);
        return "storage/parameter_create";
    }

    @PostMapping("/storage/parameter")
    public String parameterSave(@ModelAttribute("parameter") ParameterDto parameterDto,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "storage/parameter/" + parameterDto.getCategoryId() + "/create";
        }
        CategoryDto categoryDto = categoryService.findDtoById(parameterDto.getCategoryId());
        categoryDto.getParameters().add(parameterDto);
        CategoryDto savedCategoryDto = categoryService.saveCategoryDto(categoryDto);
        return "redirect:/storage/category/" + savedCategoryDto.getId() + "/show";
    }
}
