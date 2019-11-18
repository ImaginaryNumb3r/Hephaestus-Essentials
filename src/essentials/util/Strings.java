package essentials.util;

import essentials.annotations.Positive;
import essentials.contract.InstanceNotAllowedException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Patrick Plieschnegger
 */
@SuppressWarnings("StringConcatenationInLoop")
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

    public static String join(String separator, String... strings) {
        return join(Arrays.asList(strings), separator);
    }

    public static String spaces(String... strings) {
        return join(Arrays.asList(strings), " ");
    }

    public static String joinToString(Iterable<?> objects, String separator) {
        var strings = new ArrayList<String>();
        objects.forEach(obj -> strings.add(obj.toString()));

        return join(strings, separator);
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
        var iter = strings.iterator();

        while (iter.hasNext()) {
            CharSequence string = iter.next();
            result += string;

            if (iter.hasNext()) {
                result += delimiter;
            }
        }

        return result;
    }

    public static String ofSequence(char character, @Positive int occurrence) {
        if (occurrence < 0)
            throw new IllegalArgumentException("Occurance must be positive for string sequences. Argument: " + occurrence);

        char[] chars = new char[occurrence];
        for (int i = 0; i != occurrence; ++i) {
            chars[i] = character;
        }

        return String.valueOf(chars);
    }
}
