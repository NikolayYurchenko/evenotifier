package com.crazygeniuses.notifier.evenotifier.exception.configuration.rest;

import com.crazygeniuses.notifier.evenotifier.exception.BaseEvenotifierException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private int code;

    private Long timestamp;

    private String message;

    private String cause;

    private String stackTrace;

    private Map<String, String> errors = new HashMap<>();

    private final static int defaultErrorCode = 500;

    /**
     * Build default system exception
     * @return
     */
    protected static ApiErrorResponse buildDefault() {

        ApiErrorResponse response = new ApiErrorResponse();

        response.setCode(defaultErrorCode);
        response.setTimestamp(System.currentTimeMillis());

        return response;
    }

    /**
     * Build constraint validation api response
     * @param e
     * @return
     */
    protected static ApiErrorResponse buildForConstraintViolation(ConstraintViolationException e) {

        ApiErrorResponse response = new ApiErrorResponse();

        response.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage(e.getMessage());
        response.setCause(e.getCause().toString());

        e.getConstraintViolations().forEach(it -> {
            response.getErrors().put(it.getPropertyPath().toString(), it.getMessage());
        });

        return response;
    }

    /**
     * Build api response for system global exception
     * @param e
     * @return
     */
    protected static ApiErrorResponse buildForGlobalException(Exception e) {

        ApiErrorResponse response = new ApiErrorResponse();

        response.setMessage(e.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        response.setCause(e.getCause().toString());
        response.setStackTrace(Arrays.toString(e.getStackTrace()));

        if (e instanceof BaseEvenotifierException) {
            response.setCode(((BaseEvenotifierException) e).getCode());
        } else if (e.getCause() instanceof BaseEvenotifierException) {
            response.setCode(((BaseEvenotifierException) e.getCause()).getCode());
        }

        return response;
    }
}
