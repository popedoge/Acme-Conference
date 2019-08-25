package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository
		extends
			JpaRepository<Conference, Integer> {

	@Query("")
	public List<Conference> findByOwner(int id);
}
