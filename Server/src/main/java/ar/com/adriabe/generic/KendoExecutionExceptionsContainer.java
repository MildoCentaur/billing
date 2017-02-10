package ar.com.adriabe.generic;

import java.util.ArrayList;
import java.util.List;

public class KendoExecutionExceptionsContainer extends KendoExecutionException {

    private List<KendoExecutionException> exceptions = new ArrayList<KendoExecutionException>();

    public KendoExecutionExceptionsContainer(String key) {
        super(key);
        exceptions.add(new KendoExecutionException(key));
    }

    public void add(String key) {
        exceptions.add(new KendoExecutionExceptionsContainer(key));
    }

    public List<KendoExecutionException> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<KendoExecutionException> exceptions) {
        this.exceptions = exceptions;
    }
}
