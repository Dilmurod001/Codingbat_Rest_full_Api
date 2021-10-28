package com.example.appcodingbat.pyload;

import lombok.Data;

@Data
public class TaskDTO {
    private String name;
    private String text;
    private String solution;
    private String hint;
    private Boolean isStar;
    private String method;
    private Integer languageId;
}
