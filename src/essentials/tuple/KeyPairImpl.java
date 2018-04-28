package essentials.tuple;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author Patrick
 * @since 19.11.2016
 */

class KeyPairImpl<Key, Val> extends TupleImpl<Key, Val> implements KeyPair<Key, Val> {

    public KeyPairImpl(@NotNull Tuple<Key, Val> tuple) {
        super(tuple);
    }

    public KeyPairImpl(@Nullable Key key, @Nullable Val val) {
        super(key, val);
    }

    public void putInto(Map<Key, Val> map){
        map.put(A, B);
    }
}