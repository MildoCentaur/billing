package ar.com.adriabe.generic;

public interface Processor<T> {
    void process(T context) throws KendoExecutionException;
}
