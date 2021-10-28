package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Example;
import com.example.appcodingbat.entity.Tasks;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.ExampleDTO;
import com.example.appcodingbat.reposistory.ExampleRepository;
import com.example.appcodingbat.reposistory.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExampleService {
    final
    ExampleRepository exampleRepository;
    final
    TaskRepository taskRepository;

    public ExampleService(ExampleRepository exampleRepository, TaskRepository taskRepository) {
        this.exampleRepository = exampleRepository;
        this.taskRepository = taskRepository;
    }

    public ApiResponse delete(Integer id) {
        if (exampleRepository.existsById(id)) {
            exampleRepository.deleteById(id);
        } else {
            return new ApiResponse("Id not found", false);
        }
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse edit(Integer id, ExampleDTO exampleDTO) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Example example = optionalExample.get();
        example.setText(exampleDTO.getText());
        Optional<Tasks> byId = taskRepository.findById(exampleDTO.getTaskId());
        if (!byId.isPresent()) {
            return new ApiResponse("Task id not found", false);
        }
        example.setTask(byId.get());
        exampleRepository.save(example);
        return new ApiResponse("Edited", true);
    }

    public ApiResponse add(ExampleDTO exampleDTO) {
        Example example = new Example();
        example.setText(exampleDTO.getText());
        Optional<Tasks> byId = taskRepository.findById(exampleDTO.getTaskId());
        if (!byId.isPresent()) {
            return new ApiResponse("Task id not found", false);
        }
        example.setTask(byId.get());
        exampleRepository.save(example);
        return new ApiResponse("Saved", true);
    }
}
