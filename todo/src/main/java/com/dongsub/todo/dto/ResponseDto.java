package com.dongsub.todo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private Boolean success;
    private T data;
    private Error error;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true,data,null);
    }

    public static <T> ResponseDto<T> fail(String code, String message) {
        return new ResponseDto<>(false,null,new Error(code,message));
    }

    @Getter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }

}
