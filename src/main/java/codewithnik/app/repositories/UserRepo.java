package codewithnik.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;
import codewithnik.app.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email) ;
	//create
	//update
	//delete
	//get
	//get all
}
