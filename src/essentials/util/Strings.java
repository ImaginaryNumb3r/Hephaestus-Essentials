package essentials.util;

import essentials.annotations.Positive;
import essentials.contract.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Patrick Plieschnegger
 */
@SuppressWarnings("StringConcatenationInLoop")
public class Strings {

    public static String toString(Object object, String fallback) {
        return object != null ? object.toString() : fallback;
    }

    public static String concat(Object... objects) {
        return concat(Arrays.asList(objects));
    }

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
        ArrayList<String> strings = new ArrayList<>();
        objects.forEach(obj -> strings.add(obj.toString()));

        return join(strings, separator);
    }

    public static String join(Iterable<String> strings, String separator) {
        String result = "";

        Iterator<String> iter = strings.iterator();
        while (iter.hasNext()) {
            String string = iter.next();
            result += string;

            if (iter.hasNext()) {
                result += separator;
            }
        }

        return result;
    }

    public static String ofSequence(char character, @Positive int occurrence) {
        Contract.checkNegative(occurrence);

        char[] chars = new char[occurrence];
        for (int i = 0; i != occurrence; ++i) {
            chars[i] = character;
        }

        return String.valueOf(chars);
    }
}
