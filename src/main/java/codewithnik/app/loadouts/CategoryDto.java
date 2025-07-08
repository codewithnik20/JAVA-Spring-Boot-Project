package codewithnik.app.loadouts; 
import jakarta.validation.constraints.NotEmpty; 
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	 
	private int categoryId;

	@NotEmpty
	@Size(min=8,max=14)
	private String Title;
	
	@NotEmpty
	@Size(min=12,max=100)
	private String categoryDescription;

}
