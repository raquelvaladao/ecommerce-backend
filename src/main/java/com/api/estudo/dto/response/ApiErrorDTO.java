package com.api.estudo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {
    private String message;
    private List<String> errors;
    private HttpStatus status;

    public ApiErrorDTO(String message, String singleError, HttpStatus httpStatus){
        this.message = message;
        this.errors = Arrays.asList(singleError);
        this.status = httpStatus;
    }
}
