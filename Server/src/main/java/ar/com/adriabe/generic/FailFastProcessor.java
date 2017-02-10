package ar.com.adriabe.generic;


public abstract class FailFastProcessor<T> implements Processor<T> {

    protected final ExecutableCollection<T> executables = new ExecutableCollection<T>();

    @Override
    public void process(T entity) throws KendoExecutionException {
        try {
            executables.executeAll(entity);
        } catch (KendoExecutionException e) {
            e.printStackTrace();
            throw new KendoExecutionException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Proceso interrumpido.");
        }

    }

}