package essentials.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 * Purpose:
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface Package {
}
