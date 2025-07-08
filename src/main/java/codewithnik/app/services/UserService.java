package codewithnik.app.services;
import codewithnik.app.loadouts.UserDto; 
import codewithnik.app.loadouts.UserResponse;

public interface UserService {
                                     
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	UserResponse getAllUsers(Integer pageNumber, Integer pageSize);
	
	void deleteUser(Integer userId);
	
}
