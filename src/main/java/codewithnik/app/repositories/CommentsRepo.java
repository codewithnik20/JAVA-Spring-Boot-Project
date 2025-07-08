package codewithnik.app.repositories;
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import codewithnik.app.entities.Comments;
import codewithnik.app.entities.User;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer> {
	
	List<Comments> findAllByUser(User user);

}
