package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Users;
import com.example.appcodingbat.pyload.ApiResponse;
import com.example.appcodingbat.pyload.UserDTO;
import com.example.appcodingbat.reposistory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }else{
            return new ApiResponse("Id not found", false);
        }
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse addUser(UserDTO userDTO) {
        Users users = new Users();
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        userRepository.save(users);
        return new ApiResponse("Saved", true);
    }

    public ApiResponse edit(Integer id, UserDTO userDTO) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        Users users = optionalUsers.get();
        if (userDTO.getEmail() != null){
            users.setPassword(userDTO.getPassword());
        }
        if (userDTO.getPassword() != null) {
            users.setEmail(userDTO.getPassword());
        }
        userRepository.save(users);
        return new ApiResponse("Edited", true);

    }
}
