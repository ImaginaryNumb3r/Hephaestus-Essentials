package essentials.annotations;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author Patrick
 * @since 18.05.2017
 */
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface ToTest {
}
