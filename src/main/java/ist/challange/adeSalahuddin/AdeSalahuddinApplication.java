package ist.challange.adeSalahuddin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AdeSalahuddinApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdeSalahuddinApplication.class, args);
    }

    //	@Bean
//	PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
