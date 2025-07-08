package codewithnik.app.loadouts;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter

public class UserDto {

	
	private int id;
	
    @NotEmpty(message="Name can'nt be Empty")
    @Size(min=6,message="Username must be of min length 6")
	private String name;
	
	@Email(message="Please Write a Valid Email Address")
	private String email;
	
	@NotEmpty(message="password field can'nt be Empty")
	@Size(min=8,max=16,message="Password must be of min 8 and max 16")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
	         message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one  special character, and be at least 8 characters long")
	@JsonIgnore
	private String password;
	
	
	@NotEmpty(message="about can'nt be Empty")
	private String about;
}
