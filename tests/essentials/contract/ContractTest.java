package essentials.contract;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Creator: Patrick
 * Created: 28.02.2019
 * Purpose:
 */
public class ContractTest {

    @Test
    public void testCheckNulls() {
        Contract.checkNulls();
        Contract.checkNulls("no null");
        Contract.checkNulls("no null", new Object());

        Object obj = null;
        assertTrue(throwsException(obj));
        assertTrue(throwsException("no null", obj));
        assertTrue(throwsException(obj, "no null"));
        assertTrue(throwsException("no null", obj, new Object()));
    }

    private boolean throwsException(Object... arguments) {

        try {
            Contract.checkNulls(arguments);
        } catch (ParameterNullException ex) {
            return true;
        }

        return false;
    }
}
