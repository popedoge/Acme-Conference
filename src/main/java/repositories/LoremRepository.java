/*
 * ActorRepository.java
 *
 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lorem;

@Repository
public interface LoremRepository extends JpaRepository<Lorem, Integer> {

	@Query("select l from Lorem l where l.reference.id=?1")
	public List<Lorem> findByReference(int id);

	@Query("select l from Lorem l where l.locked=TRUE")
	public List<Lorem> findFinal();

	@Query("select l from Lorem l where l.locked=TRUE and l.reference.id=?1")
	public List<Lorem> findFinalByReference(int id);
}
