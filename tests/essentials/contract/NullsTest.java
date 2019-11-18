package essentials.contract;

import org.junit.Test;

import static essentials.util.Nulls.init;
import static org.junit.Assert.assertEquals;

public class NullsTest {

    @Test
    public void testInit() {
        Object instance = new Object();
        Object replacement = new Object();

        Object result = init(instance, () -> replacement);
        assertEquals(result, instance);

        Object nullReference = null;
        result = init(nullReference, () -> replacement);
        assertEquals(result, replacement);
    }
}
