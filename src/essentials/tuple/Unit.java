package essentials.tuple;

import essentials.contract.Contract;
import essentials.collections.ArrayListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface Unit<A> extends Structure {

    static <A> Unit<A> from(@NotNull Unit<A> unit){
        Contract.checkNull(unit, "unit");
        return new UnitImpl<>(unit);
    }

    static <A> Unit<A> from(@Nullable A a){
        return new UnitImpl<>(a);
    }

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    A getA();

    @NotNull
    @Override
    default ListIterator<Object> listIterator(){
        return ArrayListIterator.of(getA());
    }
}
