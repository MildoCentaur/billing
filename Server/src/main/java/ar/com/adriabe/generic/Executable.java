package ar.com.adriabe.generic;


public interface Executable<T> {

    public T execute(T target) throws KendoExecutionException;
}