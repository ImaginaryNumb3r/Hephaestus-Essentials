package essentials.contract;

import org.jetbrains.annotations.NotNull;

/**
 * @author Patrick
 * @since 23.05.2017
 *
 * An exception that is thrown when an instance of a class is now allowed (think java.lang.Math)
 */
public class InstanceNotAllowedException extends RuntimeException {

    public InstanceNotAllowedException(@NotNull Class<?> type) {
        super("Class \"" + type.getName() +"\" cannot be instantiated");
    }
}
