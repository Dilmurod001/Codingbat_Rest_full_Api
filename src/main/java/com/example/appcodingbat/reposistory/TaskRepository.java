package com.example.appcodingbat.reposistory;

import com.example.appcodingbat.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {


}
