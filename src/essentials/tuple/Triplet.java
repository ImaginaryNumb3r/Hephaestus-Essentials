package essentials.tuple;

import essentials.collections.ArrayListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface Triplet<A, B, C> extends Tuple<A, B> {

    static <A, B, C> Triplet from(@NotNull Triplet<A, B, C> triplet){
        return new TripletImpl<>(triplet.getA(), triplet.getB(), triplet.getC());
    }

    static <A, B, C> Triplet from(@NotNull Tuple<A, B> tuple, @Nullable C c){
        return new TripletImpl<>(tuple.getA(), tuple.getB(), c);
    }

    static <A, B, C> Triplet from(@NotNull Unit<A> unit, @Nullable B b, @Nullable C c){
        return new TripletImpl<>(unit.getA(), b, c);
    }

    static <A, B, C> Triplet<A, B, C> from(@Nullable A a, @Nullable B b, @Nullable C c) {
        return new TripletImpl<>(a, b, c);
    }

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    C getC();

    @NotNull
    @Override
    default ListIterator<Object> listIterator(){
        return ArrayListIterator.of(getA(), getB(), getC());
    }
}
