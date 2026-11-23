package br.com.software.api_autenticacao.handlers;

import br.com.software.api_autenticacao.exceptions.EmailJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailJaCadastradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse handleEmailJaCadastrado(EmailJaCadastradoException e) {
        return new ErroResponse("email", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroResponse> handleValidacao(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(erro -> {
                    String tipo = erro instanceof FieldError campo
                            ? campo.getField()
                            : erro.getObjectName();

                    return new ErroResponse(tipo, erro.getDefaultMessage());
                })
                .toList();
    }

    public record ErroResponse(
            String tipo,
            String mensagem
    ) {}
}
