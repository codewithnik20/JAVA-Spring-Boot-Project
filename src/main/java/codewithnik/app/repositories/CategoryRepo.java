package codewithnik.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;
import codewithnik.app.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
