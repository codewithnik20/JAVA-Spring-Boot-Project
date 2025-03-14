package codewithnik.app.api;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.ModelMap;
import org.modelmapper.ModelMapper;
 
@EnableJpaRepositories("codewithnik.app.repositories")
@SpringBootApplication(scanBasePackages = "codewithnik.app")
@EntityScan("codewithnik.app.entities")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public ModelMapper modelMapper() {
    	return new Modelmapper();
		
	}

}
