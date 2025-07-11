package codewithnik.app.entities;
import jakarta.persistence.Entity;      
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name="users")
@Setter
@Getter

public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name", nullable=false, length=100)
	private String name;
	
   private String email;
	
   
   @JsonIgnore
   private String password;
	
   
   private String about;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comments> comments = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = @JoinColumn(name ="user",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id")
			)
	
	private Set<Role> roles = new HashSet<>();

	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() {         // Since, Spring Security understands authorities not roles 
		
	  List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()) ;
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return this.email ;
	}
	
	@Override
	public String getPassword() {
		
	    return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		
	    return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
	    return true;
	}

	@Override
	public boolean isEnabled() {
		
	    return true;
	}



	
}
