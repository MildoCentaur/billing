package ar.com.adriabe.generic;

public interface Registrar<R, S> {

    public R register(S provider) throws KendoExecutionException;

}
