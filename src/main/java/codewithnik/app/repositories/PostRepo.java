package codewithnik.app.repositories;

import java.util.List;  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codewithnik.app.entities.Category;
import codewithnik.app.entities.Post;
import codewithnik.app.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findAllByUser(User user);
	List<Post> findAllByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	List<Post> findByContentContaining(String content);
}
