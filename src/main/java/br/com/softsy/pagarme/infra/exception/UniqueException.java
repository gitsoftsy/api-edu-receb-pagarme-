package br.com.softsy.pagarme.infra.exception;

public class UniqueException extends RuntimeException {
    public UniqueException(String mensagem) {
        super(mensagem);
    }
}
