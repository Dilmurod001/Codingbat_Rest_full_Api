package com.example.appcodingbat.pyload;

import com.example.appcodingbat.entity.Categories;
import lombok.Data;

@Data
public class ApiResponse {
    private String massage;
    private boolean success;
    private Object object;

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }

    public ApiResponse(String found, boolean success, Categories category) {
    }
}
