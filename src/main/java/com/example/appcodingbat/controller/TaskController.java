package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Tasks;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.TaskDTO;
import com.example.appcodingbat.reposistory.TaskRepository;
import com.example.appcodingbat.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    final
    TaskRepository taskRepository;
    final
    TaskService taskService;

    public TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(taskRepository.findById(id));
    }

    @GetMapping
    public HttpEntity<List<Tasks>> getAllCategory() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse delete = taskService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.saveCategory(taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.CREATED :
                HttpStatus.CONFLICT).
                body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {

        ApiResponse apiResponse = taskService.edit(id, taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
