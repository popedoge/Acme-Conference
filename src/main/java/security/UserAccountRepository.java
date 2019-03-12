/*
 * UserAccountRepository.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<User, Integer> {

	@Query("select ua from User ua where ua.username = ?1")
	User findByUsername(String username);

	@Query("select a.user from Actor a where a.id = ?1")
	User findByActorId(int actorId);

}
