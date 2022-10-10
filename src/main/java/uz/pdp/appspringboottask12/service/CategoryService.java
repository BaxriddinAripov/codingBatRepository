package uz.pdp.appspringboottask12.service;

import org.springframework.stereotype.Service;
import uz.pdp.appspringboottask12.entity.Category;
import uz.pdp.appspringboottask12.entity.Language;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.CategoryDto;
import uz.pdp.appspringboottask12.repository.CategoryRepository;
import uz.pdp.appspringboottask12.repository.LanguageRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    final
    CategoryRepository categoryRepository;
    final
    LanguageRepository languageRepository;
    public CategoryService(CategoryRepository categoryRepository, LanguageRepository languageRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
    }

    /**
     * Barcha Category larni qaytarish
     * @return categories
     */
    public List<Category> allCategory(){
        return categoryRepository.findAll();
    }

    /**
     * ID orqali Category ni qaytarish method
     * @param id Integer
     * @return category
     */
    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    /**
     * Categore ni saqlash method
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    public ApiResponse addCategory(CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists)
            return new ApiResponse("Bunday name mavjud", false);
        List<Language> languages = languageRepository.findAllByIdIn(categoryDto.getLanguageId());
        Set<Language> languageSet = new HashSet<>(languages);
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDiscreption(categoryDto.getDiscreption());
        category.setLanguageId(languageSet);
        categoryRepository.save(category);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    /**
     * ID orqali Category ni tahrirlash
     * @param id Integer
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    public ApiResponse editCategory(Integer id, CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday name va id mavjud", false);
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday category mavjud emas", false);
        List<Language> languages = languageRepository.findAllByIdIn(categoryDto.getLanguageId());
        Set<Language> languageSet = new HashSet<>(languages);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDiscreption(categoryDto.getDiscreption());
        category.setLanguageId(languageSet);
        categoryRepository.save(category);
        return new ApiResponse("Ma'lumot tahrirlandi", true);
    }

    /**
     * ID orqali Category ni o'chirish method
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteCategory( Integer id){
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }
}
