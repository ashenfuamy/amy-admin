package site.zxhy.amyadmin.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
@Configuration
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public void NoResourceFoundExceptionHandler(NoResourceFoundException e) {
        e.printStackTrace();
    }
}
