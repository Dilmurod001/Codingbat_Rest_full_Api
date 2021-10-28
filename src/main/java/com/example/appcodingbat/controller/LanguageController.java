package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Languages;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.LanguageDTO;
import com.example.appcodingbat.reposistory.LanguageRepository;
import com.example.appcodingbat.service.LanguageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    final
    LanguageRepository languageRepository;
    final
    LanguageService languageService;

    public LanguageController(LanguageRepository languageRepository, LanguageService languageService) {
        this.languageRepository = languageRepository;
        this.languageService = languageService;
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(languageRepository.findById(id));
    }

    @GetMapping
    public HttpEntity<List<Languages>> getAllCategory() {
        return ResponseEntity.ok(languageRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse delete = languageService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody LanguageDTO languageDTO) {

        ApiResponse apiResponse = languageService.edit(id, languageDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody LanguageDTO languageDTO) {
        ApiResponse apiResponse = languageService.add(languageDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

}
