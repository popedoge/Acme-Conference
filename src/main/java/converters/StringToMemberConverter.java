/*
 * StringToMemberConverter.java
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

import domain.Member;
import repositories.MemberRepository;

@Component
@Transactional
public class StringToMemberConverter implements Converter<String, Member> {

	@org.springframework.beans.factory.annotation.Autowired
	MemberRepository MemberRepository;


	@Override
	public Member convert(final String text) {
		Member result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.MemberRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
