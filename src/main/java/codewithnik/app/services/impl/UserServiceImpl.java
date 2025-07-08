package codewithnik.app.services.impl;
import java.util.List;  
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import codewithnik.app.entities.User;
import codewithnik.app.loadouts.UserDto;
import codewithnik.app.loadouts.UserResponse;
import codewithnik.app.repositories.UserRepo;
import codewithnik.app.services.UserService;
import codewithnik.app.exception.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	 
	@Override
	public UserDto createUser(UserDto userDto) {
	
		User user =this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1= this.userToDto(updatedUser);
		return userDto1;
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		return this.userToDto(user);
		
	}
	
	@Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<User> userr=this.userRepo.findAll(p);
		 List<User> usercon = userr.getContent();
		List<UserDto> userDtos= usercon.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(userDtos);
		userResponse.setPageNumber(userr.getNumber());
		userResponse.setPageSize(userr.getSize());
		userResponse.setTotalElements(userr.getTotalElements());
		userResponse.setTotalPages(userr.getTotalPages());
		userResponse.setLastPage(userr.isLast());
		return userResponse; 
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		User user =this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
	/*	UserDto userDto =new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());        */
		return userDto;
	}

	}


