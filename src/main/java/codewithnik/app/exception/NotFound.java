package codewithnik.app.exception;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter


public class NotFound extends RuntimeException {

	String resourceName;
	String fieldName;
	String fieldValue;
	

public NotFound(String resourceName,String fieldName,String fieldValue) {
	super("%s not found with %s :%s".formatted(resourceName, fieldName, fieldValue));
	this.resourceName=resourceName;
	this.fieldName=fieldName;
	this.fieldValue=fieldValue;
}
}
