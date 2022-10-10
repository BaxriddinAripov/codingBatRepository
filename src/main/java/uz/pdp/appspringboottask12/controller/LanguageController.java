package uz.pdp.appspringboottask12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringboottask12.entity.Language;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.LanguageDto;
import uz.pdp.appspringboottask12.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    final
    LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    /**
     * Barcha language larni qaytarish method
     * @return languages
     */
    @GetMapping
    public ResponseEntity<List<Language>> allLanguage(){
        List<Language> languages = languageService.allLanguage();
        return ResponseEntity.ok(languages);
    }

    /**
     * ID orqali language ni qaytarish method
     * @param id Integer
     * @return language
     */
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable Integer id){
        Language language = languageService.getLanguage(id);
        return ResponseEntity.ok(language);
    }

    /**
     * Language ni yaratish method
     * @param languageDto Integer
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Language ni ID orqali o'zgartirish
     * @param id Integer
     * @param languageDto LanguageDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable Integer id, @Valid @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


}
