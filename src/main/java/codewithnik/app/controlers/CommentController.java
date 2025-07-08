package codewithnik.app.controlers;
import java.util.List; 
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
import org.springframework.web.bind.annotation.RestController;
import codewithnik.app.loadouts.ApiExceptions;
import codewithnik.app.loadouts.CommentsDto;
import codewithnik.app.services.CommentsService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	
	 @Autowired
	 private CommentsService commentService ;
	
	@PostMapping("/{postId}/create")                                                    //Create Comments
	public ResponseEntity <CommentsDto> createComment (
			@RequestBody CommentsDto commentsDto, 
			@PathVariable Integer postId)      {
		
		CommentsDto comment = this.commentService.createComment(commentsDto, postId);
		
		return new ResponseEntity <CommentsDto> (comment, HttpStatus.CREATED);
	}
	
	@PutMapping("/{commentId}/edit")                                                  //Edit Comments
	public ResponseEntity<CommentsDto> editComment (
			@RequestBody CommentsDto commentsDto,
			@PathVariable Integer commentId)           {
		
		CommentsDto comment = this.commentService.editComment(commentsDto, commentId) ;
		
		return new ResponseEntity<CommentsDto> (comment, HttpStatus.OK) ;
		
	}
	
	@DeleteMapping("/{commentId}/delete")                                             //Delete Comments
	
	public ResponseEntity<ApiExceptions> deleteComment (@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiExceptions> (new ApiExceptions("Comment Deleted SucessFully...☺️", true), HttpStatus.OK) ;
		
	}
	
	@GetMapping("/{userId}/commentsByUser")                                         //Get Comment By User
	 
	 public ResponseEntity<List<CommentsDto>> getAllByUser(@PathVariable Integer userId)   {
	 List<CommentsDto> req =this.commentService.getCommentsByUser(userId) ;
	 return new ResponseEntity<List<CommentsDto>>(req, HttpStatus.OK);

}
}
