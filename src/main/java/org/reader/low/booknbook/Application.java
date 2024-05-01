package org.reader.low.booknbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
		/*(scanBasePackages = {"org.reader.low.booknbook.controller",
		"org.reader.low.booknbook.persistence.repository",
		"org.reader.low.booknbook.service",
		"org.reader.low.booknbook.model.bnb",
		"org.reader.low.booknbook.model.bnb.id",
		"org.reader.low.booknbook.config",
		"org.reader.low.booknbook.config.security"
})*/
//@EnableJpaRepositories(basePackages = "org.reader.low.booknbook.persistence.repository")
public class  Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
