package ar.com.adriabe.generic;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Creates the chain of {@link Executable}s and wraps the executables
 * chain execution via its executeAll method.
 * <p/>
 * By default this class provides a "fail fast" chain, this is: the first failing executable
 * will interrupt the execution chain.
 * <p/>
 * <code>ValidatorChain</code>'s {@link Executable}s can be iterated using standard {@link Iterable} mechanism.
 * This is provided to facilitate new implementations that does not interrupts the validation chain on
 * validation failures, thus giving the chance to execute all executables and fail last.
 */
public class ExecutableCollection<T> implements Iterable<Executable<T>> {
    private List<Executable<T>> executables = Lists.newArrayList();

    /**
     * Adds a new {@link Executable} to the validation chain
     *
     * @param validationRule the next validation rule for the chain
     */
    public void add(Executable<T> validationRule) {
        executables.add(validationRule);
    }

    /**
     * Wraps the validation chain execution
     *
     * @param target the object to be validated
     * @throws KendoExecutionException indicates the validation chain has failed
     */
    public T executeAll(T target) throws KendoExecutionException {
        for (Executable<T> executable : executables) {
            executable.execute(target);
        }
        return target;
    }

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Executable<T>> iterator() {
        return executables.iterator();
    }
}
