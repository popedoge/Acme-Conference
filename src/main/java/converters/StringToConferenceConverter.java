/*
 * StringToConferenceConverter.java
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

import domain.Conference;
import repositories.ConferenceRepository;

@Component
@Transactional
public class StringToConferenceConverter implements Converter<String, Conference> {

	@org.springframework.beans.factory.annotation.Autowired
	ConferenceRepository conferenceRepository;


	@Override
	public Conference convert(final String text) {
		Conference result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.conferenceRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
