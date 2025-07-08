package codewithnik.app.api;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
@EnableJpaAuditing
@EnableJpaRepositories("codewithnik.app.repositories")
@EntityScan("codewithnik.app.entities")


@SpringBootApplication(scanBasePackages = "codewithnik.app")
public class Application {
	

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}
    @Bean
     ModelMapper modelMapper() {
    	return new ModelMapper();
	}
	
    
 
  
}