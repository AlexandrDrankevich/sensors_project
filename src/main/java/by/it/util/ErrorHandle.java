package by.it.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorHandle {
    public static String errorResponse(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append(fieldError.getField())
                    .append(":").append(fieldError.getDefaultMessage() == null ? fieldError.getCode() : fieldError
                            .getDefaultMessage());
        }
        return stringBuilder.toString();
    }
}
