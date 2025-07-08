package codewithnik.app.config;
import org.springframework.context.annotation.Bean;     
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import codewithnik.app.security.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final CustomUserDetailService userDetailsService ;

    public SecurityConfig(CustomUserDetailService userDetailsService) {                         //For Fetching The User by email or username
        this.userDetailsService = userDetailsService;
    }
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {                  //To Write Security Rules
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()                  // login required for any get request
            )
            .httpBasic(Customizer.withDefaults());             //basic authentication
            
        return http.build();
    }
    
    @Bean
        DaoAuthenticationProvider daoAuthenticationProvider() {                  // USECASE - Authenticate The UserDetails or Manages flow of Authentication
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();    // Authorise Password Match
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
        PasswordEncoder passwordEncoder() {                                                  // Encode The Password
        return new BCryptPasswordEncoder();
    }

}