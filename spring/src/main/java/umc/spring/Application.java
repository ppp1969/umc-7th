package umc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 스프링 어플리케이션에서 jpa를 사용하도록 변경
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
