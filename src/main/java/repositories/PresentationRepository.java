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

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Presentation;

@Transactional
public interface PresentationRepository extends JpaRepository<Presentation, Integer> {

}
