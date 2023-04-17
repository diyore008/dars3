package uz.pdp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring.entity.Category;
import uz.pdp.spring.payload.ApiResponse;
import uz.pdp.spring.payload.CategoryDto;
import uz.pdp.spring.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PreAuthorize(value = "hasAuthority('READ_ALL')")
    @GetMapping("/getCategory")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> categoryList = categoryService.getCategory();
        return ResponseEntity.ok(categoryList);
    }


    @PreAuthorize(value = "hasAuthority('READ_ONE')")
    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
        Category categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryById);
    }

    @PreAuthorize(value = "hasAuthority('ADD')")
    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('UP')")
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('DELETE')")
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
