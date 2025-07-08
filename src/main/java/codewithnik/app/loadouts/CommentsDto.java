package codewithnik.app.loadouts;
import java.time.LocalDateTime; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsDto {
	 
	private Integer id;
	
	private String content;
	
	private String commentedAgo;
	
	private LocalDateTime createdDate ;
	
	private LocalDateTime updatedDate ;

}
