package codewithnik.app.loadouts;
import java.time.LocalDateTime; 
import java.util.HashSet;
import java.util.Set;
import codewithnik.app.entities.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private int postId ;
	
	private String title ;
	
	private  String content ;
	
	private String imageName ;
	
	private LocalDateTime createdDate ;
	
	private LocalDateTime updatedDate ;
	
	private String postedAgo ;
	
	private UserDto user ;
	
	private CategoryDto category ;
	
	private Set<Comments> comments = new HashSet<>() ;
	
}
