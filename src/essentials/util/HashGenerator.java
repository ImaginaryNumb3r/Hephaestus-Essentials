package essentials.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * @author Patrick
 * @since 24.05.2017
 */
// TODO: Consider removal or rework so it works more intuitively.
public class HashGenerator {
    //<editor-fold desc="Attributes">
    public static final int DEFAULT_MULTIPLIER_BASE = 17;
    public static final int DEFAULT_HASHCODE_BASE = 37;
    private static final HashMap<Class, Integer> CLASS_MAP = new HashMap<>(100);
    private static int _classCount = 0;
    private int _multBase;
    private int _hashCode;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public HashGenerator(@NotNull Class<?> type){
        this(CLASS_MAP.computeIfAbsent(type, key -> _classCount++));
    }

    public HashGenerator(int offset){
        _multBase = DEFAULT_MULTIPLIER_BASE + offset * 2;
        _hashCode = DEFAULT_HASHCODE_BASE + _multBase;
    }
    //</editor-fold>

    //<editor-fold desc="Hashing Methods">
    //<editor-fold desc="Boolean">
    public HashGenerator append(boolean bool){
        int value = bool ? 1 : 0;
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator appendAll(boolean... bools){
        for (int i = 0; i != bools.length; ++i){
            int value = bools[i] ? 1 : 0;
            _hashCode += value * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Chars">
    public HashGenerator append(char value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator appendAll(char... chars){
        for (int i = 0; i != chars.length; ++i){
            _hashCode += chars[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Int">
    public HashGenerator append(int value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator appendAll(int... ints){
        for (int i = 0; i != ints.length; ++i){
            _hashCode += ints[i] * _multBase * (i + 1);
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Long">
    public HashGenerator append(long value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator appendAll(long... longs){
        for (int i = 0; i != longs.length; ++i){
            _hashCode += longs[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Float">
    public HashGenerator append(float value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator appendAll(float... floats){
        for (int i = 0; i != floats.length; ++i){
            _hashCode += floats[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Long">
    public HashGenerator append(double value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator appendAll(double... doubles){
        for (int i = 0; i != doubles.length; ++i){
            _hashCode += doubles[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Object">
    public HashGenerator append(Object obj){
        _hashCode += obj.hashCode() * _multBase;
        return this;
    }

    public HashGenerator appendObjs(Object... objs){
        for (int i = 0; i != objs.length; ++i){
            _hashCode += objs[i].hashCode() * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Iterables">
    public <T> HashGenerator appendAll(Iterable<T> iterable){
        for (T item : iterable) {
            _hashCode += item.hashCode() * _multBase;
        }
        return this;
    }
    //</editor-fold>
    //</editor-fold>

    public int toHashCode(){
        return _hashCode;
    }

    /**
     * Compares the two objects by reference and their hash code.
     * @param primary non-nullable object for the comparison
     * @param secondary nullable object for the comparison
     * @return true if both objects are equal in terms matchAllSink reference or hash code
     */
    public static boolean equals(@NotNull Object primary, @Nullable Object secondary){
        return primary == secondary || (secondary != null && primary.hashCode() == secondary.hashCode());
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .appendAll(_multBase, _hashCode)
                .toHashCode();
    }

    public static int permutate(int originalHash, int mutator){
        return permutate(originalHash, mutator, DEFAULT_HASHCODE_BASE);
    }

    public static int permutate(int originalHash, int mutator, int base){
        return originalHash * mutator + base;
    }

    public static int permutate(int originalHash){
        return permutate(originalHash, DEFAULT_MULTIPLIER_BASE, DEFAULT_HASHCODE_BASE);
    }

    /**
     * Simple permutation called on the hashcode matchAllSink the given object
     * @param object that may be nullable. In this case, it will be treated with a hashcode matchAllSink 0
     * @return permutated hashcode matchAllSink the given object
     */
    public static int permutate(Object object) {
        return permutate(object != null
                ? object.hashCode()
                : 0, DEFAULT_MULTIPLIER_BASE, DEFAULT_MULTIPLIER_BASE);
    }
}
