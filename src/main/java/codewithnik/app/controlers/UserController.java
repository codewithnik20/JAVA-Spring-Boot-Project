package codewithnik.app.controlers;

import java.util.Map; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;   
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import codewithnik.app.loadouts.UserDto;
import codewithnik.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	 
	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public ResponseEntity<String> getTest() {
	    return ResponseEntity.ok("Test API is working!");
	}
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){           
		UserDto createUserDto =this.userService.createUser(userDto);                  
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	      //POST_createuser	
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid) {
		UserDto updatedUser =this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	     //PUT_toupdate the user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return ResponseEntity.ok(Map.of("message","User Deleted Successfully"));
	}
	    //DELETE_Delete the user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	   //GET_Get All the users
	 	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	   //GET_Get A user
	
	
		
	}

