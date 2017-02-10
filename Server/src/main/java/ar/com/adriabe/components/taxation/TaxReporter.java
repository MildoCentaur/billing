package ar.com.adriabe.components.taxation;

import java.util.List;

/**
 * Created by AJMILD1 on 19/10/2015.
 */
public interface TaxReporter<R,C> {

    public List<R> report(C context);
}
