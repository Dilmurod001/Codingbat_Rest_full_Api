package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Answers;
import com.example.appcodingbat.entity.Tasks;
import com.example.appcodingbat.entity.Users;
import com.example.appcodingbat.pyload.AnswerDTO;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.reposistory.AnswerRepository;
import com.example.appcodingbat.reposistory.TaskRepository;
import com.example.appcodingbat.reposistory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    final
    AnswerRepository answerRepository;
    final
    TaskRepository taskRepository;
    final
    UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse delete(Integer id) {
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
        } else {
            return new ApiResponse("Id not found", false);
        }
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse addAnswer(AnswerDTO answerDTO) {
        Answers answers = new Answers();
        answers.setText(answerDTO.getText());
        answers.set_correct(answerDTO.getIsCorrect());
        Optional<Tasks> optionalTasks = taskRepository.findById(answerDTO.getTaskId());
        if (!optionalTasks.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        answers.setTask(optionalTasks.get());
        Optional<Users> optionalUsers = userRepository.findById(answerDTO.getUserId());
        if (!optionalUsers.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        answers.setUser(optionalUsers.get());
        answerRepository.save(answers);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse edit(Integer id, AnswerDTO answerDTO) {
        Optional<Answers> optionalAnswers = answerRepository.findById(id);
        if (!optionalAnswers.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Answers answers = optionalAnswers.get();
        answers.setText(answerDTO.getText());
        answers.set_correct(answerDTO.getIsCorrect());
        Optional<Tasks> optionalTasks = taskRepository.findById(answerDTO.getTaskId());
        if (!optionalTasks.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        answers.setTask(optionalTasks.get());
        Optional<Users> optionalUsers = userRepository.findById(answerDTO.getUserId());
        if (!optionalUsers.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        answers.setUser(optionalUsers.get());
        answerRepository.save(answers);
        return new ApiResponse("Edited", true);
    }
}
