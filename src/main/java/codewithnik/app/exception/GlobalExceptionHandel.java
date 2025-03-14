package codewithnik.app.exception;

 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
