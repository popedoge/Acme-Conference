/*
 * StringToReviewerConverter.java
 *
 * Copyright (C) 2019 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reviewer;
import repositories.ReviewerRepository;

@Component
@Transactional
public class StringToReviewerConverter implements Converter<String, Reviewer> {

	@org.springframework.beans.factory.annotation.Autowired
	ReviewerRepository ReviewerRepository;


	@Override
	public Reviewer convert(final String text) {
		Reviewer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.ReviewerRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
