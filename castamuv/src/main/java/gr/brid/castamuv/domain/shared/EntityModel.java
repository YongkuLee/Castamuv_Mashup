package gr.brid.castamuv.domain.shared;

public interface EntityModel<E> {

	boolean sameEntityAs(E model);

}
