package ar.com.adriabe.services.generics;

/**
 * Created by Mildo on 7/20/15.
 */
public interface Executor<T> {

    public T execute(T element);
}
