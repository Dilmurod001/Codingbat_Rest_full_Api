package com.example.appcodingbat.reposistory;

import com.example.appcodingbat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
