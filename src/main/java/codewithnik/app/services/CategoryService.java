package codewithnik.app.services;
import codewithnik.app.loadouts.CategoryDto;
import codewithnik.app.loadouts.CategoryResponse;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	//create
	
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	//update
	
	public void deleteCategory(int categoryId);
	//delete
	
	CategoryDto getCategory(int categoryId);
	//get
	
	CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize);
	//get all

}
