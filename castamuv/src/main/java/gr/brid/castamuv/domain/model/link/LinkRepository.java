package gr.brid.castamuv.domain.model.link;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, LinkId> {
	Link findByValue(String value);
}
