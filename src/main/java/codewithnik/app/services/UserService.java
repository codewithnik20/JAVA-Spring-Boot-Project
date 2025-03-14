package codewithnik.app.services;
import java.util.List;

import org.springframework.stereotype.Service;

import codewithnik.app.loadouts.UserDto;

@Service
public interface UserService {
                                     
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
