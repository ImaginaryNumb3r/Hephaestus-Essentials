package essentials.tuple;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
class TupleImpl<A, B> extends UnitImpl<A> implements Tuple<A, B> {
    protected B B;

    public TupleImpl(@NotNull Tuple<A, B> tuple){
        this(tuple.getA(), tuple.getB());
    }

    public TupleImpl(@Nullable A a, @Nullable B b) {
        super(a);
        B = b;
    }

    public B getB() {
        return B;
    }

    public void setB(B b) {
        B = b;
    }

    @Override
    protected boolean equalClass(Object obj) {
        return obj instanceof Tuple;
    }

    @Override
    protected Supplier<List<Object>> makeArray(){
        return () -> Arrays.asList(A, B);
    }
}