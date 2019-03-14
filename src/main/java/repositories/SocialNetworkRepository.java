
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialNetwork;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {

	@Query("select s from SocialNetwork s where s.name = ?1")
	public SocialNetwork findByName(String name);
}
