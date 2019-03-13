
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m inner join m.container c where c.id = ?1")
	List<Message> findByMessageBox(int messageBoxId);

	@Query("select m from Message m inner join m.container c where c.category='NOTIF' and m.tick='false'")
	List<Message> findUnreadNotifications(int actorId);
}
