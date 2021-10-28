package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Categories;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.CategoryDTO;
import com.example.appcodingbat.reposistory.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    final
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public ApiResponse edit(Integer id, CategoryDTO categoryDTO) {
        Optional<Categories> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Categories category = optionalCategory.get();
        if (categoryDTO.getDescription() != null) {
            category.setDescription(categoryDTO.getDescription());
        }
        if (categoryDTO.getName() != null) {
            category.setName(categoryDTO.getName());
        }
        categoryRepository.save(category);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse addCategory(CategoryDTO categoryDTO) {
        Categories category = new Categories();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        categoryRepository.save(category);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse delete(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }else{
            return new ApiResponse("Id not found", false);
        }
        return new ApiResponse("Deleted", true);
    }


}
