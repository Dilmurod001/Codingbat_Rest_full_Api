package com.example.appcodingbat.reposistory;

import com.example.appcodingbat.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
