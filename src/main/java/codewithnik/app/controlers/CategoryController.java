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
import codewithnik.app.loadouts.CategoryDto;
import codewithnik.app.loadouts.CategoryResponse;
import codewithnik.app.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoDto) {
		CategoryDto cata =this.categoryService.createCategory(categoDto);
		return new ResponseEntity<CategoryDto>(cata, HttpStatus.CREATED);
	}
	              //create
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoDto,@PathVariable Integer categoryId) {
		CategoryDto meta =this.categoryService.updateCategory(categoDto,categoryId);
		return new ResponseEntity<CategoryDto>(meta, HttpStatus.OK);
	}
	             //update
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiExceptions> deleteCategory(@PathVariable Integer categoryId) {
	     this.categoryService.deleteCategory(categoryId);
	     return new ResponseEntity<ApiExceptions>(new ApiExceptions("Category Deleted Successfully...", true), HttpStatus.OK); 
	}      
	             //delete

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId) {
		CategoryDto metaa =this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(metaa, HttpStatus.OK);
	}
	            //get
	
	@GetMapping("/")
	public ResponseEntity<CategoryResponse> getAllCategory(
			@RequestParam(value = AppConstants.PAGE_NUMBER,defaultValue= AppConstants.PAGE_NUMBER ,required=false) Integer pageNumber,
			@RequestParam(value = AppConstants.PAGE_SIZE,defaultValue= AppConstants.PAGE_SIZE ,required=false) Integer pageSize
			) {
		CategoryResponse cat=this.categoryService.getAllCategory(pageNumber, pageSize);
		return new ResponseEntity<CategoryResponse> (cat, HttpStatus.OK);
	}
	           //get all

}
