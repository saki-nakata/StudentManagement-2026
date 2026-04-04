package raisetech.StudentManagement2026ver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム",
    description = "受講生情報や受講生コース情報を管理するアプリ", version = "v1.0.0"),
    servers = {
        @Server(url = "http://localhost:8080", description = "ローカル環境")
    })
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
