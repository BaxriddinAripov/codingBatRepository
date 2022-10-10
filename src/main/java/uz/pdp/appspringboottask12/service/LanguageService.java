package uz.pdp.appspringboottask12.service;

import org.springframework.stereotype.Service;
import uz.pdp.appspringboottask12.entity.Language;
import uz.pdp.appspringboottask12.payload.ApiResponse;
import uz.pdp.appspringboottask12.payload.LanguageDto;
import uz.pdp.appspringboottask12.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    final
    LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    /**
     * Barcha language larni qaytarish method
     * @return languages
     */
    public List<Language> allLanguage(){
        return languageRepository.findAll();
    }

    /**
     * ID orqali language ni qaytarish method
     * @param id Integer
     * @return language
     */
    public Language getLanguage(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    /**
     * Language ni yaratish method
     * @param languageDto Integer
     * @return ApiResponse
     */
    public ApiResponse addLanguage(LanguageDto languageDto){
        boolean exists = languageRepository.existsByName(languageDto.getName());
        if (exists)
            return new ApiResponse("Bunday name mavjud", false);
        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    /**
     * Language ni ID orqali o'zgartirish
     * @param id Integer
     * @param languageDto LanguageDto
     * @return ApiResponse
     */
    public ApiResponse editLanguage(Integer id, LanguageDto languageDto){
        boolean exists = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
        if (exists)
            return new ApiResponse("Bunday name va id mavjud", false);
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday language mavjud emas", false);
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    public ApiResponse deleteLanguage(Integer id){
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Malumot o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!!", false);
        }
    }
}
