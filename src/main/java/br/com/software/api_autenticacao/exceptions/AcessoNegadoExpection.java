package br.com.software.api_autenticacao.exceptions;

public class AcessoNegadoExpection extends RuntimeException {
    @Override
    public String getMessage() {
        return "Acesso negado. Usuário inválido.";
    }
}
