package codewithnik.app.exception;
import lombok.Getter;    
import  lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@Getter

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
		String fieldName;
		long fieldValue;
		
   
	public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue) {
		super("%s not found with %s :%s".formatted(resourceName, fieldName, fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}

}
