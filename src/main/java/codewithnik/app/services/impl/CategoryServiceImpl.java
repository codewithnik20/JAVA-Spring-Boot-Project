package codewithnik.app.services.impl;
import java.util.List;   
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import codewithnik.app.entities.Category;
import codewithnik.app.exception.ResourceNotFoundException;
import codewithnik.app.loadouts.CategoryDto;
import codewithnik.app.loadouts.CategoryResponse;
import codewithnik.app.repositories.CategoryRepo;
import codewithnik.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category createdCategory =this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory =this.categoryRepo.save(createdCategory);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category findCat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", categoryId));
	   findCat.setCategoryTitle(categoryDto.getTitle());
	   findCat.setCategoryDescription(categoryDto.getCategoryDescription());
	   Category findings= this.categoryRepo.save(findCat);
		return this.modelMapper.map(findings, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
	 Category cattu =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", categoryId));
	 this.categoryRepo.delete(cattu);
	 
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		 Category Req = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", categoryId));
		 return this.modelMapper.map(Req,CategoryDto.class);
	
	}

	@Override
	public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Category> all= this.categoryRepo.findAll(p);
		 List<Category> cat = all.getContent();
	  List<CategoryDto> alltDto= cat.stream().map((categoryDto)-> this.modelMapper.map(categoryDto, CategoryDto.class)).collect(Collectors.toList());
	  CategoryResponse categoryResponse =new CategoryResponse();
	  categoryResponse.setContent(alltDto);
	  categoryResponse.setPageNumber(all.getNumber());
	  categoryResponse.setPageSize(all.getSize());
	 categoryResponse.setTotalElements(all.getTotalElements());
	 categoryResponse.setTotalPages(all.getTotalPages());
	 categoryResponse.setLastPage(all.isLast());
		return categoryResponse;
		
	}


}