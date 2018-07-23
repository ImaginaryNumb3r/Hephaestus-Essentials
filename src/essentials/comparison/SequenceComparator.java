package essentials.comparison;

import essentials.annotations.ToTest;

import java.util.*;

/**
 * Creator: Patrick
 * Created: 16.05.2018
 * Purpose:
 */
@ToTest
public class SequenceComparator<T> implements Comparator<T> {
    private final Map<T, Integer> _valueMap;

    private SequenceComparator(Map<T, Integer> valueMap) {
        _valueMap = valueMap;
    }

    @Override
    public int compare(T o1, T o2) {
        Integer val1 = _valueMap.get(o1);
        Integer val2 = _valueMap.get(o2);

        if (val1 == null || val2 == null) {
            String message;

            if (val1 == null && val2 == null) {
                message = "Neither \"" + o1.toString() + "\" nor \"" + o2.toString() + "\" were not indexed during initialization";
            } else {
                String value = val1 == null ? o1.toString() : o2.toString();
                message = "Value \"" + value + "\" was not index during initialization";
            }

            throw new IllegalStateException(message);
        }

        return Integer.compare(val1, val2);
    }

    @SafeVarargs
    public static <T> Comparator<T> ofDistinct(T... collection) {
        return ofDistinct(Arrays.asList(collection));
    }

    public static <T> Comparator<T> ofDistinct(Collection<T> collection) {
        HashMap<T, Integer> hashMap = new HashMap<>(collection.size());

        return createComparator(hashMap, collection);
    }

    public static <E extends Enum<E>> Comparator<E> ofEnum(E[] enumeration) {
        Class<E> type = enumeration[0].getDeclaringClass();
        EnumMap<E, Integer> hashMap = new EnumMap<>(type);

        return createComparator(hashMap, Arrays.asList(enumeration));
    }

    private static <K> Comparator<K> createComparator(Map<K, Integer> map, Collection<K> collection) {
        int i = 0;
        for (K item : collection) {
            Integer existingValue = map.put(item, i);

            if (existingValue != null) {
                String message = "Sequence Comparator needs to be constructed with a collection of unique values! Duplicate found: " + existingValue.toString();
                throw new IllegalArgumentException(message);
            }
        }

        return new SequenceComparator<>(map);
    }
}
