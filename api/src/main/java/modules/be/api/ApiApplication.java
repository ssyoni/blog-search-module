package modules.be.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"modules.be.api","modules.be.client","modules.be.domain"}
)
public class ApiApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application,application-domain");
		SpringApplication.run(ApiApplication.class, args);
	}

}
