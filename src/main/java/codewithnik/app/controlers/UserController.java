package codewithnik.app.controlers;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codewithnik.app.config.AppConstants;
import codewithnik.app.loadouts.ApiExceptions;
import codewithnik.app.loadouts.UserDto;
import codewithnik.app.loadouts.UserResponse;
import codewithnik.app.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	 
	@Autowired
	private UserService userService;
  
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){//request ko DTO me store karaya aur aur use @Requestbody ki help se call kiya constructor mein 
		UserDto createUserDto =this.userService.createUser(userDto);//phir us dto ko createuser method me pass kiya aur phir class ka instance banake return karaya http response ke bhes me
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	      //POST_createuser	
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		UserDto updatedUser =this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	} //PUT_toupdate the user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiExceptions> deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiExceptions>(new ApiExceptions("User Deleted Successfully", true), HttpStatus.OK);
	} //DELETE_Delete the user
	
	@GetMapping("/")
	public ResponseEntity<UserResponse> getAllUsers(
			@RequestParam(value = AppConstants.PAGE_NUMBER,defaultValue= AppConstants.PAGE_NUMBER ,required=false) Integer pageNumber,
			@RequestParam(value = AppConstants.PAGE_SIZE,defaultValue= AppConstants.PAGE_SIZE ,required=false) Integer pageSize
			) {
		UserResponse user=this.userService.getAllUsers(pageNumber, pageSize);
		return new ResponseEntity<UserResponse> (user, HttpStatus.OK);
	}   //GET_Get All the users
	 	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	} //GET_Get A user
	}

