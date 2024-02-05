package com.zhurawell.base.api.handling;

import com.zhurawell.base.api.dto.error.ErrorDto;
import com.zhurawell.base.service.exception.BuilderRuntimeException;
import com.zhurawell.base.service.exception.CustomExceptionHandler;
import com.zhurawell.base.service.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonErrorHandler {

    private final ErrorDto defaultErrorDto = new ErrorDto(ErrorCodes.DEFAULT.getCode(), ErrorCodes.DEFAULT.getMessage());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleCommonError(Exception ex) {
        log.error("Unexpected error occurred", ex);
        BuilderRuntimeException root =  CustomExceptionHandler.getBuilderRuntimeException(ex);
        ErrorDto errorDto = root == null ? defaultErrorDto : new ErrorDto(root.getCode(), root.getMessage());
        return ResponseEntity.internalServerError().body(errorDto);
    }

    @ExceptionHandler(BuilderRuntimeException.class)
    public ResponseEntity<ErrorDto> handleBuilderError(BuilderRuntimeException ex) {
        log.error("Error occurred", ex);
        return ResponseEntity.internalServerError().body(new ErrorDto(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleBuilderError(BadCredentialsException ex) {
        log.error("Error occurred", ex);
        return ResponseEntity.internalServerError().body(new ErrorDto(ErrorCodes.C_102.getCode(), ErrorCodes.C_102.getMessage()));
    }

}
