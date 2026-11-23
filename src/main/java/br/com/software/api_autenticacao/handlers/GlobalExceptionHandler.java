package br.com.software.api_autenticacao.handlers;

import br.com.software.api_autenticacao.exceptions.EmailJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailJaCadastradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse handleEmailJaCadastrado(EmailJaCadastradoException e) {
        return new ErroResponse("email", e.getMessage());
    }

    public record ErroResponse(String tipo, String mensagem) {}
}
