package essentials.collections;


import org.jetbrains.annotations.NotNull;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 * Purpose:
 */
public interface PeekIteratorEx<T, X extends Exception> extends IteratorEx<T, X> {

    T peek() throws X;

    static <T, X extends Exception> PeekIteratorEx<T, X> of(@NotNull IteratorEx<T, X> iterator) throws X{
        if (iterator instanceof PeekIteratorEx) {
            return (PeekIteratorEx<T, X>) iterator;
        }

        return new PeekIteratorExImpl<>(iterator);
    }
}
