package essentials.util;

import essentials.annotations.ToTest;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Patrick Plieschnegger
 * Created: 18.08.2017
 *
 * Implements an immutable range of integers.
 */
@ToTest
public final class IntRange implements Range, Iterable<Integer> {
    private final int inclusiveStart;
    private final int inclusiveEnd;
    private final boolean _forward;

    /**
     * Creates a range of ints from a given inclusiveStart and inclusiveEnd.
     * The lower given variable will serve as the inclusiveStart and the other will be the inclusiveEnd of the range.
     */
    private IntRange(int inclusiveStart, int inclusiveEnd) {
        _forward = inclusiveStart < inclusiveEnd;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    /**
     * Creates a range of ints from a given inclusiveStart and inclusiveEnd.
     * The lower given variable will serve as the inclusiveStart and the other will be the inclusiveEnd of the range.
     */
    public static IntRange range(int startInclusive, int endInclusive) {
        return new IntRange(startInclusive, endInclusive);
    }

    /**
     * Creates a range of ints from a given inclusiveStart and inclusiveEnd.
     * The lower given variable will serve as the inclusiveStart and the other will be the inclusiveEnd of the range.
     */
    public static IntRange range(Integer startInclusive, Integer endInclusive) {
        return new IntRange(startInclusive, endInclusive);
    }

    /**
     * Creates a range of ints from a given inclusiveStart and inclusiveEnd.
     * The lower given variable will serve as the inclusiveStart and the other will be the inclusiveEnd of the range.
     * Performs a size comparison and takes the lower argument as inclusiveStart.
     *
     * @param int1 that does not need to be the lower value
     * @param int2 that does not need to be the greater value
     */
    public static IntRange forward(int int1, int int2) {
        if (int1 > int2){
            // Swap
            int temp = int1;
            int1 = int2;
            int2 = temp;
        }

        return new IntRange(int1, int2);
    }

    /**
     * Creates a range of ints from a given inclusiveStart and inclusiveEnd.
     * The lower given variable will serve as the inclusiveStart and the other will be the inclusiveEnd of the range.
     * Performs a size comparison and takes the lower argument as inclusiveStart.
     *
     * @param int1 that does not need to be the greater value
     * @param int2 that does not need to be the lower value
     */
    public static IntRange backwards(int int1, int int2) {
        if (int1 < int2){
            // Swap
            int temp = int1;
            int1 = int2;
            int2 = temp;
        }

        return new IntRange(int1, int2);
    }

    /**
     * @return An array containing all values in the bound in the specified order.
     */
    public int[] toArray(){
        int[] array = new int[size()];

        int start = _forward ? 0 : size();
        int end   = _forward ? size() : 0;

        for (int i = start; i != end; i = _forward ? ++i : --i){
            array[i] = i + this.inclusiveStart;
        }

        return array;
    }

    @NotNull
    @Override
    public Integer getStart() {
        return inclusiveStart;
    }

    @NotNull
    @Override
    public Integer getEnd() {
        return inclusiveEnd;
    }

    public void forEach(IntConsumer consumer) {
        int start = _forward ? this.inclusiveStart : this.inclusiveEnd;
        int end = _forward ? this.inclusiveEnd : this.inclusiveStart;
        int pos = _forward ? start : end;

        while (_forward ? pos != end : pos != start) {
            consumer.accept(pos);
            pos = _forward ? ++pos : --end;
        }
    }

    public int randomInt() {
        return (int) random();
    }

    /**
     * @return an an int within the range, casted to a double.
     */
    public double random() {
        int exclusiveEnd = inclusiveEnd + 1;
        Random random = ThreadLocalRandom.current();
        return random.ints(inclusiveStart, exclusiveEnd).findFirst().getAsInt();
    }

    /**
     * @return the size of amount of ints in the range. Is greater than zero.
     */
    public int size(){
        // +1 because there is always at least one element.
        // e.g. the range 10 to 10 is not zero.
        return Math.abs(inclusiveEnd - inclusiveStart) + 1;
    }

    public IntStream stream(){
        return _forward
                ? IntStream.range(inclusiveStart, inclusiveEnd)
                : IntStream.range(inclusiveEnd, inclusiveStart);
    }

    /**
     * @return an iterator providing all values from the range exactly once, but at random.
     */
    public ListIterator<Integer> randomIterator(){
        List<Integer> integers = stream()
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(integers);

        return integers.listIterator();
    }

    /**
     * Inclusive contains check.
     *
     * @param value actual value
     * @return true if it is contained in the range, including the min and max values.
     */
    public boolean contains(double value) {
        return _forward
                ? value >= inclusiveStart && value <= inclusiveEnd
                : value <= inclusiveStart && value >= inclusiveEnd;
    }

    /**
     * Exclusive contains check.
     *
     * @param value actual value
     * @return true if it is contained in the range, excluding the min and max values.
     */
    public boolean exclusiveContains(double value) {
        return _forward
                ? value > inclusiveStart && value < inclusiveEnd
                : value < inclusiveStart && value > inclusiveEnd;
    }

    @Override
    public String toString() {
        return inclusiveStart + ".." + inclusiveEnd;
    }

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return _forward
                ? new IntRangeIterator( 1, inclusiveEnd, inclusiveStart)
                : new IntRangeIterator(-1, inclusiveEnd, inclusiveStart);
    }

    private class IntRangeIterator implements Iterator<Integer> {
        private final int _step;
        private final int _inclusiveEnd;
        private int _current;

        private IntRangeIterator(int step, int exclusiveEnd, int current) {
            _step = step;
            _inclusiveEnd = exclusiveEnd;
            _current = current;

            if (step == 0) {
                throw new IllegalArgumentException("Step may not be zero");
            }
        }

        @Override
        public boolean hasNext() {
            // Overflow aware comparison for forward and backward iterations.
            return _step > 0
                    ? _current <= _inclusiveEnd
                    : _current >= _inclusiveEnd;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                String range = IntRange.this.toString();
                throw new NoSuchElementException(_current + " is not contained in range " + range);
            }
            int next = _current;
            _current += _step;

            return next;
        }
    }
}
