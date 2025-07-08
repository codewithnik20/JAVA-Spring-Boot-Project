package codewithnik.app.exception;
import java.util.HashMap; 
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import codewithnik.app.loadouts.ApiExceptions;

@RestControllerAdvice
public class GlobalExceptionHandel {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiExceptions> ResourceNotFoundExceptionHandel(ResourceNotFoundException ex){
		String message =ex.getMessage();
		ApiExceptions apiExceptions = new ApiExceptions(message,false);
		return new ResponseEntity<ApiExceptions>(apiExceptions, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handelMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message =error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<ApiExceptions> NotFound(NotFound ex){
		String message =ex.getMessage();
		ApiExceptions apiExceptions = new ApiExceptions(message,false);
		return new ResponseEntity<ApiExceptions>(apiExceptions, HttpStatus.NOT_FOUND);
	}
}
