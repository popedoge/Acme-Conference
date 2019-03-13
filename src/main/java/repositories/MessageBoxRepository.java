
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MessageBox;

@Repository
public interface MessageBoxRepository extends JpaRepository<MessageBox, Integer> {

	@Query("select a from MessageBox a where a.owner.id = ?1")
	List<MessageBox> findByActor(int actorId);

	@Query("select a from MessageBox a where a.owner.user=?1")
	List<MessageBox> findByUser(int userId);

	@Query("select a from MessageBox a where a.owner.id = ?1 and a.category = ?2")
	List<MessageBox> findByCategory(int actorId, String category);

	@Query("select a from MessageBox a where a.parent.id = ?1")
	List<MessageBox> findByParent(Integer parentId);

}
