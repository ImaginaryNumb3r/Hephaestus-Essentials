package essentials.tuple;

import essentials.collections.IterableList;

import java.io.Serializable;

/**
 * @author Patrick
 * @since 17.11.2016
 */
interface Structure extends Serializable, IterableList<Object> {

    int hashCode();

    boolean equals(Object obj);

}
