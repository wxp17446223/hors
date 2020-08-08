package cn.hors.controller.backstage;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String error(){
        return "/backstage/error/403";
    }
}
