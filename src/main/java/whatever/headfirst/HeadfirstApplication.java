package whatever.headfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
class HeadfirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeadfirstApplication.class, args);
	}

}
