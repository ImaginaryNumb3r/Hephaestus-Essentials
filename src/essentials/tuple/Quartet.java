package essentials.tuple;

import essentials.collections.ArrayListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface Quartet<A, B, C, D> extends Triplet<A, B, C> {

    static <A, B, C, D> Quartet from(@NotNull Quartet<A, B, C, D> quartet) {
        return new QuartetImpl<>(quartet.getA(), quartet.getB(), quartet.getC(), quartet.getD());
    }

    static <A, B, C, D> Quartet from(@NotNull Triplet<A, B, C> triplet, @Nullable D d) {
        return new QuartetImpl<>(triplet.getA(), triplet.getB(), triplet.getC(), d);
    }

    static <A, B, C, D> Quartet from(@NotNull Tuple<A, B> tuple, @Nullable C c, @Nullable D d) {
        return new QuartetImpl<>(tuple.getA(), tuple.getB(), c, d);
    }

    static <A, B, C, D> Quartet from(@NotNull Unit<A> unit, @Nullable B b, @Nullable C c, @Nullable D d) {
        return new QuartetImpl<>(unit.getA(), b, c, d);
    }

    static <A, B, C, D> Quartet from(@Nullable A a, @Nullable B b, @Nullable C c, @Nullable D d) {
        return new QuartetImpl<>(a, b, c, d);
    }

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    D getD();

    @NotNull
    @Override
    default ListIterator<Object> listIterator(){
        return ArrayListIterator.of(getA(), getB(), getC(), getD());
    }
}
