package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Example;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.ExampleDTO;
import com.example.appcodingbat.reposistory.ExampleRepository;
import com.example.appcodingbat.service.ExampleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    final
    ExampleRepository exampleRepository;
    final
    ExampleService exampleService;

    public ExampleController(ExampleRepository exampleRepository, ExampleService exampleService) {
        this.exampleRepository = exampleRepository;
        this.exampleService = exampleService;
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(exampleRepository.findById(id));
    }

    @GetMapping
    public HttpEntity<List<Example>> getAllCategory() {
        return ResponseEntity.ok(exampleRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse delete = exampleService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ExampleDTO exampleDTO) {

        ApiResponse apiResponse = exampleService.edit(id, exampleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody ExampleDTO exampleDTO) {
        ApiResponse apiResponse = exampleService.add(exampleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
