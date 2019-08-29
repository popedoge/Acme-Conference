package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Submission;

@Repository
public interface SubmissionRepository
		extends
			JpaRepository<Submission, Integer> {

	@Query("select s from Submission s where s.owner=?1")
	List<Submission> findByOwner(int userId);

	@Query("select s from Submission s where s.owner=?1 and s.conference=?2")
	List<Submission> findByOwnerInConference(int userId, int conferenceId);

	@Query("select s from Submission s where s.conference=?1")
	List<Submission> findByConference(int conferenceId);
}
