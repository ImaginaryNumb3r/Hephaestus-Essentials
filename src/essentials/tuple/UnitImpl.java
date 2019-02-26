package essentials.tuple;

import essentials.collections.IterableList;
import essentials.datastructure.Lazy;
import essentials.util.HashGenerator;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
@SuppressWarnings("WeakerAccess")
class UnitImpl<A> implements Serializable, IterableList<Object>, Unit<A> {
    protected A A;
    protected final Lazy<List<Object>> _values;
    protected final Lazy<Integer> _hashCode;

    // ===============
    //  Constructors
    // ===============

    public UnitImpl(Unit<A> a){
        this(a.getA());
    }

    public UnitImpl(A a) {
        A = a;
        _values = Lazy.of(makeArray());
        _hashCode = Lazy.of(this::makeHash);
    }

    // ===============
    //   Properties
    // ===============

    public A getA() {
        return A;
    }

    public void setA(A a) {
        A = a;
    }

    // ===============
    //    Methods
    // ===============

    protected Supplier<List<Object>> makeArray(){
        return () -> Collections.singletonList(A);
    }

    /**
     * Part for the equals
     * @apiNote Must be overridden by all subclasses
     * @param obj the
     * @return
     */
    protected boolean equalClass(Object obj){
        return obj instanceof Unit;
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return equalClass(obj) && hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return _hashCode.get();
    }

    public int makeHash() {
        return new HashGenerator(getClass())
                .appendAll(_values.get())
                .toHashCode();
    }

    @NotNull
    @Override
    public ListIterator<Object> iterator() {
        return _values.get().listIterator();
    }

    @NotNull
    @Override
    public ListIterator<Object> listIterator() {
        return iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");

        int count = 0;
        for (Iterator<Object> iter = _values.get().iterator(); iter.hasNext(); ){
            Object cur = iter.next();
            builder.append("Val").append(count++).append(": ").append(cur);

            if (iter.hasNext()){
                builder.append(" | ");
            }
            else {
                builder.append("]");
            }
        }

        return builder.toString();
    }
}
