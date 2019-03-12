
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ActorPreferences;

@Repository
public interface ActorPreferencesRepository extends JpaRepository<ActorPreferences, Integer> {

	@Query("select a from ActorPreferences a where a.owner.id = ?1")
	public ActorPreferences findByActor(int id);
}
