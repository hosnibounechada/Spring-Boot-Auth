package com.hb.auth.exception;

public class FileSizeLimitException extends RuntimeException{
    public FileSizeLimitException(String message){
        super(message);
    }
}
