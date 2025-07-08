package codewithnik.app.entities;
import java.time.LocalDateTime;  
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Posts")
@NoArgsConstructor
@Setter
@Getter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String title;

	@Column(length = 10000)
	private String content;

	private String imageName;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	private String postedAgo ;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	@JsonIgnore                       //or you can set Comments below as CommentsDto      
	
	private Set<Comments> comments= new HashSet<>();
}
