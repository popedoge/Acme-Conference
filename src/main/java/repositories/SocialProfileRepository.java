
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialProfile;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Integer> {

	@Query("select s from SocialProfile s where s.owner.id = ?1")
	public List<SocialProfile> findByActor(int id);
}
