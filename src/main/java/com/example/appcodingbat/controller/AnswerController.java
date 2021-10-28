package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Answers;
import com.example.appcodingbat.pyload.AnswerDTO;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.reposistory.AnswerRepository;
import com.example.appcodingbat.service.AnswerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    final
    AnswerRepository answerRepository;
    final
    AnswerService answerService;

    public AnswerController(AnswerRepository answerRepository, AnswerService answerService) {
        this.answerRepository = answerRepository;
        this.answerService = answerService;
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(answerRepository.findById(id));
    }

    @GetMapping
    public HttpEntity<List<Answers>> getAllCategory() {
        return ResponseEntity.ok(answerRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse delete = answerService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody AnswerDTO answerDTO) {
        ApiResponse apiResponse = answerService.addAnswer(answerDTO);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.CREATED :
                HttpStatus.CONFLICT).
                body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody AnswerDTO answerDTO) {

        ApiResponse apiResponse = answerService.edit(id, answerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
