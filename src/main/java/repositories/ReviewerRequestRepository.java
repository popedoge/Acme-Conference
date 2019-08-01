package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.ReviewerRequest;

public interface ReviewerRequestRepository
		extends
			JpaRepository<ReviewerRequest, Integer> {

}
