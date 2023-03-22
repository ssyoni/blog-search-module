package modules.be.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"modules.be.api","modules.be.client","modules.be.domain"})
@EntityScan(basePackages = {"modules.be.domain"})
@EnableJpaRepositories(basePackages = {"modules.be.domain"})
public class ApiApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application,application-client,application-domain");
		SpringApplication.run(ApiApplication.class, args);
	}

}
