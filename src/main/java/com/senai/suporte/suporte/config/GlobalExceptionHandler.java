package com.senai.suporte.suporte.config;


import com.senai.suporte.suporte.exception.RecursoNaoEncontradoException;
import org.springframework.ui.Model ;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNaoEncontrado(RecursoNaoEncontradoException ex, Model model) {
        model.addAttribute("MensagemErro", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRuntime(RuntimeException ex, Model model) {
        model.addAttribute("MensagemErro", ex.getMessage());
        return "error";
    }
}
