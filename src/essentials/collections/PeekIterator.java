package essentials.collections;

import java.util.Iterator;

import static essentials.contract.Contract.checkNull;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 * Purpose:
 */
public interface PeekIterator<T> extends Iterator<T> {

    T peek();

    static <T> PeekIterator<T> of(Iterable<T> iterable) {
        return of(iterable.iterator());
    }

    static <T> PeekIterator<T> of(Iterator<T> iterator) {
        if (iterator instanceof PeekIterator) {
            return (PeekIterator<T>) iterator;
        }

        checkNull(iterator, "iterator");
        return new PeekIteratorImpl<>(iterator);
    }
}
