package com.example.appcodingbat.pyload;

import lombok.Data;

@Data
public class AnswerDTO {
    private String text;
    private Integer taskId;
    private Integer userId;
    private Boolean isCorrect;
}
