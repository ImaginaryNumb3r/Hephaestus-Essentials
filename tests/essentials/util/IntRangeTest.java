package essentials.util;

import org.junit.Test;

import java.util.ListIterator;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;

import static essentials.util.IntRange.forward;
import static essentials.util.IntRange.range;
import static org.junit.Assert.*;

/**
 * Creator: Patrick
 * Created: 05.04.2019
 * Purpose:
 */
public class IntRangeTest {

    @Test
    public void testRandom() {
        final int val = 10;
        IntRange singletonRange = range(val, val);
        IntRange range = range(0, val);
        ListIterator<Integer> iter = range.randomIterator();

        for (int i = 0; i != 100; ++i) {
            assertEquals(singletonRange.random(), val, 0.001);
        }

        checkRandom(range::randomInt);
        checkRandom(iter::next);

        // Check random iterator
        iter = range.randomIterator();
        boolean[] marker = new boolean[val];
        int iterationCount = 0;

        while (iter.hasNext()) {
            Integer rand = iter.next();
            assertNotEquals("Random iterator of range returned invalid value null", null, rand);

            marker[rand] = true;
            ++iterationCount;
        }

        assertEquals("Number of random numbers is not equal to range size", val, iterationCount);

        for (boolean exists : marker) {
            assertTrue("Random iterator did not cover the full range", exists);
        }
    }

    private void checkRandom(IntSupplier randomSupplier) {
        int first = randomSupplier.getAsInt();
        int other = -1;
        for (int i = 0; i != 100000 && other == -1; ++i) {
            int random = randomSupplier.getAsInt();
            if (first != random) {
                other = random;
            }
        }

        assertNotEquals("Int range does not provide random numbers!", -1, other);
    }

    @Test
    public void testCreation() {
        final Integer start = 5;
        final Integer  end = 15;
        final int size = 11; // | 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 | = 11
        // Test Sequential order.
        IntRange range = range(start, end);

        assertEquals(start, range.getStart());
        assertEquals(end, range.getEnd());
        assertEquals(size, range.size());

        range = forward(start, end);

        assertEquals(start, range.getStart());
        assertEquals(end, range.getEnd());
        assertEquals(size, range.size());

        range = forward(end, start);

        assertEquals(start, range.getStart());
        assertEquals(end, range.getEnd());
        assertEquals(size, range.size());

        // Test backwards order
        range = IntRange.backwards(start, end);

        assertEquals(end, range.getStart());
        assertEquals(start, range.getEnd());
        assertEquals(size, range.size());

        range = IntRange.backwards(end, start);

        assertEquals(end, range.getStart());
        assertEquals(start, range.getEnd());
        assertEquals(size, range.size());
    }

    @Test
    public void testIteration() {
        checkSequential(-5, 15);
        checkSequential(5, 15);
        checkSequential(0, 15);
        checkSequential(0, 0);

        checkBackwards(15, -5);
        checkBackwards(15, 5);
        checkBackwards(15, 0);
        checkBackwards(0, 0);
    }

    private void checkSequential(int start, int end) {
        checkIteration(start, end, true, IntRange::forward);
        checkIteration(start, end, true, IntRange::range);
    }

    private void checkBackwards(int start, int end) {
        checkIteration(start, end, false, IntRange::backwards);
        checkIteration(start, end, false, IntRange::range);
    }

    private void checkIteration(int start, int end, boolean forward,
                                BiFunction<Integer, Integer, IntRange> constructor
    ) {
        int expected = start;
        IntRange range = constructor.apply(start, end);

        int iterations = 0;
        for (int val : range) {
            assertEquals(expected, val);
            expected = forward ? ++expected : --expected;

            ++iterations;
        }

        assertEquals("The range did not provide the correct amount of iterations (with direction: " + (forward ? "forward)" : "backwards)") , range.size(), iterations);
    }
}
