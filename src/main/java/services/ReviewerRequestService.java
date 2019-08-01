package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ReviewerRequestRepository;
import domain.Actor;
import domain.ReviewerRequest;

@Service
@Transactional
public class ReviewerRequestService {

	@Autowired
	private ReviewerRequestRepository revreqrepo;

	public ReviewerRequest findById(int id) {
		return this.revreqrepo.findOne(id);
	}

	public List<ReviewerRequest> findAll(int id) {
		return this.revreqrepo.findAll();
	}

	public ReviewerRequest acceptRequest(ReviewerRequest request) {
		request.setStatus("ACCEPTED");
		ReviewerRequest saved = this.revreqrepo.save(request);
		return saved;
	}

	public ReviewerRequest denyRequest(ReviewerRequest request) {
		request.setStatus("DENIED");
		ReviewerRequest saved = this.revreqrepo.save(request);
		return saved;
	}

	public ReviewerRequest create(Actor actor) {
		ReviewerRequest request = new ReviewerRequest();
		request.setMoment(new Date());
		request.setRequestee(actor);
		request.setStatus("PENDING");
		return request;
	}

	public ReviewerRequest save(ReviewerRequest request) {
		return this.revreqrepo.save(request);
	}
}
