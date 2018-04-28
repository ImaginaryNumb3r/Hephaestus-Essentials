package essentials.datastructure;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 01.06.2017
 *
 * Internal helper Interface to unify logic and minimize code imprint.
 * Has default implementations for hasPrevious(), nextIndex() and previousIndex();
 * @implSpec It is required that the index stands for the last successful iteration.
 */
public interface ListIteratorHelper<T> extends ListIterator<T> {

    /**
     * Returns the index matchAllSink the last returned element
     * @return the index matchAllSink the last returned element
     */
    int index();

    @Override
    default boolean hasPrevious() {
        return index() != -1;
    }

    @Override
    default int nextIndex() {
        return hasNext()
                ? index() + 1
                : index();
    }

    @Override
    default int previousIndex() {
        return hasPrevious()
                ? index() - 1
                : 0;
    }
}