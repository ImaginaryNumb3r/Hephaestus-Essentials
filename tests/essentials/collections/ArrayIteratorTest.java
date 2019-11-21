package essentials.collections;


import org.junit.Test;

import java.util.NoSuchElementException;

import static org.bitbucket.cowwoc.requirements.DefaultRequirements.requireThat;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
public class ArrayIteratorTest {

    @Test
    public void testEmptyArray() {
        int[] nullArray = null;
        Integer i = null;

        int[] array = { 1, 1, 1 };
        requireThat(array, "array").isNotNull();

        requireThat(array, "array").isNotNull();
        requireThat(array, "array").isNotNull().isNotEmpty();

        requireThat((Object) array, "i").isNotNull();
        requireThat(array, "i").isNotNull();
        requireThat(array, "i").length().isBetweenClosed(0, 3);

        requireThat((Object) array, "array").isNotNull();
        requireThat(array, "array").length().isNotNull();

        requireThat(nullArray, "array").length().isNull();

        requireThat(array, "i").isNotEmpty();

        Integer[] emptyArray = new Integer[0];
        ArrayIterator<Integer> iterator = new ArrayIterator<>(emptyArray);
        assert !iterator.hasNext();

        try {
            iterator.next();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }
    }

    @Test
    public void testSingletonArray() {
        final int element = 1;

        ArrayIterator<Integer> iterator = new ArrayIterator<>(new Integer[]{element});
        assert iterator.hasNext();

        boolean equals = iterator.next() == element;
        assert equals;

        try {
            iterator.next();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }
    }
}
