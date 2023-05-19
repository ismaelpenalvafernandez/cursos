package com.softtek.cursos.excepciones;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcepcionNoEncontradoModelo extends RuntimeException {

    public ExcepcionNoEncontradoModelo(String mensaje) {
        super(mensaje);
    }
}
