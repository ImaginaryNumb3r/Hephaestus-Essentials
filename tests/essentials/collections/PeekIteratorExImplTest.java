package essentials.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Creator: Patrick
 * Created: 07.03.2019
 * Purpose:
 */
public class PeekIteratorExImplTest {

    @Test
    public void testPeekIterator() {
        List<Integer> nullTestList = Arrays.asList(null, 0, 1, 2, null, 3, 4, 5, 6, null);
        List<Integer> testList = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

        // Test with and without nulls.
        for (List<Integer> list : Arrays.asList(nullTestList, testList)) {
            PeekIterator<Integer> peeker = PeekIterator.of(list.iterator());

            LinkedList<Integer> iterated = new LinkedList<>();
            while (peeker.hasNext()) {
                Integer peeked1 = peeker.peek();
                Integer peeked2 = peeker.peek();

                Integer next = peeker.next();

                // Multiple peeks must deliver the same result.
                assertEquals(peeked1, peeked2);
                // Next and peeked elements must be equal.
                assertEquals(peeked1, next);
                iterated.addLast(next);
            }

            assertEquals(list, iterated);
        }
    }
}
