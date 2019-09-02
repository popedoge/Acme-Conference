
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.owner.id= ?1")
	public List<Report> findByOwner(int actorId);

	@Query("select r from Report r where r.submission.id= ?1")
	public List<Report> findBySubmission(int submissionId);
}
