package codewithnik.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import codewithnik.app.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
