
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialNetworkRepository;
import domain.SocialNetwork;

@Service
@Transactional
public class SocialNetworkService {

	@Autowired
	private SocialNetworkRepository	socialNetRepo;


	public SocialNetwork findById(final int id) {
		return this.socialNetRepo.findOne(id);
	}

	public List<SocialNetwork> findAll() {
		return this.socialNetRepo.findAll();
	}

}
