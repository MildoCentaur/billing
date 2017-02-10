package ar.com.adriabe.generic;

/**
 * Created by ajmild1 on 29/09/2015.
 */
public interface Builder<R, S> {

    public R build(S provider) throws KendoExecutionException;
}



