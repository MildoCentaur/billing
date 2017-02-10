package ar.com.adriabe.generic;

public abstract class AbstractFailLastValidator<T> implements Validator<T> {

    protected final ExecutableCollection<T> executables = new ExecutableCollection<T>();

    @Override
    public void validate(T entity) throws KendoExecutionExceptionsContainer {
        KendoExecutionExceptionsContainer kendoExecutionExceptionsContainer = null;
        for (Executable<T> executable : executables) {
            try {
                executable.execute(entity);
            } catch (KendoExecutionException e) {
                if (kendoExecutionExceptionsContainer == null) {
                    kendoExecutionExceptionsContainer = new KendoExecutionExceptionsContainer(e.getMessage());
                } else {
                    kendoExecutionExceptionsContainer.add(e.getMessage());
                }
            }
        }

        if (kendoExecutionExceptionsContainer != null) {
            throw kendoExecutionExceptionsContainer;
        }
    }
}
