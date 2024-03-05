package ru.otus.pro.Yaremchuk.reactive.service.exceptions;

public class AppException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public AppException(String code) {
        this.code = code;
    }
}
