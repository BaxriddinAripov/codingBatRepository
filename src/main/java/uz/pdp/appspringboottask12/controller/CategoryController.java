package uz.pdp.appspringboottask12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringboottask12.entity.Category;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.CategoryDto;
import uz.pdp.appspringboottask12.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Barcha Category larni qaytarish
     * @return categories
     */
    @GetMapping
    public ResponseEntity<List<Category>> allCategory(){
        List<Category> categories = categoryService.allCategory();
        return ResponseEntity.ok(categories);
    }

    /**
     * ID orqali Category ni qaytarish method
     * @param id Integer
     * @return category
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id){
        Category category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    /**
     * Category ni saqlash method
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Category ni tahrirlash
     * @param id Integer
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * ID orqali Category ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

}
