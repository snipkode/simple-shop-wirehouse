package com.wirehouse.simpleshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private Integer responseCode;
    private T data;
    
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResponseCode(200);
        response.setData(data);
        return response;
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(message);
        response.setResponseCode(200);
        response.setData(data);
        return response;
    }
    
    public static <T> ApiResponse<T> error(Integer responseCode, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(message);
        response.setResponseCode(responseCode);
        response.setData(null);
        return response;
    }
}