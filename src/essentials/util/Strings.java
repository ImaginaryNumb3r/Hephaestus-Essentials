package essentials.util;

import essentials.contract.InstanceNotAllowedException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Patrick Plieschnegger
 */
public final class Strings {

    private Strings() {
        throw new InstanceNotAllowedException(getClass());
    }

    public static String toString(Object object, String fallback) {
        return object != null ? object.toString() : fallback;
    }

    /**
     * Derives the string representation of the provided objects and concatenates them.
     * @param objects using toString to derive their string representation.
     * @return joined string of all objects (via string representation).
     */
    public static String concat(Object... objects) {
        return concat(Arrays.asList(objects));
    }

    /**
     * Derives the string representation of the provided objects and concatenates them.
     * @param objects using toString to derive their string representation.
     * @return joined string of all objects (via string representation).
     */
    public static String concat(Iterable<?> objects) {
        return joinToString(objects, "");
    }

    /**
     * Joins the string representation of the provided objects, separated by a delimiter.
     *
     * @param objects using toString to derive their string representation.
     * @param delimiter the value between the objects.
     * @return joined string of objects (via string representation), separated by a delimiter.
     */
    public static String joinToString(@NotNull Iterable<?> objects, @NotNull String delimiter) {
        List<String> strings = new ArrayList<>();
        objects.forEach(obj -> strings.add(obj.toString()));

        return join(strings, delimiter);
    }

    /**
     * Joins the strings, each separated by a delimiter.
     *
     * @param strings which are joined
     * @param delimiter the value between the objects.
     * @return joined string of all strings, separated by delimiters.
     */
    public static String join(@NotNull Iterable<? extends CharSequence> strings, @NotNull CharSequence delimiter) {
        String result = "";

        Iterator<? extends CharSequence> iter = strings.iterator();
        while (iter.hasNext()) {
            CharSequence string = iter.next();
            result += string;

            if (iter.hasNext()) {
                result += delimiter;
            }
        }

        return result;
    }
}
