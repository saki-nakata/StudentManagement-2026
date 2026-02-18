package raisetech.StudentManagement2026ver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagement2026verApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagement2026verApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello() {
		return "お疲れさま";
	}

}
