package codewithnik.app.services;
import java.util.List;
import codewithnik.app.loadouts.PostDto;
import codewithnik.app.loadouts.PostResponse;

public interface PostService {

	//PostCreation
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    
    //PostUpdate
    PostDto updatePost(PostDto postDto, Integer postId);
    
    //deletePost
    void deletePost(Integer postId);
    
    //GetAll
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    
    //get
    PostDto getPostById(Integer postId);
    
    //GetAllByCategory
    List<PostDto> getPostsByCategory(Integer categoryId);
    
    //GetAllByUser
    List<PostDto> getPostsByUser(Integer userId);
    
    //searchPost
    List<PostDto> searchPost(String keyword);
}
