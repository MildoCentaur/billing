package ar.com.adriabe.generic;


public interface Validator<T> {
    void validate(T entity) throws KendoExecutionException;
}
