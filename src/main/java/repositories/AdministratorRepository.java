/*
 * AdministratorRepository.java
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

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select count(a.id) from Submission a group by a.conference")
	public List<Long> submissionsPerConference();

	@Query("select count(a.id) from Registration a group by a.conference")
	public List<Long> registrationsPerConference();

	@Query("select a.fee from Conference a")
	public List<Long> feePerConference();

	//TODO: edit this stuff
	@Query("select count(a.id) from Lorem a group by a.reference")
	public List<Long> loremPerReference();
}
