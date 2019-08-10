package essentials.collections;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 * Purpose:
 */
public interface PeekIterator<T> extends PeekIteratorEx<T, RuntimeException>, Iterator<T> {

    T peek();

    static <T> PeekIterator<T> of(@NotNull Iterable<T> iterable) {
        return PeekIterator.of(iterable.iterator());
    }

    static <T> PeekIterator<T> of(@NotNull Iterator<T> iterator) {
        if (iterator instanceof PeekIterator) {
            return (PeekIterator<T>) iterator;
        }

        return new PeekIteratorImpl<>(iterator);
    }

    static <T, X extends Exception> PeekIteratorEx<T, X> of(@NotNull IteratorEx<T, X> iterator) {
        if (iterator instanceof PeekIterator) {
            return (PeekIteratorEx<T, X>) iterator;
        }

        return new PeekIteratorExImpl<>(iterator);
    }
}
