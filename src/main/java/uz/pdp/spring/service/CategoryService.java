package uz.pdp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.spring.entity.Category;
import uz.pdp.spring.payload.ApiResponse;
import uz.pdp.spring.payload.CategoryDto;
import uz.pdp.spring.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getCategoryById(Integer id){
        Optional<Category> byIdCategory = categoryRepository.findById(id);
        return byIdCategory.get();
    }

    public ApiResponse addCategory(CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Parent category not found", false);

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setCategory(optionalCategory.get());
        categoryRepository.save(category);
        return new ApiResponse("Saved category", true);
    }

    public ApiResponse updateCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found", false);
        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optionalParentCategory.isPresent())
            return new ApiResponse("Parent category not found", false);

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setCategory(optionalParentCategory.get());
        categoryRepository.save(category);
        return new ApiResponse("Updated category", true);
    }

    public ApiResponse deleteCategory(Integer id){
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Deleted category", true);
        }catch (Error e){
            return new ApiResponse("Error", false);
        }
    }
}
