
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	@Query("select r from Registration r where r.owner.id= ?1")
	public List<Registration> findByOwner(int actorId);

	@Query("select r from Registration r where r.conference.id= ?1")
	public List<Registration> findByConference(int conferenceId);

	@Query("select r from Registration r where r.owner.id= ?1 and r.conference.id= ?2")
	public Registration findByConferenceAndOwner(int actorId, int conferenceId);
}
