package essentials.collections;

import essentials.functional.exception.ConsumerEx;
import essentials.functional.exception.FunctionalMappingException;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Creator: Patrick
 * Created: 16.05.2018
 *
 * @param <T> the returned data of the iterator.
 * @param <X> the exception that can be thrown.
 * An iterator which can throw an exception of type X.
 */
public interface IteratorEx<T, X extends Exception> {

    boolean hasNext();

    T next() throws X;

    static <T, X extends Exception> IteratorEx<T, X> of(Iterator<T> iterator) {
        return new IteratorEx<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }
        };
    }

    default void forEach(@NotNull ConsumerEx<? super T, X> action) throws X {
        while (hasNext()) {
            T value = next();
            action.tryAccept(value);
        }
    }

    @Deprecated() // I will get rid of FunctionalMappingException in the future. Don't use it.
    default Iterator<T> toIterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return IteratorEx.this.hasNext();
            }

            @Override
            public T next() {
                try {
                    return IteratorEx.this.next();
                } catch (Exception ex) {
                    throw new FunctionalMappingException(ex);
                }
            }
        };
    }

    /**
     * Factory method to create a function ally identical Iterator from an IteratorEX which returns a RuntimeException.
     *
     * @param iteratorEx original instance
     * @param <T> type of conveyed data
     * @param <X> type of runtime exception
     * @return Wrapped IteatorEX instance inside a new java.lang.Iterator.
     */
    static <T, X extends RuntimeException> Iterator<T> toIterator(IteratorEx<T, X> iteratorEx) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iteratorEx.hasNext();
            }

            @Override
            public T next() {
                return iteratorEx.next();
            }
        };
    }
}
