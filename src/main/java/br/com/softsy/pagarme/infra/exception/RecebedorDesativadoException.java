package br.com.softsy.pagarme.infra.exception;

public class RecebedorDesativadoException extends RuntimeException {
    public RecebedorDesativadoException(String message) {
        super(message);
    }
}
