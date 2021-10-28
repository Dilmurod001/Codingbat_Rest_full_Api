package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Categories;
import com.example.appcodingbat.entity.Languages;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.LanguageDTO;
import com.example.appcodingbat.reposistory.CategoryRepository;
import com.example.appcodingbat.reposistory.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageService {
    final
    LanguageRepository languageRepository;
    final
    CategoryRepository categoryRepository;
    public LanguageService(LanguageRepository languageRepository, CategoryRepository categoryRepository) {
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse delete(Integer id) {
        if (languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
        }else{
            return new ApiResponse("Id not found", false);
        }
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse edit(Integer id, LanguageDTO languageDTO) {
        Optional<Languages> optionalLanguages = languageRepository.findById(id);
        if (!optionalLanguages.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Languages languages = optionalLanguages.get();
        if (languageDTO.getLanguageName() != null) {
            languages.setLanguageName(languageDTO.getLanguageName());
        }
        if (languageDTO.getCategoryId() != null) {
            Optional<Categories> optionalCategories = categoryRepository.findById(languageDTO.getCategoryId());
            if (!optionalCategories.isPresent()) {
                return new ApiResponse("Category id not found", false);
            }
            languages.setCategories(optionalCategories.get());
        }
        languageRepository.save(languages);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse add(LanguageDTO languageDTO) {
        Languages languages = new Languages();
        Optional<Categories> optionalCategories = categoryRepository.findById(languageDTO.getCategoryId());
        if (!optionalCategories.isPresent()) {
            return new ApiResponse("Category id not found", false);
        }
        languages.setCategories(optionalCategories.get());
        languages.setLanguageName(languageDTO.getLanguageName());
        languageRepository.save(languages);
        return new ApiResponse("Saved", true);
    }
}
