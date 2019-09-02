
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

	@Query("select s from Submission s where s.owner.id=?1")
	List<Submission> findByOwner(int userId);

	@Query("select s from Submission s where s.owner.id=?1 and s.conference.id=?2")
	List<Submission> findByOwnerInConference(int userId, int conferenceId);

	@Query("select s from Submission s where s.conference.id=?1")
	List<Submission> findByConference(int conferenceId);

	//TODO: fix query
	//SELECT d FROM Document AS d WHERE :user MEMBER OF d.accessors
	@Query("select s from Submission s inner join s.reviewers r where r.id=?1")
	List<Submission> findByReviewer(int reviewerId);

	@Query("select s from Submission s inner join s.reviewers r where r.id=?1 and s.conference.id=?2")
	List<Submission> findByReviewerAndConference(int reviewerId, int conferenceId);
}
