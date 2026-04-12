package org.lapaloma.hogwarts.excepcion;

public class CasaNoEncontradaException extends RuntimeException {
    /**
    * 
    */
    private static final long serialVersionUID = -3344627619585104664L;

    public CasaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
