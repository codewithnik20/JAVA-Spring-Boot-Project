package codewithnik.app.services;
import java.util.List;

import codewithnik.app.loadouts.CommentsDto;

public interface CommentsService {
	
	 CommentsDto createComment(CommentsDto commentsDto, Integer postId) ;
	
     CommentsDto editComment(CommentsDto commentsDto, Integer postId) ;
     
     List<CommentsDto> getCommentsByUser(Integer userId);
     
	void deleteComment(Integer commentId) ;

}
