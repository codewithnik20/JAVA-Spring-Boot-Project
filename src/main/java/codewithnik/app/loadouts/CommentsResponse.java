package codewithnik.app.loadouts;
import java.util.List; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter


public class CommentsResponse {

		private List<CommentsDto> content;
		
		private int pageNumber;
		
		private int pageSize;
		
		private Long totalElements;
		
		private int totalPages;
		
		private boolean lastPage;
		
	}



