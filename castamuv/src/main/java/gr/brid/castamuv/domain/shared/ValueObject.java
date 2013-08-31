package gr.brid.castamuv.domain.shared;

import java.io.Serializable;

public interface ValueObject<V> extends Serializable {

	boolean sameValueOf(V object);

}
