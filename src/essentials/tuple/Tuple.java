package essentials.tuple;

import essentials.collections.ArrayListIterator;
import essentials.contract.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 *
 * A iteration generic value pair
 */
public interface Tuple<A, B> extends Unit<A>{

    static <A, B> Tuple<A, B> from(Tuple<A, B> tuple){
        Contract.checkNull(tuple, "tuple");
        return new TupleImpl<>(tuple);
    }

    static <A, B> Tuple<A, B> from(Unit<A> unit, @Nullable B b){
        Contract.checkNull(unit, "unit");
        return new TupleImpl<>(unit.getA(), b);
    }

    static <A, B> Tuple<A, B> from(@Nullable A a, @Nullable B b){
        return new TupleImpl<>(a, b);
    }

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    B getB();

    @NotNull
    @Override
    default ListIterator<Object> listIterator(){
        return ArrayListIterator.of(getA(), getB());
     }
}