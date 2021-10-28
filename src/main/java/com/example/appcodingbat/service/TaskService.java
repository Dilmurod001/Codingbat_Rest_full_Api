package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Languages;
import com.example.appcodingbat.entity.Tasks;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.TaskDTO;
import com.example.appcodingbat.reposistory.LanguageRepository;
import com.example.appcodingbat.reposistory.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    final
    TaskRepository taskRepository;
    final
    LanguageRepository languageRepository;

    public TaskService(TaskRepository taskRepository, LanguageRepository languageRepository) {
        this.taskRepository = taskRepository;
        this.languageRepository = languageRepository;
    }


    public ApiResponse delete(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            return new ApiResponse("Id not found", false);
        }
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse saveCategory(TaskDTO taskDTO) {
        Tasks tasks = new Tasks();
        tasks.setHitn(taskDTO.getHint());
        tasks.setTaskName(taskDTO.getName());
        tasks.setHas_star(taskDTO.getIsStar());
        tasks.setMethod(taskDTO.getMethod());
        tasks.setSolution(taskDTO.getSolution());
        tasks.setText(taskDTO.getText());
        Optional<Languages> optionalLanguages = languageRepository.findById(taskDTO.getLanguageId());
        if (!optionalLanguages.isPresent()) {
            return new ApiResponse("Language id not found", false);
        }
        tasks.setLanguage(optionalLanguages.get());
        taskRepository.save(tasks);
        return new ApiResponse("Saved", true);

    }

    public ApiResponse edit(Integer id, TaskDTO taskDTO) {
        Optional<Tasks> optionalTasks = taskRepository.findById(id);
        if (!optionalTasks.isPresent()) {
            return new ApiResponse("Task id not found", false);
        }
        Tasks tasks = optionalTasks.get();
        tasks.setHitn(taskDTO.getHint());
        tasks.setTaskName(taskDTO.getName());
        tasks.setHas_star(taskDTO.getIsStar());
        tasks.setMethod(taskDTO.getMethod());
        tasks.setSolution(taskDTO.getSolution());
        tasks.setText(taskDTO.getText());
        Optional<Languages> optionalLanguages = languageRepository.findById(taskDTO.getLanguageId());
        if (!optionalLanguages.isPresent()) {
            return new ApiResponse("Language id not found", false);
        }
        tasks.setLanguage(optionalLanguages.get());
        taskRepository.save(tasks);
        return new ApiResponse("Edited", true);
    }
}
