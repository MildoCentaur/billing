package ar.com.adriabe.services;

/**
 * Created by Mildo on 3/8/15.
 */
public class InvalidDataException extends Exception {

    public InvalidDataException() {
        super("Parametros invalidos");
    }
}
