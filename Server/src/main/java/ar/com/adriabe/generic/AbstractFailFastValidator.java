package ar.com.adriabe.generic;

public abstract class AbstractFailFastValidator<T> implements Validator<T> {

    protected final ExecutableCollection<T> executables = new ExecutableCollection<T>();

    @Override
    public void validate(T entity) throws KendoExecutionException {
        try {
            executables.executeAll(entity);
        } catch (Exception e) {
            throw new KendoExecutionException(e.getMessage());
        }
    }
}
