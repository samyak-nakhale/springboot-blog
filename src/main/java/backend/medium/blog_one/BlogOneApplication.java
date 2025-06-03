package backend.medium.blog_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class BlogOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogOneApplication.class, args);
		System.out.println("Hello World");
	}

}
