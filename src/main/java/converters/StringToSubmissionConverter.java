/*
 * StringToSubmissionConverter.java
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

import domain.Submission;
import repositories.SubmissionRepository;

@Component
@Transactional
public class StringToSubmissionConverter implements Converter<String, Submission> {

	@org.springframework.beans.factory.annotation.Autowired
	SubmissionRepository SubmissionRepository;


	@Override
	public Submission convert(final String text) {
		Submission result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.SubmissionRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
