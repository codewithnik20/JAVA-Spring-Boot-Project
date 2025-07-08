package codewithnik.app.services.impl;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewithnik.app.entities.Comments;
import codewithnik.app.entities.Post;
import codewithnik.app.entities.User;
import codewithnik.app.exception.ResourceNotFoundException;
import codewithnik.app.loadouts.CommentsDto;
import codewithnik.app.repositories.CommentsRepo;
import codewithnik.app.repositories.PostRepo;
import codewithnik.app.repositories.UserRepo;
import codewithnik.app.services.CommentsService;


@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	private CommentsRepo commentsRepo ;
	
	@Autowired
	private UserRepo userRepo ;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired 
	private ModelMapper modelMapper ;
	

	@Override
	public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
		Comments comments = this.modelMapper.map(commentsDto, Comments.class);
		comments.setPost(post);
		Comments savedComment = this.commentsRepo.save(comments);
		
		CommentsDto commic = this.modelMapper.map(savedComment, CommentsDto.class);
		
		   
	      // Calculate "postedAgo" text
	      LocalDateTime now = LocalDateTime.now();
	      LocalDateTime created = comments.getCreatedDate();

	      Duration duration = Duration.between(created, now);

	      if (duration.toMinutes() < 1) {
	          commic.setCommentedAgo(" just now ");
	      } 
	         else if (duration.toMinutes() < 60) {
	          commic.setCommentedAgo(duration.toMinutes() + " minutes ago");
	      }
	         else if (duration.toHours() < 24) {
	          commic.setCommentedAgo(duration.toHours() + " hours ago");
	      } 
	         else if (duration.toDays() < 7) {
	          commic.setCommentedAgo(duration.toDays() + " days ago");
	      }
	         else {
	          long weeks = duration.toDays() / 7;
	          commic.setCommentedAgo(weeks + " weeks ago");
	      }
		
		return commic ;
	}

	@Override
	public CommentsDto editComment(CommentsDto commentsDto, Integer commentId) {
		Comments comment = this.commentsRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "comment id", commentId));
		comment.setContent(commentsDto.getContent());
		Comments momenta = this.commentsRepo.save(comment);
		
	      return this.modelMapper.map(momenta, CommentsDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comments comment = this.commentsRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id", commentId));
		this.commentsRepo.delete(comment);
		
	}
	
	@Override
	public List<CommentsDto> getCommentsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
		List<Comments> comment = this.commentsRepo.findAllByUser(user) ;
		List<CommentsDto> req = comment.stream().map((comments)->this.modelMapper.map(comments, CommentsDto.class)).collect(Collectors.toList());
		return req ;

	}

}
