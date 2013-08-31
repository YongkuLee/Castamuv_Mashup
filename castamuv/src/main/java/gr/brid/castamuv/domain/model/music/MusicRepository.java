package gr.brid.castamuv.domain.model.music;

import gr.brid.castamuv.domain.model.user.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MusicRepository extends JpaRepository<Music, MusicId> {
	@Query("select m from Music as m join m.likes as l where l.user = :user")
	List<Music> findByUser(@Param("user") User user);
}
