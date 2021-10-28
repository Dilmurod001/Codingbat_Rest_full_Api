package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Users;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.UserDTO;
import com.example.appcodingbat.reposistory.UserRepository;
import com.example.appcodingbat.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final
    UserRepository userRepository;
    final
    UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping
    public HttpEntity<List<Users>> getAllCategory() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse delete = userService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.addUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.CREATED :
                HttpStatus.CONFLICT).
                body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody UserDTO userDTO) {

        ApiResponse apiResponse = userService.edit(id, userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
