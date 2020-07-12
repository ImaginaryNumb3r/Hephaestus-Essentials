package essentials.contract;

import org.jetbrains.annotations.NotNull;

/**
 * @author Patrick
 * @since 23.05.2017
 */
public class InstanceNotAllowedException extends RuntimeException {

    public InstanceNotAllowedException(@NotNull Class<?> type) {
        super("Class \"" + type.getName() +"\" cannot be instantiated");
    }
}
