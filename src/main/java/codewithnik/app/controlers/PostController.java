package codewithnik.app.controlers;
import java.io.IOException;   
import java.io.InputStream;
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import codewithnik.app.config.AppConstants;
import codewithnik.app.loadouts.ApiExceptions;
import codewithnik.app.loadouts.PostDto;
import codewithnik.app.loadouts.PostResponse;
import codewithnik.app.services.FileService;
import codewithnik.app.services.PostService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	
	@Value("${project.image}")
	private String path;
	//create
	
	
	 @GetMapping("/test")
	    public String hello() {
	        return "Backend Working!";
	   }
	
	//create

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		
		PostDto createdPost=this.postService.createPost(postDto, userId, categoryId);
	    
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	//update
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)  {
		PostDto posty=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto> (posty, HttpStatus.OK);
	
	}
	//delete
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiExceptions> deletePost(@PathVariable Integer postId)  {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiExceptions> (new ApiExceptions("Post Deleted Sucessfully...",true), HttpStatus.OK);
	}
	
	//get single post
	@GetMapping("/post/{postId}") 
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) { 
		PostDto stream =this.postService.getPostById(postId);
		return new ResponseEntity<PostDto> (stream, HttpStatus.OK);
		
	}
	
	//get all by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostsByUser(
			@PathVariable Integer userId) {
	List<PostDto> reqposts=this.postService.getPostsByUser(userId);
	
		return new ResponseEntity<List<PostDto>> (reqposts, HttpStatus.OK);
	}
	
	//get all by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>>getPostsByCategory(
			@PathVariable Integer categoryId) {
	List<PostDto> reqposts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>> (reqposts, HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = AppConstants.PAGE_NUMBER,defaultValue= AppConstants.PAGE_NUMBER ,required=false) Integer pageNumber,
			@RequestParam(value = AppConstants.PAGE_SIZE,defaultValue= AppConstants.PAGE_SIZE ,required=false) Integer pageSize,
			@RequestParam(value = AppConstants.SORT_BY,defaultValue= AppConstants.SORT_BY ,required=false) String sortBy,
			@RequestParam(value = AppConstants.SORT_DIR,defaultValue= AppConstants.SORT_DIR ,required=false) String sortDir
			 ) {
           PostResponse stream =this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
	return new ResponseEntity<PostResponse> (stream, HttpStatus.OK);
	}
	
	//search
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable(AppConstants.KEYWORDS) String keywords
			)  {
		List<PostDto> result =this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>> (result,HttpStatus.OK);
	}
	
	//post image upload
	
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam(AppConstants.IMAGE) MultipartFile image,
			@PathVariable Integer postId)       throws IOException   {
		
		String fileName = this.fileService.uploadImage(path, image);
		PostDto postDto = this.postService.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatedPost =this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto> (updatedPost, HttpStatus.OK);
	}
	
	//method to serve files
	@GetMapping(value= "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable(AppConstants.IMAGE) String imageName,
			HttpServletResponse response
			) throws IOException  {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
}
