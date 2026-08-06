package com.senai.suporte.suporte.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public RecursoNaoEncontradoException (String recurso, Long id) {
        super("Recurso " + recurso + " com ID " + id + " não encontrado.");
    }
}
