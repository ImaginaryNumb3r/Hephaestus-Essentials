package essentials.collections;

import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;

/**
 * @author Patrick Plieschnegger
 * @since 16.11.2016
 *
 * A convenience Interface for implementing the listIterator() method that can be found on java.util.List.
 * It extends the Iterable interface and auto-implements it.
 */
public interface IterableList<T> extends Iterable<T> {

    /**
     * Returns a ListIterator over elements of type {@code T}.
     * @return a ListIterator
     */
    @NotNull
    @Override
    default ListIterator<T> iterator(){
        return listIterator();
    }

    /**
     * Returns a ListIterator over elements of type {@code T}.
     * @return a ListIterator
     */
    @NotNull
    ListIterator<T> listIterator();
}
