package com.eventmangment.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResponseStructure<T>{
    private int statusCode;
    private String message;
    private T data;
}