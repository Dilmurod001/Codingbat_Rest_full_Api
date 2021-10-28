package com.example.appcodingbat.reposistory;

import com.example.appcodingbat.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answers, Integer> {

}
