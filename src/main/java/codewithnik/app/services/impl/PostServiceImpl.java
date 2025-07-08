package codewithnik.app.services.impl;
import java.time.Duration;
import java.time.LocalDateTime;  
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import codewithnik.app.entities.Category;
import codewithnik.app.entities.Post;
import codewithnik.app.entities.User;
import codewithnik.app.exception.ResourceNotFoundException;
import codewithnik.app.loadouts.PostDto;
import codewithnik.app.loadouts.PostResponse;
import codewithnik.app.repositories.CategoryRepo;
import codewithnik.app.repositories.PostRepo;
import codewithnik.app.repositories.UserRepo;
import codewithnik.app.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","user id", userId));
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id", categoryId));
		Post createdPost=this.modelMapper.map(postDto, Post.class);
		
	    
	      createdPost.setImageName("default.png");
	      createdPost.setUser(user);
	      createdPost.setCategory(cat);
	    
	      Post SavedPost =this.postRepo.save(createdPost);
		
	      PostDto posty =this.modelMapper.map(SavedPost, PostDto.class);
	      
	   
	      // Calculate "postedAgo" text
	      LocalDateTime now = LocalDateTime.now();
	      LocalDateTime created = createdPost.getCreatedDate();

	      Duration duration = Duration.between(created, now);

	      if (duration.toMinutes() < 1) {
	          posty.setPostedAgo("just now");
	      } 
	         else if (duration.toMinutes() < 60) {
	          posty.setPostedAgo(duration.toMinutes() + " minutes ago");
	      }
	         else if (duration.toHours() < 24) {
	          posty.setPostedAgo(duration.toHours() + " hours ago");
	      } 
	         else if (duration.toDays() < 7) {
	          posty.setPostedAgo(duration.toDays() + " days ago");
	      }
	         else {
	          long weeks = duration.toDays() / 7;
	          posty.setPostedAgo(weeks + " weeks ago");
	      }
	  
		return posty ;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	     Post posty=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
	      posty.setImageName(postDto.getImageName());
	      posty.setTitle(postDto.getTitle());
	      posty.setContent(postDto.getContent());
	     Post reqpost =this.postRepo.save(posty);
	      PostDto stream =this.modelMapper.map(reqpost, PostDto.class);
		return stream;
	}

	@Override
	public void deletePost(Integer postId) {
		Post posty=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
	    this.postRepo.delete(posty);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		Pageable p =PageRequest.of(pageNumber, pageSize, sort);
	   Page<Post> PagePost =this.postRepo.findAll(p);
	   List<Post> posts = PagePost.getContent();
	   List<PostDto> stream=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	   PostResponse postResponse = new PostResponse();
	   postResponse.setContent(stream);
	   postResponse.setPageNumber(PagePost.getNumber());
	   postResponse.setPageSize(PagePost.getSize());
	   postResponse.setTotalElements(PagePost.getTotalElements());
	   postResponse.setTotalPages(PagePost.getTotalPages());
	   postResponse.setLastPage(PagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post postreq=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		PostDto streammap=this.modelMapper.map(postreq, PostDto.class);
		return streammap;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	 Category category  =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "category id", categoryId));
		List<Post> reqposts =this.postRepo.findAllByCategory(category);
		List<PostDto> posty=reqposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return posty;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
		List<Post> reqposts=this.postRepo.findAllByUser(user);
		List<PostDto> stream =reqposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return stream;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts =this.postRepo.findByTitleContaining(keyword);
		List<PostDto> reqposts = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return reqposts;
	}
	

}
