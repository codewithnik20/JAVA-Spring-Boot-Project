package codewithnik.app.security;
import org.springframework.beans.factory.annotation.Autowired;   
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import codewithnik.app.entities.User;
import codewithnik.app.exception.NotFound;
import codewithnik.app.repositories.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Loading user from database by UserName
		User user =this.userRepo.findByEmail(username).orElseThrow(()-> new NotFound("User", "email : ", "username"));
		
		return user;
	}
	
	

}
